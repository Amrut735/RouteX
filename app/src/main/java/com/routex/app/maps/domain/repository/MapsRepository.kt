package com.routex.app.maps.domain.repository

import com.routex.app.maps.domain.model.BusLocationUpdate
import kotlinx.coroutines.flow.Flow

interface MapsRepository {

    /** Real-time bus location for a single route (Firebase RTDB). */
    fun getBusLocation(routeId: String): Flow<BusLocationUpdate?>

    /** Real-time location stream for ALL online buses. */
    fun getAllActiveBusLocations(): Flow<List<BusLocationUpdate>>

    /**
     * Driver: atomically writes the current location + metadata to RTDB.
     * Called from the background service every 3 s.
     */
    suspend fun uploadDriverLocation(routeId: String, update: BusLocationUpdate)

    /** Driver: mark the bus online/offline when the service starts/stops. */
    suspend fun setDriverOnlineStatus(routeId: String, isOnline: Boolean)
}
