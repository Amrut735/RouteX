package com.routex.app.admin.domain.model

import com.routex.app.student.domain.model.BusStop

data class BusRoute(
    val id: String = "",
    val routeNumber: String = "",
    val routeName: String = "",
    val startPoint: String = "",
    val endPoint: String = "",
    val stopNames: List<String> = emptyList(),
    val busNumber: String = "",
    val driverId: String = "",
    val driverName: String = "",
    val isActive: Boolean = true,
    val totalDistance: Double = 0.0,
    val estimatedDuration: Int = 0,
    val scheduleTime: String = "",
    val stops: List<BusStop> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
)
