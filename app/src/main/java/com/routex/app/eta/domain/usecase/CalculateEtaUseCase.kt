package com.routex.app.eta.domain.usecase

import com.routex.app.eta.domain.model.BusStatus
import com.routex.app.eta.domain.model.EtaResult
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Core ETA calculation engine.
 *
 * Algorithm:
 *   1. Maintain a rolling cache of the last [SPEED_CACHE_SIZE] speed readings
 *      so a momentary stop doesn't drop ETA accuracy.
 *   2. Apply a time-of-day traffic factor (rush hour = slower effective speed).
 *   3. Compute ETA = haversine_distance / effective_speed.
 *   4. Determine [BusStatus] by comparing current ETA to the previous ETA trend,
 *      proximity to stop, and bus speed.
 *
 * This class is **stateful** (holds speed cache + last ETA). It is intentionally
 * *unscoped* so that each injection site (ViewModel) receives its own private
 * instance — eliminating shared mutable state across sessions.
 */
class CalculateEtaUseCase @Inject constructor() {

    private val speedCache = ArrayDeque<Float>(SPEED_CACHE_SIZE)
    private var lastEtaMinutes: Float = Float.MAX_VALUE
    private var stoppedSince: Long    = 0L

    operator fun invoke(
        busLat: Double,
        busLng: Double,
        stopLat: Double,
        stopLng: Double,
        rawSpeedKmh: Float,
        stopName: String,
        isOnline: Boolean,
    ): EtaResult {
        if (!isOnline) {
            return EtaResult(
                etaMinutes       = Float.MAX_VALUE,
                distanceKm       = haversine(busLat, busLng, stopLat, stopLng),
                status           = BusStatus.OFFLINE,
                effectiveSpeedKmh = 0f,
                rawSpeedKmh      = 0f,
                trafficFactor    = 1f,
                stopName         = stopName,
                etaFormatted     = "—",
            )
        }

        // ── Speed cache ──────────────────────────────────────────────────────
        if (rawSpeedKmh > 0.5f) {
            if (speedCache.size >= SPEED_CACHE_SIZE) speedCache.removeFirst()
            speedCache.addLast(rawSpeedKmh)
            stoppedSince = 0L
        } else if (stoppedSince == 0L) {
            stoppedSince = System.currentTimeMillis()
        }

        val avgCachedSpeed = if (speedCache.isEmpty()) 0f
                             else speedCache.average().toFloat()

        // ── Traffic factor ────────────────────────────────────────────────────
        val trafficFactor = trafficFactor()

        // ── Effective speed ───────────────────────────────────────────────────
        // If bus stopped < 90 s, keep cached speed (traffic light / junction).
        // If stopped > 90 s, treat as genuinely delayed.
        val busIsStopped = rawSpeedKmh < 1f &&
            (stoppedSince > 0 && System.currentTimeMillis() - stoppedSince > 90_000)

        val effectiveSpeed = when {
            busIsStopped         -> 0f
            avgCachedSpeed < 1f  -> MIN_SPEED_KMH       // first update, no history yet
            else                 -> avgCachedSpeed
        }

        // ── Distance ─────────────────────────────────────────────────────────
        val distanceKm = haversine(busLat, busLng, stopLat, stopLng)

        // ── ETA ───────────────────────────────────────────────────────────────
        val etaMinutes = when {
            distanceKm < 0.05  -> 0f              // bus is at the stop
            effectiveSpeed < 1f -> {
                // Extrapolate from last known ETA, increasing by delay factor
                if (lastEtaMinutes < Float.MAX_VALUE) (lastEtaMinutes + STOP_DELAY_INCREMENT_MIN)
                else Float.MAX_VALUE
            }
            else -> (distanceKm.toFloat() / effectiveSpeed * 60f * trafficFactor)
        }

        // ── Status ────────────────────────────────────────────────────────────
        val isDelayed = lastEtaMinutes < Float.MAX_VALUE &&
            etaMinutes > lastEtaMinutes + DELAY_THRESHOLD_MIN

        val status = when {
            distanceKm < 0.05  -> BusStatus.ARRIVING
            distanceKm < 0.30  -> BusStatus.APPROACHING
            busIsStopped       -> BusStatus.STOPPED
            isDelayed          -> BusStatus.DELAYED
            else               -> BusStatus.ON_TIME
        }

        lastEtaMinutes = etaMinutes

        val etaFormatted = formatEta(etaMinutes, status)

        return EtaResult(
            etaMinutes        = etaMinutes,
            distanceKm        = distanceKm,
            status            = status,
            effectiveSpeedKmh = effectiveSpeed,
            rawSpeedKmh       = rawSpeedKmh,
            trafficFactor     = trafficFactor,
            stopName          = stopName,
            etaFormatted      = etaFormatted,
        )
    }

    fun reset() {
        speedCache.clear()
        lastEtaMinutes = Float.MAX_VALUE
        stoppedSince   = 0L
    }

    // ── Haversine formula ─────────────────────────────────────────────────────

    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        return 2 * EARTH_RADIUS_KM * asin(sqrt(a))
    }

    // ── Traffic approximation ─────────────────────────────────────────────────

    private fun trafficFactor(): Float {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 7..9   -> 1.45f   // morning rush
            in 17..19 -> 1.55f   // evening rush — worst traffic
            in 12..13 -> 1.20f   // lunch rush
            in 22..23, in 0..5 -> 0.85f  // light traffic
            else               -> 1.10f  // normal daytime
        }
    }

    // ── ETA format ────────────────────────────────────────────────────────────

    private fun formatEta(etaMinutes: Float, status: BusStatus): String = when {
        status == BusStatus.ARRIVING     -> "Arriving"
        status == BusStatus.OFFLINE      -> "—"
        etaMinutes >= Float.MAX_VALUE    -> "Unknown"
        status == BusStatus.STOPPED      -> "${lastEtaMinutes.toInt()}+ min"
        etaMinutes < 1f                  -> "< 1 min"
        etaMinutes < 60f                 -> "${etaMinutes.toInt()} min"
        else -> {
            val h = (etaMinutes / 60).toInt()
            val m = (etaMinutes % 60).toInt()
            "${h}h ${m}m"
        }
    }

    companion object {
        private const val SPEED_CACHE_SIZE         = 6        // ~18–30 s of data at 3 s intervals
        private const val MIN_SPEED_KMH            = 5f       // minimum assumed speed
        private const val DELAY_THRESHOLD_MIN      = 3f       // ETA increased by 3+ min = delayed
        private const val STOP_DELAY_INCREMENT_MIN = 0.5f     // add 30 s per update while stopped
        private const val EARTH_RADIUS_KM          = 6371.0
    }
}
