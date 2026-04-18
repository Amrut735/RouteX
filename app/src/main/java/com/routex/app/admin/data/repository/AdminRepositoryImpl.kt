package com.routex.app.admin.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.admin.data.source.AlertDataSource
import com.routex.app.admin.data.source.BusDataSource
import com.routex.app.admin.data.source.DriverDataSource
import com.routex.app.admin.data.source.RouteAdminDataSource
import com.routex.app.admin.domain.model.AnalyticsSnapshot
import com.routex.app.admin.domain.model.Bus
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.model.Driver
import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.admin.domain.model.HourlyActivity
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.admin.domain.repository.AdminStats
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Thin orchestration layer that delegates to focused data sources.
 * Each data source owns a single Firestore collection, keeping this
 * class under 60 lines and each source easily unit-testable.
 */
@Singleton
class AdminRepositoryImpl @Inject constructor(
    private val routeSource:  RouteAdminDataSource,
    private val busSource:    BusDataSource,
    private val driverSource: DriverDataSource,
    private val alertSource:  AlertDataSource,
    private val firestore:    FirebaseFirestore,
) : AdminRepository {

    // ── Routes ────────────────────────────────────────────────────────────────
    override fun getAllRoutes() = routeSource.getAllRoutes()
    override suspend fun addRoute(route: BusRoute)           = routeSource.addRoute(route)
    override suspend fun updateRoute(route: BusRoute)        = routeSource.updateRoute(route)
    override suspend fun deleteRoute(routeId: String)        = routeSource.deleteRoute(routeId)

    // ── Buses ─────────────────────────────────────────────────────────────────
    override fun getAllBuses() = busSource.getAllBuses()
    override suspend fun addBus(bus: Bus)                    = busSource.addBus(bus)
    override suspend fun updateBus(bus: Bus)                 = busSource.updateBus(bus)
    override suspend fun deleteBus(busId: String)            = busSource.deleteBus(busId)
    override suspend fun assignBusToRoute(
        busId: String, routeId: String, driverId: String,
    ) = busSource.assignBusToRoute(busId, routeId, driverId)
    override suspend fun getBusById(busId: String)           = busSource.getBusById(busId)

    // ── Drivers ───────────────────────────────────────────────────────────────
    override fun getAllDrivers() = driverSource.getAllDrivers()
    override suspend fun addDriver(driver: Driver)           = driverSource.addDriver(driver)
    override suspend fun updateDriver(driver: Driver)        = driverSource.updateDriver(driver)
    override suspend fun deleteDriver(driverId: String)      = driverSource.deleteDriver(driverId)
    override suspend fun getDriverByAuthUid(uid: String)     = driverSource.getDriverByAuthUid(uid)
    override suspend fun seedDummyDriversIfEmpty()           = driverSource.seedDummyDriversIfEmpty()
    override suspend fun seedBusesIfEmpty()                  = busSource.seedBusesIfEmpty()
    override suspend fun seedRoutesIfEmpty()                 = routeSource.seedRoutesIfEmpty()

    /** Atomic: update driver + bus fields in a single batch commit. */
    override suspend fun assignDriver(
        driverId: String, busId: String, routeId: String,
    ): Resource<Unit> = runCatching {
        val batch = firestore.batch()
        // Update driver record
        batch.update(firestore.collection("drivers").document(driverId), mapOf(
            "assignedBusId"   to busId,
            "assignedRouteId" to routeId,
        ))
        // Update bus to reflect assigned driver
        if (busId.isNotBlank()) {
            batch.update(firestore.collection("buses").document(busId), mapOf(
                "assignedDriverId" to driverId,
                "assignedRouteId"  to routeId,
            ))
        }
        batch.commit().await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Assignment failed") },
    )

    // ── Emergency Alerts ──────────────────────────────────────────────────────
    override suspend fun sendEmergencyAlert(alert: EmergencyAlert) =
        alertSource.sendEmergencyAlert(alert)
    override fun observeActiveAlerts(): Flow<List<EmergencyAlert>> =
        alertSource.observeActiveAlerts()
    override suspend fun resolveAlert(alertId: String)       = alertSource.resolveAlert(alertId)

    // ── Analytics ─────────────────────────────────────────────────────────────
    override suspend fun getAnalyticsSnapshot(): Resource<AnalyticsSnapshot> = runCatching {
        val subscriptions = firestore.collection("subscriptions")
            .whereEqualTo("active", true).get().await()
        val routes  = firestore.collection("routes").get().await()
        val drivers = firestore.collection("drivers").get().await()
        val alerts  = firestore.collection("emergency_alerts")
            .whereEqualTo("isActive", true).get().await()

        val routeLoadMap = mutableMapOf<String, Int>()
        subscriptions.documents.forEach { doc ->
            val rId = doc.getString("routeId") ?: return@forEach
            routeLoadMap[rId] = (routeLoadMap[rId] ?: 0) + 1
        }
        val routeNameMap  = routes.documents.associate { it.id to (it.getString("routeName") ?: it.id) }
        val namedLoadMap  = routeLoadMap.entries.associate { (k, v) -> (routeNameMap[k] ?: k) to v }
        val currentHour   = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        AnalyticsSnapshot(
            totalTripsToday       = routes.size() * 2,
            activeDriversNow      = drivers.count { it.getBoolean("isActive") == true },
            avgSpeedKmh           = 28f,
            totalDistanceTodayKm  = routes.sumOf { it.getDouble("totalDistance") ?: 0.0 } * 2,
            alertsSentToday       = alerts.size(),
            onTimePercentage      = 82f,
            peakHour              = peakHourLabel(currentHour),
            routeLoadMap          = namedLoadMap,
            hourlyActivity        = buildHourlyActivity(routes.size()),
        )
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to load analytics") },
    )

    // ── Dashboard Stats ───────────────────────────────────────────────────────
    override suspend fun getDashboardStats(): Resource<AdminStats> = runCatching {
        val routes  = firestore.collection("routes").get().await()
        val users   = firestore.collection("users").whereEqualTo("role", "student").get().await()
        val drivers = firestore.collection("drivers").get().await()
        val buses   = firestore.collection("buses").get().await()
        val alerts  = firestore.collection("emergency_alerts")
            .whereEqualTo("isActive", true).get().await()
        AdminStats(
            totalRoutes       = routes.size(),
            activeRoutes      = routes.count { it.getBoolean("isActive") == true },
            totalStudents     = users.size(),
            totalDrivers      = drivers.size(),
            totalBuses        = buses.size(),
            activeAlertsCount = alerts.size(),
        )
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to load stats") },
    )

    // ── Private helpers ───────────────────────────────────────────────────────

    private fun peakHourLabel(hour: Int): String = when (hour) {
        in 7..9   -> "Morning Rush (7–9 AM)"
        in 17..19 -> "Evening Rush (5–7 PM)"
        else      -> "Off-Peak"
    }

    private fun buildHourlyActivity(routeCount: Int): List<HourlyActivity> =
        (6..22).map { h ->
            HourlyActivity(
                hour         = h,
                tripCount    = routeCount * (if (h in 7..9 || h in 17..19) 2 else 1),
                avgOccupancy = (20..35).random(),
            )
        }
}
