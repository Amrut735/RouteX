package com.routex.app.maps.data.geofencing

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

/**
 * Receives OS-level geofence transition events.
 *
 * Responsibilities:
 *  1. Forward events to [GeofenceManager]'s in-process event bus (for the live MapScreen snackbar).
 *  2. Post a system notification via [GeofenceNotificationHelper] so students are alerted
 *     even when the app is backgrounded or killed.
 */
class GeofenceBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val event = GeofencingEvent.fromIntent(intent) ?: return
        if (event.hasError()) {
            Log.e(TAG, "Geofence error code: ${event.errorCode}")
            return
        }

        val transition         = event.geofenceTransition
        val triggeringGeofences = event.triggeringGeofences ?: return
        val triggeringLocation  = event.triggeringLocation

        if (transition == Geofence.GEOFENCE_TRANSITION_ENTER ||
            transition == Geofence.GEOFENCE_TRANSITION_DWELL
        ) {
            triggeringGeofences.forEachIndexed { index, geofence ->
                val parts    = geofence.requestId.split("|")
                val geofenceId = parts[0]
                val stopName = if (parts.size > 3) parts[3] else "Stop"
                val stopLat  = parts.getOrNull(1)?.toDoubleOrNull() ?: 0.0
                val stopLng  = parts.getOrNull(2)?.toDoubleOrNull() ?: 0.0
                val isCampus = geofenceId == "campus_git"

                val distArr = FloatArray(1)
                if (triggeringLocation != null && stopLat != 0.0) {
                    Location.distanceBetween(
                        triggeringLocation.latitude,
                        triggeringLocation.longitude,
                        stopLat, stopLng,
                        distArr,
                    )
                }

                Log.d(TAG, "Bus entering geofence: $stopName (${distArr[0].toInt()} m) campus=$isCampus")

                if (isCampus) {
                    // 🎓 Special campus arrival notification
                    GeofenceNotificationHelper.notifyArrivedAtCollege(
                        context        = context,
                        notificationId = CAMPUS_NOTIFICATION_ID,
                    )
                } else {
                    // Regular stop proximity notification
                    GeofenceNotificationHelper.notifyBusNearStop(
                        context        = context,
                        stopName       = stopName,
                        distanceMeters = distArr[0],
                        notificationId = GEOFENCE_NOTIFICATION_BASE_ID + index,
                    )
                }

                // In-process event bus (for foreground snackbar + MapViewModel)
                GeofenceManager.onGeofenceEvent(transition, triggeringGeofences)
            }
        }
    }

    companion object {
        private const val TAG = "GeofenceReceiver"
        private const val GEOFENCE_NOTIFICATION_BASE_ID = 2000
        private const val CAMPUS_NOTIFICATION_ID        = 2999
    }
}
