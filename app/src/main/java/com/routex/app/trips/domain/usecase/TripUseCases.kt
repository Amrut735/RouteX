package com.routex.app.trips.domain.usecase

import com.routex.app.core.utils.Resource
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StartTripUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    suspend operator fun invoke(trip: Trip): Resource<Trip> = repository.startTrip(trip)
}

@Singleton
class EndTripUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    suspend operator fun invoke(tripId: String): Resource<Unit> = repository.endTrip(tripId)
}

@Singleton
class UpdateTripStatusUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    suspend operator fun invoke(tripId: String, status: TripStatus): Resource<Unit> =
        repository.updateTripStatus(tripId, status)
}

@Singleton
class ObserveTripUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    operator fun invoke(tripId: String): Flow<Trip?> = repository.observeTrip(tripId)
}

@Singleton
class ObserveActiveTripForRouteUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    operator fun invoke(routeId: String): Flow<Trip?> =
        repository.observeActiveTripForRoute(routeId)
}

@Singleton
class ObserveAllTripsUseCase @Inject constructor(
    private val repository: TripRepository,
) {
    operator fun invoke(): Flow<Resource<List<Trip>>> = repository.observeAllTrips()
}
