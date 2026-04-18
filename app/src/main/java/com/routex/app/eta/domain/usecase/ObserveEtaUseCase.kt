package com.routex.app.eta.domain.usecase

import com.routex.app.eta.domain.model.EtaResult
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase
import com.routex.app.student.domain.model.BusStop
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Bridges the live bus location stream from Firebase RTDB with [CalculateEtaUseCase],
 * emitting a fresh [EtaResult] every time the driver uploads a new location.
 */
class ObserveEtaUseCase @Inject constructor(
    private val getBusLocation: GetBusLocationUseCase,
    private val calculateEta: CalculateEtaUseCase,
) {
    /**
     * @param routeId  Firebase RTDB key for this route's bus.
     * @param stop     The student's selected boarding stop.
     */
    operator fun invoke(routeId: String, stop: BusStop): Flow<EtaResult> =
        getBusLocation(routeId)
            .filterNotNull()
            .map { bus ->
                calculateEta(
                    busLat       = bus.location.latitude,
                    busLng       = bus.location.longitude,
                    stopLat      = stop.latitude,
                    stopLng      = stop.longitude,
                    rawSpeedKmh  = bus.speed,
                    stopName     = stop.name,
                    isOnline     = bus.isOnline,
                )
            }
}
