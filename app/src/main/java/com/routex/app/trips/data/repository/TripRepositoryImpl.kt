package com.routex.app.trips.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.routex.app.core.utils.Resource
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.repository.TripRepository
import com.routex.app.trips.domain.repository.TripRepository.Companion.PAGE_SIZE
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject
import javax.inject.Singleton

private const val TRIPS_COLLECTION = "trips"

@Singleton
class TripRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : TripRepository {

    private val col get() = firestore.collection(TRIPS_COLLECTION)

    // ── Write operations ──────────────────────────────────────────────────────

    override suspend fun startTrip(trip: Trip): Resource<Trip> = runCatching {
        val doc     = col.document()
        val started = trip.copy(
            id          = doc.id,
            status      = TripStatus.RUNNING,
            startTime   = System.currentTimeMillis(),
            lastUpdated = System.currentTimeMillis(),
        )
        doc.set(started.toMap()).await()
        started
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to start trip") },
    )

    override suspend fun endTrip(tripId: String): Resource<Unit> = runCatching {
        col.document(tripId).update(
            mapOf(
                "status"      to TripStatus.COMPLETED.name.lowercase(),
                "endTime"     to System.currentTimeMillis(),
                "lastUpdated" to System.currentTimeMillis(),
            ),
        ).await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to end trip") },
    )

    override suspend fun updateTripStatus(tripId: String, status: TripStatus): Resource<Unit> =
        runCatching {
            col.document(tripId).update(
                mapOf(
                    "status"      to status.name.lowercase(),
                    "lastUpdated" to System.currentTimeMillis(),
                ),
            ).await()
        }.fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Failed to update trip status") },
        )

    // ── Real-time observers ───────────────────────────────────────────────────

    override fun observeTrip(tripId: String): Flow<Trip?> = callbackFlow {
        val reg = col.document(tripId).addSnapshotListener { snap, _ ->
            trySend(snap?.toTrip())
        }
        awaitClose { reg.remove() }
    }

    override fun observeActiveTripForRoute(routeId: String): Flow<Trip?> = callbackFlow {
        val reg = col
            .whereEqualTo("routeId", routeId)
            .whereIn("status", listOf("running", "delayed"))
            .orderBy("startTime", Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { snap, _ ->
                trySend(snap?.documents?.firstOrNull()?.toTrip())
            }
        awaitClose { reg.remove() }
    }

    override fun observeAllTrips(): Flow<Resource<List<Trip>>> = callbackFlow {
        trySend(Resource.Loading)
        val reg = col
            .orderBy("startTime", Query.Direction.DESCENDING)
            .limit(50)
            .addSnapshotListener { snap, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to load trips"))
                    return@addSnapshotListener
                }
                val trips = snap?.documents?.mapNotNull { it.toTrip() } ?: emptyList()
                trySend(Resource.Success(trips))
            }
        awaitClose { reg.remove() }
    }

    // ── Cursor-based pagination ───────────────────────────────────────────────

    override suspend fun getPagedTrips(
        afterDocumentId: String?,
        pageSize: Int,
    ): Resource<List<Trip>> = runCatching {
        var query = col
            .orderBy("startTime", Query.Direction.DESCENDING)
            .limit(pageSize.toLong())

        if (afterDocumentId != null) {
            val cursor: DocumentSnapshot = col.document(afterDocumentId).get().await()
            if (cursor.exists()) query = query.startAfter(cursor)
        }

        query.get().await().documents.mapNotNull { it.toTrip() }
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to load trips") },
    )

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun com.google.firebase.firestore.DocumentSnapshot.toTrip(): Trip? =
        runCatching {
            Trip(
                id          = id,
                busId       = getString("busId").orEmpty(),
                busNumber   = getString("busNumber").orEmpty(),
                driverId    = getString("driverId").orEmpty(),
                driverName  = getString("driverName").orEmpty(),
                routeId     = getString("routeId").orEmpty(),
                routeName   = getString("routeName").orEmpty(),
                status      = TripStatus.fromString(getString("status").orEmpty()),
                startTime   = getLong("startTime") ?: 0L,
                endTime     = getLong("endTime") ?: 0L,
                lastUpdated = getLong("lastUpdated") ?: System.currentTimeMillis(),
            )
        }.getOrNull()
}
