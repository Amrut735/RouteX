package com.routex.app.maps.domain.usecase

import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.repository.MapsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBusLocationUseCase @Inject constructor(
    private val repository: MapsRepository,
) {
    operator fun invoke(routeId: String): Flow<BusLocationUpdate?> =
        repository.getBusLocation(routeId)

    /** All online buses — used for the "all routes" map view. */
    fun allActive(): Flow<List<BusLocationUpdate>> =
        repository.getAllActiveBusLocations()
}
