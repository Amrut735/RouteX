package com.routex.app.trips.domain.repository

import com.routex.app.core.utils.Resource
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import kotlinx.coroutines.flow.Flow

interface TripRepository {

    /** Creates a new trip document in Firestore and returns it (with generated ID). */
    suspend fun startTrip(trip: Trip): Resource<Trip>

    /** Sets status = COMPLETED and records endTime. */
    suspend fun endTrip(tripId: String): Resource<Unit>

    /** Updates only the status field — used for auto-delay detection. */
    suspend fun updateTripStatus(tripId: String, status: TripStatus): Resource<Unit>

    /** Real-time observer for a single trip (driver / student view). */
    fun observeTrip(tripId: String): Flow<Trip?>

    /**
     * Returns the active (RUNNING or DELAYED) trip for a given route, or null.
     * Used by the student map screen to show the live trip status.
     */
    fun observeActiveTripForRoute(routeId: String): Flow<Trip?>

    /** Real-time observer for all trips — used by the admin dashboard. */
    fun observeAllTrips(): Flow<Resource<List<Trip>>>

    /**
     * Cursor-based paginated fetch of trips ordered by [startTime] descending.
     * Pass [afterDocumentId] to start after a specific document (second page, etc.).
     * Returns at most [pageSize] trips.
     */
    suspend fun getPagedTrips(
        afterDocumentId: String? = null,
        pageSize: Int = PAGE_SIZE,
    ): Resource<List<Trip>>

    companion object {
        const val PAGE_SIZE = 20
    }
}
