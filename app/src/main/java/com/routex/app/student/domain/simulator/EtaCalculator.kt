package com.routex.app.student.domain.simulator

import com.google.android.gms.maps.model.LatLng
import com.routex.app.maps.domain.model.BusLocationUpdate

object EtaCalculator {

    enum class BusStatus {
        ARRIVING,
        NEAR,
        PASSED
    }

    data class EtaResult(
        val estimationMinutes: Int,
        val status: BusStatus,
        val distanceMeters: Double
    )

    /**
     * Determine if a bus has passed a stop, and if not, its ETA.
     * The bus has "Passed" if its current route sequence > stop sequence,
     * OR if the bus heading is diverging significantly away from the exact location,
     * OR simply if the bus has progressed beyond the total path distance offset of the stop.
     */
    fun calculateEta(
        bus: BusLocationUpdate,
        stopLoc: LatLng,
        avgSpeedMs: Double = 12.0
    ): EtaResult {
        // Direct flight distance 
        val results = FloatArray(1)
        android.location.Location.distanceBetween(
            bus.location.latitude, bus.location.longitude,
            stopLoc.latitude, stopLoc.longitude,
            results
        )
        val dist = results[0].toDouble()

        // Very basic pass logic for mock: Real one uses the Polyline length remainder.
        // For local simulator demo: We consider it 'Passed' if status flags are marked.
        // Let's rely on distance and speed.
        val etaSec = dist / avgSpeedMs
        val etaMin = (etaSec / 60).toInt()

        val status = when {
            dist < 100 -> BusStatus.NEAR
            // Note: Since bidirectional local simulator handles path progress natively, 
            // the 'Passed' logic inside MapViewModel might be cleaner if we use path-distances.
            // For now, if ETA > 0 we say ARRIVING. PASSED is best tracked via sequence index.
            else -> BusStatus.ARRIVING 
        }

        return EtaResult(etaMin, status, dist)
    }
}
