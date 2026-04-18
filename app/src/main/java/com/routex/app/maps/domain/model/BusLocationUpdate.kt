package com.routex.app.maps.domain.model

data class BusLocationUpdate(
    val routeId: String = "",
    val busNumber: String = "",
    val location: MapLocation = MapLocation(),
    val speed: Float = 0f,
    val heading: Float = 0f,
    val isOnline: Boolean = true,
    val lastUpdated: Long = System.currentTimeMillis(),
)
