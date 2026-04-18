package com.routex.app.maps.data.geofencing

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.routex.app.core.location.CollegeHub
import com.routex.app.maps.domain.model.GeofenceEvent
import com.routex.app.maps.domain.model.GeofenceTransition
import com.routex.app.student.domain.model.BusStop
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

const val GEOFENCE_RADIUS_M = 200f          // trigger radius in metres
private const val GEOFENCE_LOITERING_DELAY = 10_000  // 10 s dwell before DWELL event

@Singleton
class GeofenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val client: GeofencingClient = LocationServices.getGeofencingClient(context)

    // ── Shared event bus used by MapViewModel ────────────────────────────────
    companion object {
        private val _events = MutableSharedFlow<GeofenceEvent>(extraBufferCapacity = 16)
        val events: SharedFlow<GeofenceEvent> = _events.asSharedFlow()

    /** Called by [GeofenceBroadcastReceiver]. */
    fun onGeofenceEvent(
        transition: Int,
        geofences: List<com.google.android.gms.location.Geofence>,
    ) {
        // requestId format: "id|lat|lng|name"
        // Campus requestId prefix: "campus_git|..."
        geofences.forEach { g ->
            val parts       = g.requestId.split("|")
            if (parts.size < 3) return@forEach
            val lat         = parts[1].toDoubleOrNull() ?: return@forEach
            val lng         = parts[2].toDoubleOrNull() ?: return@forEach
            val stopName    = parts.getOrNull(3).orEmpty()
            val isCampus    = parts[0] == "campus_git"
            val stop        = BusStop(id = parts[0], name = stopName, latitude = lat, longitude = lng)
            val t = when (transition) {
                Geofence.GEOFENCE_TRANSITION_ENTER -> GeofenceTransition.ENTER
                Geofence.GEOFENCE_TRANSITION_DWELL -> GeofenceTransition.DWELL
                else                               -> GeofenceTransition.EXIT
            }
            _events.tryEmit(GeofenceEvent(stop, 0f, t, isCampusEntry = isCampus))
        }
    }
    }

    // ── Register campus geofence ─────────────────────────────────────────────

    /**
     * Registers a single geofence for KLS GIT campus.
     * Call this once after the map loads (alongside [registerStopGeofences]).
     */
    @SuppressLint("MissingPermission")
    suspend fun registerCampusGeofence() {
        val campusGeofence = Geofence.Builder()
            .setRequestId(CollegeHub.GEOFENCE_REQUEST_ID)
            .setCircularRegion(CollegeHub.LATITUDE, CollegeHub.LONGITUDE, CollegeHub.GEOFENCE_RADIUS_M)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()

        val request = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(campusGeofence)
            .build()

        runCatching { client.addGeofences(request, buildPendingIntent()).await() }
    }

    // ── Register geofences around bus stops ──────────────────────────────────

    @SuppressLint("MissingPermission")
    suspend fun registerStopGeofences(stops: List<BusStop>) {
        if (stops.isEmpty()) return
        removeAll()

        val geofences = stops.map { stop ->
            // requestId format: "stopId|lat|lng|stopName"
            val safeName = stop.name.replace("|", "-")
            Geofence.Builder()
                .setRequestId("${stop.id}|${stop.latitude}|${stop.longitude}|$safeName")
                .setCircularRegion(stop.latitude, stop.longitude, GEOFENCE_RADIUS_M)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(
                    Geofence.GEOFENCE_TRANSITION_ENTER or
                        Geofence.GEOFENCE_TRANSITION_DWELL or
                        Geofence.GEOFENCE_TRANSITION_EXIT,
                )
                .setLoiteringDelay(GEOFENCE_LOITERING_DELAY)
                .build()
        }

        val request = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(geofences)
            .build()

        client.addGeofences(request, buildPendingIntent()).await()
    }

    suspend fun removeAll() = runCatching { client.removeGeofences(buildPendingIntent()).await() }

    // ── Software geofence check (complementary to OS geofencing) ────────────

    /**
     * Computes whether [busLocation] is within [GEOFENCE_RADIUS_M] of any stop
     * and emits a [GeofenceEvent] for the nearest qualifying stop.
     * Called from MapViewModel on each bus location update.
     */
    fun checkProximity(
        busLat: Double,
        busLng: Double,
        stops: List<BusStop>,
    ) {
        val results = FloatArray(1)
        stops.forEach { stop ->
            Location.distanceBetween(busLat, busLng, stop.latitude, stop.longitude, results)
            if (results[0] <= GEOFENCE_RADIUS_M) {
                _events.tryEmit(GeofenceEvent(stop, results[0], GeofenceTransition.ENTER))
            }
        }
    }

    /**
     * Software-based campus proximity check — mirrors the OS geofence.
     * Called from [MapViewModel] on each bus location update.
     * Returns true if the bus is within the campus arrival radius.
     */
    fun checkCampusProximity(busLat: Double, busLng: Double): Boolean {
        val results = FloatArray(1)
        Location.distanceBetween(busLat, busLng, CollegeHub.LATITUDE, CollegeHub.LONGITUDE, results)
        val distanceM = results[0]
        if (distanceM <= CollegeHub.GEOFENCE_RADIUS_M) {
            val campusStop = BusStop(
                id        = "campus_git",
                name      = CollegeHub.SHORT_NAME,
                latitude  = CollegeHub.LATITUDE,
                longitude = CollegeHub.LONGITUDE,
            )
            _events.tryEmit(
                GeofenceEvent(
                    stop          = campusStop,
                    distanceMeters = distanceM,
                    transition    = GeofenceTransition.ENTER,
                    isCampusEntry = true,
                ),
            )
            return true
        }
        return false
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private fun buildPendingIntent(): PendingIntent {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        else
            PendingIntent.FLAG_UPDATE_CURRENT
        return PendingIntent.getBroadcast(context, 0, intent, flags)
    }
}
