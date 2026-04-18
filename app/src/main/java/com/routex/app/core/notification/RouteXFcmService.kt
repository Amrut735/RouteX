package com.routex.app.core.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * FCM entry-point.
 *
 * Handles two events:
 *  1. [onNewToken]         — platform rotated the registration token; save to Firestore.
 *  2. [onMessageReceived]  — Cloud Function (or backend) sent a data/notification message.
 */
@AndroidEntryPoint
class RouteXFcmService : FirebaseMessagingService() {

    @Inject lateinit var tokenRepository: FcmTokenRepository
    @Inject lateinit var notificationHelper: NotificationHelper

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onNewToken(token: String) {
        serviceScope.launch {
            tokenRepository.saveToken(token)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // Prefer notification payload; fall back to data payload
        val title = message.notification?.title
            ?: message.data["title"]
            ?: return

        val body = message.notification?.body
            ?: message.data["body"]
            ?: return

        notificationHelper.showRemoteNotification(title, body)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}
