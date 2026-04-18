package com.routex.app.student.domain.model

data class BusStop(
    val id: String = "",
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val arrivalTime: String = "",
    val sequence: Int = 0,
)
