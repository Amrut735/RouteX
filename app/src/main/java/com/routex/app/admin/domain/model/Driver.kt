package com.routex.app.admin.domain.model

data class Driver(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val driverCode: String = "",
    val phoneNumber: String = "",
    val licenseNumber: String = "",
    val assignedRouteId: String = "",
    val assignedBusId: String = "",
    val authUid: String = "",       // linked after driver self-registers
    val isActive: Boolean = true,
)
