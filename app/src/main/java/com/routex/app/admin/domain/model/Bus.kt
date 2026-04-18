package com.routex.app.admin.domain.model

data class Bus(
    val id: String = "",
    val number: String = "",
    val model: String = "",
    val capacity: Int = 40,
    val licensePlate: String = "",
    val assignedRouteId: String = "",
    val assignedDriverId: String = "",
    val isActive: Boolean = true,
    val yearOfManufacture: Int = 2020,
    val lastServiceDate: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)
