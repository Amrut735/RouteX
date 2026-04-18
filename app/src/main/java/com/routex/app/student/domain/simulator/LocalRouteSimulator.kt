package com.routex.app.student.domain.simulator

import com.google.android.gms.maps.model.LatLng
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.model.MapLocation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class LocalRouteSimulator {

    /**
     * Generates a continuous real-time flow of 3 simulated buses moving along the route's stops.
     * The buses move bidirectionally with staggered timings and logical spacing.
     */
    fun simulateBuses(
        routeId: String,
        routeNumber: String,
        stops: List<com.routex.app.student.domain.model.BusStop>
    ): Flow<List<BusLocationUpdate>> = flow {
        if (stops.size < 2) {
            emit(emptyList())
            return@flow
        }

        val sortedStops = stops.sortedBy { it.sequence }
        val pathLatLngs = sortedStops.map { LatLng(it.latitude, it.longitude) }

        // Determine spacing: 3 buses, separated into offsets 0.0, 0.33, 0.66 of the total path length
        val pathDistances = calculatePathDistances(pathLatLngs)
        val totalDistance = pathDistances.last()

        // 3 state objects representing the simulated buses
        val fakeBuses = listOf(
            SimulatedBus(id = "SIM-$routeNumber-1", busNumber = "SIM$routeNumber-1", offsetMeters = 0.0, movingForward = true),
            SimulatedBus(id = "SIM-$routeNumber-2", busNumber = "SIM$routeNumber-2", offsetMeters = totalDistance * 0.33, movingForward = true),
            SimulatedBus(id = "SIM-$routeNumber-3", busNumber = "SIM$routeNumber-3", offsetMeters = totalDistance * 0.66, movingForward = false)
        )

        // Average simulation speed (m/s). 30 km/h = ~8.33 m/s
        val speedMs = 12.0 
        val refreshIntervalMs = 2000L

        while (true) {
            val updates = fakeBuses.map { bus ->
                // Move bus forward or back
                if (bus.movingForward) {
                    bus.offsetMeters += (speedMs * (refreshIntervalMs / 1000.0))
                    if (bus.offsetMeters >= totalDistance) {
                        bus.offsetMeters = totalDistance
                        bus.movingForward = false
                    }
                } else {
                    bus.offsetMeters -= (speedMs * (refreshIntervalMs / 1000.0))
                    if (bus.offsetMeters <= 0.0) {
                        bus.offsetMeters = 0.0
                        bus.movingForward = true
                    }
                }

                val currentPos = interpolatePosition(pathLatLngs, pathDistances, bus.offsetMeters)

                BusLocationUpdate(
                    routeId = routeId,
                    busNumber = bus.busNumber,
                    location = MapLocation(currentPos.latitude, currentPos.longitude, 5f),
                    speed = (speedMs * 3.6).toFloat(), // km/h
                    heading = currentPos.heading,
                    isOnline = true,
                    lastUpdated = System.currentTimeMillis()
                )
            }
            emit(updates)
            delay(refreshIntervalMs)
        }
    }

    private fun calculatePathDistances(points: List<LatLng>): List<Double> {
        val distances = mutableListOf<Double>()
        var acc = 0.0
        distances.add(acc)
        for (i in 0 until points.size - 1) {
            val dist = distanceBetween(points[i], points[i + 1])
            acc += dist
            distances.add(acc)
        }
        return distances
    }

    private fun interpolatePosition(
        points: List<LatLng>,
        distances: List<Double>,
        targetDist: Double
    ): SimulatedPosition {
        if (targetDist <= 0.0) return SimulatedPosition(points.first().latitude, points.first().longitude, calculateHeading(points[0], points[1]))
        if (targetDist >= distances.last()) return SimulatedPosition(points.last().latitude, points.last().longitude, calculateHeading(points[points.size - 2], points.last()))

        for (i in 0 until points.size - 1) {
            val startDist = distances[i]
            val endDist = distances[i + 1]
            if (targetDist in startDist..endDist) {
                val fraction = (targetDist - startDist) / (endDist - startDist)
                val lat = points[i].latitude + (points[i + 1].latitude - points[i].latitude) * fraction
                val lng = points[i].longitude + (points[i + 1].longitude - points[i].longitude) * fraction
                val heading = if (targetDist > 0) calculateHeading(points[i], points[i + 1]) else 0f
                return SimulatedPosition(lat, lng, heading)
            }
        }
        return SimulatedPosition(points.last().latitude, points.last().longitude, 0f)
    }

    private fun distanceBetween(p1: LatLng, p2: LatLng): Double {
        val results = FloatArray(1)
        android.location.Location.distanceBetween(p1.latitude, p1.longitude, p2.latitude, p2.longitude, results)
        return results[0].toDouble()
    }

    private fun calculateHeading(p1: LatLng, p2: LatLng): Float {
        val lat1 = Math.toRadians(p1.latitude)
        val lat2 = Math.toRadians(p2.latitude)
        val lng1 = Math.toRadians(p1.longitude)
        val lng2 = Math.toRadians(p2.longitude)

        val dLng = lng2 - lng1
        val y = sin(dLng) * cos(lat2)
        val x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(dLng)
        var heading = Math.toDegrees(atan2(y, x)).toFloat()
        if (heading < 0) {
            heading += 360f
        }
        return heading
    }

    private data class SimulatedBus(
        val id: String,
        val busNumber: String,
        var offsetMeters: Double,
        var movingForward: Boolean
    )

    private data class SimulatedPosition(
        val latitude: Double,
        val longitude: Double,
        val heading: Float
    )
}
