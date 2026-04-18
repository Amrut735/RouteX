package com.routex.app.eta.domain.model

/**
 * Complete ETA snapshot returned by [CalculateEtaUseCase] for a single bus update.
 */
data class EtaResult(
    /** Minutes until the bus reaches the selected boarding stop (0 = arrived). */
    val etaMinutes: Float,

    /** Straight-line distance in km from the bus to the boarding stop. */
    val distanceKm: Double,

    /** Operational status derived from speed, ETA trend, and proximity. */
    val status: BusStatus,

    /** Effective speed used in this calculation (km/h), after traffic adjustment. */
    val effectiveSpeedKmh: Float,

    /** Raw bus speed from GPS (km/h). */
    val rawSpeedKmh: Float,

    /** Traffic multiplier applied (1.0 = free flow, >1 = slower). */
    val trafficFactor: Float,

    /** Name of the student's selected boarding stop. */
    val stopName: String,

    /** Formatted ETA string, e.g. "4 min" or "Arriving". */
    val etaFormatted: String,

    val timestamp: Long = System.currentTimeMillis(),
) {
    /** True when the bus is within 100 m of the stop. */
    val isAtStop: Boolean get() = distanceKm < 0.1

    /** True when the student should start walking to the stop. */
    val shouldAlert: Boolean get() = etaMinutes in 0f..5f && !isAtStop
}
