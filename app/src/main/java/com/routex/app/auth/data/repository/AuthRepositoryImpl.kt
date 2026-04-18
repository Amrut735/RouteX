package com.routex.app.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.repository.AuthRepository
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

private const val USERS_COLLECTION = "users"

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val database: FirebaseDatabase,
) : AuthRepository {

    override val authState: Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser == null) {
                trySend(null)
            } else {
                // Emit a minimal stub; full data is fetched via getCurrentUser()
                trySend(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email.orEmpty(),
                        displayName = firebaseUser.displayName.orEmpty(),
                    ),
                )
            }
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String,
    ): Resource<User> = runCatching {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: error("Sign-in returned no user")
        fetchUserFromFirestore(uid) ?: error("User profile not found in Firestore")
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Sign-in failed", it) },
    )

    override suspend fun signUpWithEmail(
        email: String,
        password: String,
        displayName: String,
        role: UserRole,
    ): Resource<User> = runCatching {
        if (role == UserRole.ADMIN) {
            error("Admin accounts cannot be created via the app.")
        }
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: error("Sign-up returned no user")
        val user = User(
            uid = uid,
            email = email,
            displayName = displayName,
            role = role,
        )
        firestore.collection(USERS_COLLECTION).document(uid).set(user.toMap()).await()
        // Mirror role to RTDB so RTDB rules can see it immediately.
        // The Cloud Function will also do this asynchronously; this is a belt-and-
        // suspenders write for the very first login before the Function triggers.
        runCatching {
            database.getReference("user_roles/$uid")
                .setValue(mapOf("role" to role.name.lowercase(), "updatedAt" to System.currentTimeMillis()))
                .await()
        }
        user
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Sign-up failed", it) },
    )

    override suspend fun signInWithGoogle(idToken: String): Resource<User> = runCatching {
        val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
        val result = firebaseAuth.signInWithCredential(credential).await()
        val uid = result.user?.uid ?: error("Google Sign-In returned no user")
        
        var user = fetchUserFromFirestore(uid)
        if (user == null) {
            user = User(
                uid = uid,
                email = result.user?.email.orEmpty(),
                displayName = result.user?.displayName.orEmpty(),
                role = UserRole.STUDENT,
                profileImageUrl = result.user?.photoUrl?.toString()
            )
            firestore.collection(USERS_COLLECTION).document(uid).set(user.toMap()).await()
            runCatching {
                database.getReference("user_roles/$uid")
                    .setValue(mapOf("role" to "student", "updatedAt" to System.currentTimeMillis()))
                    .await()
            }
        }
        user
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Google Sign-In failed", it) },
    )

    override suspend fun validateDriverCode(
        email: String,
        driverCode: String,
    ): Resource<String> = runCatching {
        val query = firestore.collection("drivers")
            .whereEqualTo("email", email.trim().lowercase())
            .get().await()
        val doc = query.documents.firstOrNull()
            ?: error("No driver record found for this email. Contact your admin.")
        val storedCode = doc.getString("driverCode") ?: error("Driver code not configured.")
        if (storedCode != driverCode.trim()) error("Invalid driver code.")
        doc.id   // return driverId
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Validation failed", it) },
    )

    override suspend fun signUpDriver(
        email: String,
        password: String,
        displayName: String,
        driverCode: String,
    ): Resource<User> = runCatching {
        // 1) Validate code first — fail fast before touching Auth
        val validationResult = validateDriverCode(email, driverCode)
        if (validationResult is Resource.Error) error(validationResult.message)
        val driverId = (validationResult as Resource.Success).data

        // 2) Create Firebase Auth account
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: error("Sign-up returned no user")

        val user = User(
            uid = uid,
            email = email,
            displayName = displayName,
            role = com.routex.app.auth.domain.model.UserRole.DRIVER,
        )

        // 3) Write to users collection
        firestore.collection(USERS_COLLECTION).document(uid).set(user.toMap()).await()

        // 4) Update driver document to link authUid
        firestore.collection("drivers").document(driverId)
            .update("authUid", uid, "displayName", displayName).await()

        // 5) Mirror role to RTDB
        runCatching {
            database.getReference("user_roles/$uid")
                .setValue(mapOf("role" to "driver", "updatedAt" to System.currentTimeMillis()))
                .await()
        }
        user
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Driver sign-up failed", it) },
    )

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override fun isLoggedIn(): Boolean = firebaseAuth.currentUser != null

    override suspend fun getCurrentUser(): User? {
        val uid = firebaseAuth.currentUser?.uid ?: return null
        return fetchUserFromFirestore(uid)
    }

    /**
     * Observes the current user's Firestore document in real time.
     * Emits `null` when no user is signed in, or when the document is deleted.
     * Emits an updated [User] whenever the document changes (role update, etc.).
     */
    override fun observeCurrentUser(): Flow<User?> = flow {
        val uid = firebaseAuth.currentUser?.uid
        if (uid == null) {
            emit(null)
            return@flow
        }
        emitAll(callbackFlow {
            val registration = firestore.collection(USERS_COLLECTION)
                .document(uid)
                .addSnapshotListener { snapshot, error ->
                    if (error != null || snapshot == null || !snapshot.exists()) {
                        trySend(null)
                        return@addSnapshotListener
                    }
                    val user = runCatching {
                        User(
                            uid             = uid,
                            email           = snapshot.getString("email").orEmpty(),
                            displayName     = snapshot.getString("displayName").orEmpty(),
                            role            = UserRole.fromString(snapshot.getString("role").orEmpty()),
                            phoneNumber     = snapshot.getString("phoneNumber"),
                            profileImageUrl = snapshot.getString("profileImageUrl"),
                            createdAt       = snapshot.getLong("createdAt") ?: System.currentTimeMillis(),
                        )
                    }.onFailure { e ->
                        android.util.Log.e("AuthRepository", "Failed to parse user doc for uid=$uid: $e")
                    }.getOrNull()
                    // Only emit if parsing succeeded; a null parse must NOT log the user out
                    if (user != null) trySend(user)
                }
            awaitClose { registration.remove() }
        })
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

    private suspend fun fetchUserFromFirestore(uid: String): User? = runCatching {
        val snap = firestore.collection(USERS_COLLECTION).document(uid).get().await()
        if (!snap.exists()) return null
        User(
            uid = uid,
            email = snap.getString("email").orEmpty(),
            displayName = snap.getString("displayName").orEmpty(),
            role = UserRole.fromString(snap.getString("role").orEmpty()),
            phoneNumber = snap.getString("phoneNumber"),
            profileImageUrl = snap.getString("profileImageUrl"),
            createdAt = snap.getLong("createdAt") ?: System.currentTimeMillis(),
        )
    }.getOrNull()

    private fun User.toMap(): Map<String, Any?> = mapOf(
        "uid"             to uid,
        "email"           to email,
        "displayName"     to displayName,
        "role"            to role.name.lowercase(),
        "phoneNumber"     to phoneNumber,
        "profileImageUrl" to profileImageUrl,
        "createdAt"       to createdAt,
    )
}
