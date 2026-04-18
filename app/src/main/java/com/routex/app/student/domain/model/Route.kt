package com.routex.app.student.domain.model

data class Route(
    val id: String = "",
    val routeNumber: String = "",
    val routeName: String = "",
    val startPoint: String = "",
    val endPoint: String = "",
    val stops: List<BusStop> = emptyList(),
    val busNumber: String = "",
    val driverName: String = "",
    val isActive: Boolean = true,
    val scheduleTime: String = "",
    val totalDistance: Double = 0.0,
    val estimatedDuration: Int = 0,
)
