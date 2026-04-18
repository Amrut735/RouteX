package com.routex.app.driver.domain.repository

import com.routex.app.core.utils.Resource

interface DriverRepository {
    /** Write the driver's GPS position to Firebase RTDB. */
    suspend fun uploadLocation(
        routeId: String,
        latitude: Double,
        longitude: Double,
        speed: Float,
        heading: Float,
        accuracy: Float,
        busNumber: String,
    ): Resource<Unit>

    /** Mark the driver's bus as online/offline in RTDB. */
    suspend fun setOnlineStatus(routeId: String, isOnline: Boolean): Resource<Unit>
}
