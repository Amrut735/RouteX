package com.routex.app.admin.domain.usecase

import com.routex.app.admin.domain.model.Bus
import com.routex.app.admin.domain.model.Driver
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBusesUseCase @Inject constructor(private val repo: AdminRepository) {
    operator fun invoke(): Flow<Resource<List<Bus>>> = repo.getAllBuses()
}

class AddBusUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(bus: Bus): Resource<Bus> = repo.addBus(bus)
}

class UpdateBusUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(bus: Bus): Resource<Unit> = repo.updateBus(bus)
}

class DeleteBusUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(busId: String): Resource<Unit> = repo.deleteBus(busId)
}

class AssignBusUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(busId: String, routeId: String, driverId: String): Resource<Unit> =
        repo.assignBusToRoute(busId, routeId, driverId)
}

class AddDriverUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(driver: Driver): Resource<Driver> = repo.addDriver(driver)
}

class UpdateDriverUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(driver: Driver): Resource<Unit> = repo.updateDriver(driver)
}

class DeleteDriverUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(driverId: String): Resource<Unit> = repo.deleteDriver(driverId)
}

class GetAllDriversUseCase @Inject constructor(private val repo: AdminRepository) {
    operator fun invoke(): Flow<Resource<List<Driver>>> = repo.getAllDrivers()
}

class GetDriverByAuthUidUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(uid: String): Resource<Driver> = repo.getDriverByAuthUid(uid)
}

class AssignDriverUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(driverId: String, busId: String, routeId: String): Resource<Unit> =
        repo.assignDriver(driverId, busId, routeId)
}

class SeedDriversUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke() = repo.seedDummyDriversIfEmpty()
}

class SeedBusesUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke() = repo.seedBusesIfEmpty()
}

class SeedRoutesUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke() = repo.seedRoutesIfEmpty()
}

class GetBusByIdUseCase @Inject constructor(private val repo: AdminRepository) {
    suspend operator fun invoke(busId: String): Resource<Bus> = repo.getBusById(busId)
}
