package com.routex.app.maps.domain.model

import com.routex.app.student.domain.model.BusStop

enum class GeofenceTransition { ENTER, DWELL, EXIT }

data class GeofenceEvent(
    val stop: BusStop,
    val distanceMeters: Float,
    val transition: GeofenceTransition = GeofenceTransition.ENTER,
    val timestamp: Long = System.currentTimeMillis(),
    /**
     * True when this event is fired by the KLS GIT campus geofence rather
     * than a regular bus-stop geofence. Consumers should show a distinct
     * "Arriving at College" UI and mark the active trip COMPLETED.
     */
    val isCampusEntry: Boolean = false,
)
