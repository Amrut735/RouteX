package com.routex.app.maps.data.geofencing

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.routex.app.MainActivity

private const val CHANNEL_ID   = "routex_geofence_channel"
private const val CHANNEL_NAME = "RouteX Bus Alerts"

/**
 * Posts a system notification when the bus enters a stop's geofence.
 * Works even when the app is in the background or killed — called from
 * [GeofenceBroadcastReceiver] which is an OS-level receiver.
 */
object GeofenceNotificationHelper {

    fun notifyBusNearStop(
        context: Context,
        stopName: String,
        distanceMeters: Float,
        notificationId: Int,
    ) {
        createChannel(context)

        val openApp = PendingIntent.getActivity(
            context,
            0,
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            else
                PendingIntent.FLAG_UPDATE_CURRENT,
        )

        val distanceText = if (distanceMeters < 50f) "arriving now"
                           else "${distanceMeters.toInt()} m away"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setContentTitle("🚌  Bus approaching $stopName")
            .setContentText("Your bus is $distanceText")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Your bus is $distanceText from $stopName. Get ready!"),
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(openApp)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notificationId, notification)
    }

    /**
     * High-priority notification shown when the bus enters the KLS GIT
     * campus geofence. Notifies all students that they have arrived.
     */
    fun notifyArrivedAtCollege(context: Context, notificationId: Int) {
        createChannel(context)

        val openApp = PendingIntent.getActivity(
            context, 0,
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            else PendingIntent.FLAG_UPDATE_CURRENT,
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_mapmode)
            .setContentTitle("🎓  Arrived at KLS GIT")
            .setContentText("Your bus has reached KLS Gogte Institute of Technology")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Your bus has arrived at KLS Gogte Institute of Technology. Trip is now complete!"),
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 300, 200, 300))
            .setContentIntent(openApp)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notificationId, notification)
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                description = "Alerts when your bus is near a stop"
                enableVibration(true)
            }
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }
}
