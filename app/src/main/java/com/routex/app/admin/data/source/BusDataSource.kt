package com.routex.app.admin.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.admin.domain.model.Bus
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    private val col get() = firestore.collection("buses")

    fun getAllBuses(): Flow<Resource<List<Bus>>> = callbackFlow {
        trySend(Resource.Loading)
        val sub = col.orderBy("number").addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Resource.Error(error.message ?: "Failed to load buses"))
                return@addSnapshotListener
            }
            val buses = snapshot?.documents?.mapNotNull { doc ->
                runCatching {
                    Bus(
                        id                = doc.id,
                        number            = doc.getString("number").orEmpty(),
                        model             = doc.getString("model").orEmpty(),
                        capacity          = doc.getLong("capacity")?.toInt() ?: 40,
                        licensePlate      = doc.getString("licensePlate").orEmpty(),
                        assignedRouteId   = doc.getString("assignedRouteId").orEmpty(),
                        assignedDriverId  = doc.getString("assignedDriverId").orEmpty(),
                        isActive          = doc.getBoolean("isActive") ?: true,
                        yearOfManufacture = doc.getLong("yearOfManufacture")?.toInt() ?: 2020,
                        lastServiceDate   = doc.getString("lastServiceDate").orEmpty(),
                        createdAt         = doc.getLong("createdAt") ?: 0,
                    )
                }.getOrNull()
            } ?: emptyList()
            trySend(Resource.Success(buses))
        }
        awaitClose { sub.remove() }
    }

    suspend fun addBus(bus: Bus): Resource<Bus> = runCatching {
        val ref    = if (bus.id.isBlank()) col.document() else col.document(bus.id)
        val newBus = bus.copy(id = ref.id)
        ref.set(newBus.toMap()).await()
        newBus
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to add bus") },
    )

    suspend fun updateBus(bus: Bus): Resource<Unit> = runCatching {
        col.document(bus.id).set(bus.toMap()).await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to update bus") },
    )

    suspend fun deleteBus(busId: String): Resource<Unit> = runCatching {
        col.document(busId).delete().await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to delete bus") },
    )

    suspend fun getBusById(busId: String): Resource<Bus> = runCatching {
        val doc = col.document(busId).get().await()
        if (!doc.exists()) throw Exception("Bus not found")
        Bus(
            id                = doc.id,
            number            = doc.getString("number").orEmpty(),
            model             = doc.getString("model").orEmpty(),
            capacity          = doc.getLong("capacity")?.toInt() ?: 40,
            licensePlate      = doc.getString("licensePlate").orEmpty(),
            assignedRouteId   = doc.getString("assignedRouteId").orEmpty(),
            assignedDriverId  = doc.getString("assignedDriverId").orEmpty(),
            isActive          = doc.getBoolean("isActive") ?: true,
            yearOfManufacture = doc.getLong("yearOfManufacture")?.toInt() ?: 2020,
            lastServiceDate   = doc.getString("lastServiceDate").orEmpty(),
            createdAt         = doc.getLong("createdAt") ?: 0,
        )
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to fetch bus") },
    )

    /**
     * Seeds 10 pre-built buses. Merge logic — only inserts buses whose
     * busNumber is not already in Firestore.
     */
    suspend fun seedBusesIfEmpty() {
        runCatching {
            val existing = col.get().await()
            val existingNumbers = existing.documents
                .mapNotNull { it.getString("number") }
                .toSet()

            val seedNumbers = (1..10).map { "KA22H100$it".let { n ->
                if (it < 10) "KA22H100$it" else "KA22H1010" } }
            val missing = seedNumbers.filter { it !in existingNumbers }
            if (missing.isEmpty()) return

            val batch = firestore.batch()
            missing.forEach { number ->
                val ref = col.document()
                batch.set(ref, mapOf(
                    "id"               to ref.id,
                    "number"           to number,
                    "model"            to "Tata",
                    "capacity"         to 40,
                    "licensePlate"     to number,
                    "assignedRouteId"  to "",
                    "assignedDriverId" to "",
                    "isActive"         to true,
                    "yearOfManufacture" to 2022,
                    "lastServiceDate"  to "",
                    "createdAt"        to System.currentTimeMillis(),
                ))
            }
            batch.commit().await()
        }
    }

    /** Atomic batch: update bus, route, and driver assignment in one commit. */
    suspend fun assignBusToRoute(
        busId: String,
        routeId: String,
        driverId: String,
    ): Resource<Unit> = runCatching {
        val batch = firestore.batch()
        batch.update(col.document(busId), mapOf(
            "assignedRouteId"  to routeId,
            "assignedDriverId" to driverId,
        ))
        if (routeId.isNotBlank()) {
            batch.update(firestore.collection("routes").document(routeId), mapOf(
                "busId"    to busId,
                "driverId" to driverId,
            ))
        }
        if (driverId.isNotBlank()) {
            batch.update(firestore.collection("drivers").document(driverId), mapOf(
                "assignedRouteId" to routeId,
                "assignedBusId"   to busId,
            ))
        }
        batch.commit().await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Assignment failed") },
    )

    private fun Bus.toMap(): Map<String, Any?> = mapOf(
        "id" to id, "number" to number, "model" to model, "capacity" to capacity,
        "licensePlate" to licensePlate, "assignedRouteId" to assignedRouteId,
        "assignedDriverId" to assignedDriverId, "isActive" to isActive,
        "yearOfManufacture" to yearOfManufacture, "lastServiceDate" to lastServiceDate,
        "createdAt" to createdAt,
    )
}
