package com.routex.app.driver.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.routex.app.MainActivity
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.location.LocationProvider
import com.routex.app.driver.domain.repository.DriverRepository
import com.routex.app.maps.data.geofencing.GeofenceNotificationHelper
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.repository.TripRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class LocationTrackingService : Service() {

    @Inject lateinit var locationProvider: LocationProvider
    @Inject lateinit var driverRepository: DriverRepository
    @Inject lateinit var tripRepository: TripRepository

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var trackingJob: Job? = null

    // Persisted across start/stop within the same service lifecycle
    private var activeRouteId: String   = ""
    private var activeBusNumber: String = ""
    private var activeTripId: String    = ""

    // Auto-delay tracking: last timestamp when speed was above the moving threshold
    private var lastMovedAtMs: Long = 0L
    private var isCurrentlyDelayed: Boolean = false

    // Campus arrival — only fire once per trip
    private var campusArrivalFired: Boolean = false

    companion object {
        const val ACTION_START = "ACTION_START_TRACKING"
        const val ACTION_STOP  = "ACTION_STOP_TRACKING"

        const val EXTRA_ROUTE_ID   = "EXTRA_ROUTE_ID"
        const val EXTRA_BUS_NUMBER = "EXTRA_BUS_NUMBER"
        const val EXTRA_TRIP_ID    = "EXTRA_TRIP_ID"

        /** Bus must be stationary for this long (ms) before trip is marked DELAYED. */
        private const val DELAY_THRESHOLD_MS   = 3 * 60 * 1_000L   // 3 minutes
        /** Speeds below this (km/h) are considered "stationary". */
        private const val MOVING_THRESHOLD_KMH = 3f
        /**
         * GPS accuracy threshold (metres).  Only apply delay logic when the fix
         * is at least this accurate — filters out false-zero speeds in tunnels,
         * parking garages, or dense urban canyons with poor satellite visibility.
         */
        private const val MIN_GPS_ACCURACY_M = 50f

        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID      = "routex_driver_channel"
        private const val TAG             = "LocationTrackingService"

        fun startIntent(
            context: Context,
            routeId: String,
            busNumber: String,
            tripId: String = "",
        ): Intent = Intent(context, LocationTrackingService::class.java).apply {
            action = ACTION_START
            putExtra(EXTRA_ROUTE_ID, routeId)
            putExtra(EXTRA_BUS_NUMBER, busNumber)
            putExtra(EXTRA_TRIP_ID, tripId)
        }

        fun stopIntent(context: Context): Intent =
            Intent(context, LocationTrackingService::class.java).apply {
                action = ACTION_STOP
            }
    }

    // ── Lifecycle ─────────────────────────────────────────────────────────────

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                val routeId   = intent.getStringExtra(EXTRA_ROUTE_ID) ?: return START_NOT_STICKY
                val busNumber = intent.getStringExtra(EXTRA_BUS_NUMBER) ?: "Unknown"
                activeRouteId      = routeId
                activeBusNumber    = busNumber
                activeTripId       = intent.getStringExtra(EXTRA_TRIP_ID).orEmpty()
                lastMovedAtMs      = System.currentTimeMillis()
                isCurrentlyDelayed = false
                campusArrivalFired = false
                createNotificationChannel()
                startForegroundWithNotification()
                startTracking()
            }
            ACTION_STOP -> stopSelf()
        }
        // START_NOT_STICKY: trip state lives in Firestore; do not auto-restart with null intent,
        // which would call startForeground() never and crash on API 26+.
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        // Best-effort offline status update — must NOT block main thread.
        // serviceScope is still running here (cancelled after the launch).
        if (activeRouteId.isNotBlank()) {
            serviceScope.launch {
                runCatching {
                    kotlinx.coroutines.withTimeoutOrNull(3_000L) {
                        driverRepository.setOnlineStatus(activeRouteId, false)
                    }
                }
            }
        }
        trackingJob?.cancel()
        serviceScope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    // ── Foreground notification ───────────────────────────────────────────────

    private fun startForegroundWithNotification() {
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE
        else 0

        val openApp = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            flags,
        )
        val stopAction = PendingIntent.getService(
            this, 1,
            stopIntent(this),
            flags,
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("RouteX – Tracking Active")
            .setContentText("Bus $activeBusNumber on Route $activeRouteId")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(openApp)
            .addAction(
                android.R.drawable.ic_menu_close_clear_cancel,
                "Stop",
                stopAction,
            )
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    // ── Location loop ─────────────────────────────────────────────────────────

    private fun startTracking() {
        // Mark online
        serviceScope.launch {
            runCatching { driverRepository.setOnlineStatus(activeRouteId, true) }
        }

        trackingJob?.cancel()
        trackingJob = locationProvider
            .locationUpdates(LocationProvider.DRIVER_INTERVAL_MS)
            .onEach { location ->
                val speedKmh = location.speed * 3.6f
                Log.d(TAG, "Upload [$activeRouteId] ${location.latitude},${location.longitude} @${speedKmh.roundToInt()}km/h acc=${location.accuracy}m")

                refreshNotification(speedKmh.roundToInt())

                driverRepository.uploadLocation(
                    routeId   = activeRouteId,
                    latitude  = location.latitude,
                    longitude = location.longitude,
                    speed     = speedKmh,
                    heading   = location.bearing,
                    accuracy  = location.accuracy,
                    busNumber = activeBusNumber,
                )

                // ── Auto-delay detection (accuracy-gated to avoid false positives) ──
                checkAndUpdateDelayStatus(
                    speedKmh  = speedKmh,
                    accuracy  = location.accuracy,
                    hasSpeed  = location.hasSpeed(),
                )

                // ── Campus arrival detection ──────────────────────────────────
                checkCampusArrival(location.latitude, location.longitude)
            }
            .catch { e -> Log.e(TAG, "Tracking error", e) }
            .launchIn(serviceScope)
    }

    /**
     * Detects when the bus has been stationary longer than [DELAY_THRESHOLD_MS].
     *
     * **Accuracy gate:** Only runs when the GPS fix meets both conditions:
     * - [accuracy] < [MIN_GPS_ACCURACY_M] (fix is precise enough to trust)
     * - [hasSpeed] is true (GPS hardware is reporting speed, not just 0.0f default)
     *
     * Without this gate, tunnels/parking garages produce GPS fixes with
     * speed = 0.0 and accuracy > 100 m, which would falsely trigger DELAYED.
     */
    private fun checkAndUpdateDelayStatus(
        speedKmh: Float,
        accuracy: Float,
        hasSpeed: Boolean,
    ) {
        if (activeTripId.isBlank()) return

        // Discard unreliable GPS fixes — do not penalise the bus for poor signal
        if (!hasSpeed || accuracy > MIN_GPS_ACCURACY_M) {
            Log.d(TAG, "Delay check skipped: hasSpeed=$hasSpeed accuracy=${accuracy}m")
            return
        }

        val isMoving = speedKmh >= MOVING_THRESHOLD_KMH
        val now      = System.currentTimeMillis()

        if (isMoving) {
            lastMovedAtMs = now
            if (isCurrentlyDelayed) {
                isCurrentlyDelayed = false
                serviceScope.launch {
                    runCatching { tripRepository.updateTripStatus(activeTripId, TripStatus.RUNNING) }
                    Log.d(TAG, "Trip $activeTripId: DELAYED → RUNNING")
                }
            }
        } else {
            val stationaryForMs = now - lastMovedAtMs
            if (!isCurrentlyDelayed && stationaryForMs >= DELAY_THRESHOLD_MS) {
                isCurrentlyDelayed = true
                serviceScope.launch {
                    runCatching { tripRepository.updateTripStatus(activeTripId, TripStatus.DELAYED) }
                    Log.d(TAG, "Trip $activeTripId: RUNNING → DELAYED (stationary ${stationaryForMs / 60_000}min)")
                }
            }
        }
    }

    /**
     * Checks whether the bus has entered the KLS GIT campus radius.
     * If yes (and not previously fired for this trip):
     *  1. Ends the active trip in Firestore (marks COMPLETED).
     *  2. Posts a "Arrived at KLS GIT" system notification.
     *  3. Stops the tracking service — the trip is done.
     */
    private fun checkCampusArrival(busLat: Double, busLng: Double) {
        if (campusArrivalFired || activeTripId.isBlank()) return

        val dist = FloatArray(1)
        android.location.Location.distanceBetween(
            busLat, busLng,
            CollegeHub.LATITUDE, CollegeHub.LONGITUDE,
            dist,
        )

        if (dist[0] <= CollegeHub.GEOFENCE_RADIUS_M) {
            campusArrivalFired = true
            Log.d(TAG, "Campus arrival detected for trip $activeTripId (${dist[0].toInt()} m from KLS GIT)")

            serviceScope.launch {
                // End the trip in Firestore
                runCatching { tripRepository.endTrip(activeTripId) }
                // Campus arrived notification (uses notification ID 2999 to avoid conflict)
                GeofenceNotificationHelper.notifyArrivedAtCollege(
                    context        = this@LocationTrackingService,
                    notificationId = 2999,
                )
                // Auto-stop the tracking service — the driver is at college
                stopSelf()
            }
        }
    }

    private fun refreshNotification(speedKmh: Int) {
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE else 0
        val openApp    = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java), flags)
        val stopAction = PendingIntent.getService(
            this, 1, stopIntent(this), flags)

        val n = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("RouteX – Tracking Active")
            .setContentText("Bus $activeBusNumber  •  ${speedKmh} km/h")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(openApp)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopAction)
            .build()
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(NOTIFICATION_ID, n)
    }

    // ── Notification channel ──────────────────────────────────────────────────

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "RouteX Driver Tracking",
                NotificationManager.IMPORTANCE_LOW,
            ).apply {
                description = "Live GPS tracking status for drivers"
                setShowBadge(false)
                enableVibration(false)
            }
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }
}
