package com.routex.app.admin.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.admin.domain.model.Driver
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DriverDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    private val col get() = firestore.collection("drivers")

    fun getAllDrivers(): Flow<Resource<List<Driver>>> = callbackFlow {
        trySend(Resource.Loading)
        val sub = col.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Resource.Error(error.message ?: "Failed to load drivers"))
                return@addSnapshotListener
            }
            val drivers = snapshot?.documents?.mapNotNull { doc ->
                runCatching {
                    Driver(
                        id              = doc.id,
                        name            = doc.getString("name").orEmpty(),
                        email           = doc.getString("email").orEmpty(),
                        driverCode      = doc.getString("driverCode").orEmpty(),
                        phoneNumber     = doc.getString("phoneNumber").orEmpty(),
                        licenseNumber   = doc.getString("licenseNumber").orEmpty(),
                        assignedRouteId = doc.getString("assignedRouteId").orEmpty(),
                        assignedBusId   = doc.getString("assignedBusId").orEmpty(),
                        authUid         = doc.getString("authUid").orEmpty(),
                        isActive        = doc.getBoolean("isActive") ?: true,
                    )
                }.getOrNull()
            } ?: emptyList()
            trySend(Resource.Success(drivers))
        }
        awaitClose { sub.remove() }
    }

    suspend fun addDriver(driver: Driver): Resource<Driver> = runCatching {
        val ref       = if (driver.id.isBlank()) col.document() else col.document(driver.id)
        val newDriver = driver.copy(id = ref.id)
        ref.set(newDriver.toMap()).await()
        newDriver
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to add driver") },
    )

    suspend fun updateDriver(driver: Driver): Resource<Unit> = runCatching {
        col.document(driver.id).set(driver.toMap()).await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to update driver") },
    )

    suspend fun deleteDriver(driverId: String): Resource<Unit> = runCatching {
        col.document(driverId).delete().await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to delete driver") },
    )

    /** Fetch the driver record whose authUid matches the signed-in user. */
    suspend fun getDriverByAuthUid(uid: String): Resource<Driver> = runCatching {
        val snap = col.whereEqualTo("authUid", uid).limit(1).get().await()
        snap.documents.firstOrNull()?.let { doc ->
            Driver(
                id              = doc.id,
                name            = doc.getString("name").orEmpty(),
                email           = doc.getString("email").orEmpty(),
                driverCode      = doc.getString("driverCode").orEmpty(),
                phoneNumber     = doc.getString("phoneNumber").orEmpty(),
                licenseNumber   = doc.getString("licenseNumber").orEmpty(),
                assignedRouteId = doc.getString("assignedRouteId").orEmpty(),
                assignedBusId   = doc.getString("assignedBusId").orEmpty(),
                authUid         = doc.getString("authUid").orEmpty(),
                isActive        = doc.getBoolean("isActive") ?: true,
            )
        } ?: throw Exception("No driver record found for this account")
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Driver record not found") },
    )

    /**
     * Seeds 12 pre-built driver records into Firestore on first launch.
     * Uses MERGE logic — only inserts drivers whose driverCode does not already exist.
     * Existing records are never overwritten.
     */
    suspend fun seedDummyDriversIfEmpty() {
        runCatching {
            val seed = listOf(
                Triple("Driver1",  "driver1@gmail.com",  "DRV001"),
                Triple("Driver2",  "driver2@gmail.com",  "DRV002"),
                Triple("Driver3",  "driver3@gmail.com",  "DRV003"),
                Triple("Driver4",  "driver4@gmail.com",  "DRV004"),
                Triple("Driver5",  "driver5@gmail.com",  "DRV005"),
                Triple("Driver6",  "driver6@gmail.com",  "DRV006"),
                Triple("Driver7",  "driver7@gmail.com",  "DRV007"),
                Triple("Driver8",  "driver8@gmail.com",  "DRV008"),
                Triple("Driver9",  "driver9@gmail.com",  "DRV009"),
                Triple("Driver10", "driver10@gmail.com", "DRV010"),
                Triple("Driver11", "driver11@gmail.com", "DRV011"),
                Triple("Driver12", "driver12@gmail.com", "DRV012"),
            )
            // Fetch all existing driverCodes so we never duplicate
            val existing = col.get().await()
            val existingCodes = existing.documents
                .mapNotNull { it.getString("driverCode") }
                .toSet()

            val missing = seed.filter { (_, _, code) -> code !in existingCodes }
            if (missing.isEmpty()) return

            val batch = firestore.batch()
            missing.forEach { (name, email, code) ->
                val ref = col.document()
                batch.set(ref, mapOf(
                    "id"              to ref.id,
                    "name"            to name,
                    "email"           to email,
                    "driverCode"      to code,
                    "phoneNumber"     to "",
                    "licenseNumber"   to "",
                    "assignedRouteId" to "",
                    "assignedBusId"   to "",
                    "authUid"         to "",
                    "isActive"        to true,
                ))
            }
            batch.commit().await()
        }
    }

    private fun Driver.toMap(): Map<String, Any?> = mapOf(
        "id" to id, "name" to name, "email" to email,
        "driverCode" to driverCode, "phoneNumber" to phoneNumber,
        "licenseNumber" to licenseNumber, "assignedRouteId" to assignedRouteId,
        "assignedBusId" to assignedBusId, "authUid" to authUid,
        "isActive" to isActive,
    )
}
