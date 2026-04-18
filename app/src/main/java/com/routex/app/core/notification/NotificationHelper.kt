package com.routex.app.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.routex.app.MainActivity
import com.routex.app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Central helper for posting all non-foreground notifications in RouteX.
 *
 * Notification IDs are deterministic so that repeated updates replace the same
 * notification rather than stacking them (e.g., ETA updates share the same ID
 * within a single route).
 */
@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val manager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        createChannels()
    }

    // ── ETA Notifications ─────────────────────────────────────────────────────

    fun showEtaAlert(routeId: String, title: String, body: String) {
        val id = (ETA_BASE_ID + routeId.hashCode()).coerceIn(1, Int.MAX_VALUE)
        postNotification(
            id          = id,
            channelId   = NotificationChannels.ETA_CHANNEL_ID,
            title       = title,
            body        = body,
            priority    = NotificationCompat.PRIORITY_HIGH,
        )
    }

    // ── Generic remote FCM pass-through ──────────────────────────────────────

    fun showRemoteNotification(title: String, body: String) {
        postNotification(
            id        = FCM_GENERIC_ID,
            channelId = NotificationChannels.ETA_CHANNEL_ID,
            title     = title,
            body      = body,
            priority  = NotificationCompat.PRIORITY_DEFAULT,
        )
    }

    // ── Core posting logic ────────────────────────────────────────────────────

    private fun postNotification(
        id: Int,
        channelId: String,
        title: String,
        body: String,
        priority: Int,
    ) {
        val tapIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            id,
            tapIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_splash_logo)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(id, notification)
    }

    // ── Channel creation ──────────────────────────────────────────────────────

    private fun createChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channels = listOf(
            NotificationChannel(
                NotificationChannels.ETA_CHANNEL_ID,
                NotificationChannels.ETA_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                description = "Alerts when your bus is near"
                enableVibration(true)
            },
            NotificationChannel(
                NotificationChannels.DRIVER_CHANNEL_ID,
                NotificationChannels.DRIVER_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW,
            ).apply {
                description = "Persistent tracking notification for drivers"
            },
            NotificationChannel(
                NotificationChannels.GEOFENCE_CHANNEL_ID,
                NotificationChannels.GEOFENCE_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                description = "Alerts when bus enters a stop zone"
                enableVibration(true)
            },
        )
        channels.forEach(manager::createNotificationChannel)
    }

    companion object {
        private const val ETA_BASE_ID    = 2000
        private const val FCM_GENERIC_ID = 9999
    }
}
