package com.routex.app.trips.domain.model

/**
 * Represents a single bus trip lifecycle.
 *
 * Firebase Firestore schema  →  trips/{tripId}:
 *   busId        : String
 *   busNumber    : String
 *   driverId     : String
 *   driverName   : String
 *   routeId      : String
 *   routeName    : String
 *   status       : "not_started" | "running" | "delayed" | "completed"
 *   startTime    : Long   (epoch millis, 0 before start)
 *   endTime      : Long   (epoch millis, 0 before end)
 *   lastUpdated  : Long
 */
data class Trip(
    val id: String = "",
    val busId: String = "",
    val busNumber: String = "",
    val driverId: String = "",
    val driverName: String = "",
    val routeId: String = "",
    val routeName: String = "",
    val status: TripStatus = TripStatus.NOT_STARTED,
    val startTime: Long = 0L,
    val endTime: Long = 0L,
    val lastUpdated: Long = System.currentTimeMillis(),
) {
    val isActive: Boolean
        get() = status == TripStatus.RUNNING || status == TripStatus.DELAYED

    /** Duration in minutes; 0 if the trip has not ended yet. */
    val durationMinutes: Long
        get() = if (startTime > 0L && endTime > 0L) (endTime - startTime) / 60_000L else 0L

    fun toMap(): Map<String, Any> = mapOf(
        "busId"       to busId,
        "busNumber"   to busNumber,
        "driverId"    to driverId,
        "driverName"  to driverName,
        "routeId"     to routeId,
        "routeName"   to routeName,
        "status"      to status.name.lowercase(),
        "startTime"   to startTime,
        "endTime"     to endTime,
        "lastUpdated" to lastUpdated,
    )
}
