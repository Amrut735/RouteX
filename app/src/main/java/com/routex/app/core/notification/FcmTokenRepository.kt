package com.routex.app.core.notification

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages the FCM registration token lifecycle.
 *
 * The token is saved to the user's Firestore document so that Cloud Functions
 * can retrieve it and send targeted push notifications.
 */
@Singleton
class FcmTokenRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {
    /** Fetch the current FCM token and persist it to Firestore. */
    suspend fun refreshAndSaveToken() {
        val uid = auth.currentUser?.uid ?: return
        try {
            val token = FirebaseMessaging.getInstance().token.await()
            saveToken(token, uid)
        } catch (_: Exception) { /* Token refresh is best-effort */ }
    }

    /** Called by [RouteXFcmService] when the platform rotates the token. */
    suspend fun saveToken(token: String) {
        val uid = auth.currentUser?.uid ?: return
        saveToken(token, uid)
    }

    /** Save boarding-stop subscription so Cloud Functions know which stop to watch. */
    suspend fun saveStopSubscription(routeId: String, stopId: String, stopName: String) {
        val uid = auth.currentUser?.uid ?: return
        try {
            val token = FirebaseMessaging.getInstance().token.await()
            firestore.collection("subscriptions").document(uid).set(
                mapOf(
                    "uid"       to uid,
                    "routeId"   to routeId,
                    "stopId"    to stopId,
                    "stopName"  to stopName,
                    "fcmToken"  to token,
                    "active"    to true,
                )
            ).await()
        } catch (_: Exception) { /* Best-effort */ }
    }

    suspend fun clearStopSubscription() {
        val uid = auth.currentUser?.uid ?: return
        try {
            firestore.collection("subscriptions").document(uid)
                .update("active", false).await()
        } catch (_: Exception) { }
    }

    private suspend fun saveToken(token: String, uid: String) {
        firestore.collection("users").document(uid)
            .update("fcmToken", token)
            .await()
    }
}
