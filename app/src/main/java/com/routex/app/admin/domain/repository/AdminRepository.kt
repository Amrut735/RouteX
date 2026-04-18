package com.routex.app.admin.domain.repository

import com.routex.app.admin.domain.model.AnalyticsSnapshot
import com.routex.app.admin.domain.model.Bus
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.model.Driver
import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AdminRepository {

    // ── Routes ────────────────────────────────────────────────────────────────
    fun getAllRoutes(): Flow<Resource<List<BusRoute>>>
    suspend fun addRoute(route: BusRoute): Resource<BusRoute>
    suspend fun updateRoute(route: BusRoute): Resource<Unit>
    suspend fun deleteRoute(routeId: String): Resource<Unit>

    // ── Buses ─────────────────────────────────────────────────────────────────
    fun getAllBuses(): Flow<Resource<List<Bus>>>
    suspend fun addBus(bus: Bus): Resource<Bus>
    suspend fun updateBus(bus: Bus): Resource<Unit>
    suspend fun deleteBus(busId: String): Resource<Unit>
    suspend fun assignBusToRoute(busId: String, routeId: String, driverId: String): Resource<Unit>
    suspend fun getBusById(busId: String): Resource<Bus>

    // ── Drivers ───────────────────────────────────────────────────────────────
    fun getAllDrivers(): Flow<Resource<List<Driver>>>
    suspend fun addDriver(driver: Driver): Resource<Driver>
    suspend fun updateDriver(driver: Driver): Resource<Unit>
    suspend fun deleteDriver(driverId: String): Resource<Unit>
    suspend fun getDriverByAuthUid(uid: String): Resource<Driver>
    suspend fun assignDriver(driverId: String, busId: String, routeId: String): Resource<Unit>
    suspend fun seedDummyDriversIfEmpty()
    suspend fun seedBusesIfEmpty()
    suspend fun seedRoutesIfEmpty()

    // ── Emergency Alerts ──────────────────────────────────────────────────────
    suspend fun sendEmergencyAlert(alert: EmergencyAlert): Resource<Unit>
    fun observeActiveAlerts(): Flow<List<EmergencyAlert>>
    suspend fun resolveAlert(alertId: String): Resource<Unit>

    // ── Analytics ─────────────────────────────────────────────────────────────
    suspend fun getAnalyticsSnapshot(): Resource<AnalyticsSnapshot>

    // ── Dashboard Stats ───────────────────────────────────────────────────────
    suspend fun getDashboardStats(): Resource<AdminStats>
}

data class AdminStats(
    val totalRoutes: Int = 0,
    val activeRoutes: Int = 0,
    val totalStudents: Int = 0,
    val totalDrivers: Int = 0,
    val totalBuses: Int = 0,
    val activeAlertsCount: Int = 0,
)
