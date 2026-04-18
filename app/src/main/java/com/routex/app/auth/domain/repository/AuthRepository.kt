package com.routex.app.auth.domain.repository

import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    /** Emits the currently authenticated user, or null when signed out. */
    val authState: Flow<User?>

    suspend fun signInWithEmail(email: String, password: String): Resource<User>
    
    suspend fun signInWithGoogle(idToken: String): Resource<User>

    /** Checks Firestore `drivers` collection: email must exist AND driverCode must match. */
    suspend fun validateDriverCode(email: String, driverCode: String): Resource<String>

    /**
     * Registers a new driver account after validating driverCode against Firestore.
     * Blocks if the code is invalid or the email is not pre-registered by admin.
     */
    suspend fun signUpDriver(
        email: String,
        password: String,
        displayName: String,
        driverCode: String,
    ): Resource<User>

    suspend fun signUpWithEmail(
        email: String,
        password: String,
        displayName: String,
        role: UserRole,
    ): Resource<User>

    suspend fun signOut()

    fun isLoggedIn(): Boolean

    suspend fun getCurrentUser(): User?

    /**
     * Returns a cold Flow that emits the full [User] (with role) every time
     * the Firestore user document changes.  Useful for detecting mid-session
     * role changes (e.g., admin promotes a student to driver while they are
     * active).
     */
    fun observeCurrentUser(): Flow<User?>
}
