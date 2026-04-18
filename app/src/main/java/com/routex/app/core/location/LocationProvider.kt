package com.routex.app.core.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Singleton
class LocationProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val fusedClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    /**
     * Emits a continuous stream of [Location] updates at the requested interval.
     * Call only after runtime location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    fun locationUpdates(
        intervalMs: Long = DRIVER_INTERVAL_MS,
        minIntervalMs: Long = intervalMs / 2,
    ): Flow<Location> = callbackFlow {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, intervalMs)
            .setMinUpdateIntervalMillis(minIntervalMs)
            .setWaitForAccurateLocation(false)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { trySend(it) }
            }
        }

        fusedClient.requestLocationUpdates(request, callback, Looper.getMainLooper())
        awaitClose { fusedClient.removeLocationUpdates(callback) }
    }

    /**
     * One-shot best available location (non-null or null if unavailable).
     */
    @SuppressLint("MissingPermission")
    suspend fun lastLocation(): Location? = suspendCancellableCoroutine { cont ->
        val cts = CancellationTokenSource()
        fusedClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, cts.token)
            .addOnSuccessListener { cont.resume(it) }
            .addOnFailureListener { cont.resumeWithException(it) }
        cont.invokeOnCancellation { cts.cancel() }
    }

    companion object {
        /** Driver GPS upload cadence (3 s) */
        const val DRIVER_INTERVAL_MS = 3_000L

        /** Student passive refresh cadence (5 s) */
        const val STUDENT_INTERVAL_MS = 5_000L
    }
}
