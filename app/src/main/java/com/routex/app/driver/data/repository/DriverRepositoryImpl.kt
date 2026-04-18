package com.routex.app.driver.data.repository

import com.routex.app.core.utils.Resource
import com.routex.app.driver.domain.repository.DriverRepository
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.model.MapLocation
import com.routex.app.maps.domain.repository.MapsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DriverRepositoryImpl @Inject constructor(
    private val mapsRepository: MapsRepository,
) : DriverRepository {

    override suspend fun uploadLocation(
        routeId: String,
        latitude: Double,
        longitude: Double,
        speed: Float,
        heading: Float,
        accuracy: Float,
        busNumber: String,
    ): Resource<Unit> = runCatching {
        val update = BusLocationUpdate(
            routeId   = routeId,
            busNumber = busNumber,
            location  = MapLocation(latitude, longitude, accuracy),
            speed     = speed,
            heading   = heading,
            isOnline  = true,
        )
        mapsRepository.uploadDriverLocation(routeId, update)
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Upload failed") },
    )

    override suspend fun setOnlineStatus(routeId: String, isOnline: Boolean): Resource<Unit> =
        runCatching {
            mapsRepository.setDriverOnlineStatus(routeId, isOnline)
        }.fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Failed to set status") },
        )
}
