package com.routex.app.maps.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.model.MapLocation
import com.routex.app.maps.domain.repository.MapsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

private const val BUS_LOCATIONS_NODE = "bus_locations"

@Singleton
class MapsRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
) : MapsRepository {

    private fun busRef(routeId: String) =
        database.getReference("$BUS_LOCATIONS_NODE/$routeId")

    // ── Student: observe single bus ─────────────────────────────────────────

    override fun getBusLocation(routeId: String): Flow<BusLocationUpdate?> = callbackFlow {
        val ref = busRef(routeId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot.toBusUpdate(routeId))
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(null)
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // ── Admin/all buses ─────────────────────────────────────────────────────

    override fun getAllActiveBusLocations(): Flow<List<BusLocationUpdate>> = callbackFlow {
        val ref = database.getReference(BUS_LOCATIONS_NODE)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val updates = snapshot.children.mapNotNull { child ->
                    child.toBusUpdate(child.key ?: "")
                        ?.takeIf { it.isOnline }
                }
                trySend(updates)
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(emptyList())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // ── Driver: write location ───────────────────────────────────────────────

    override suspend fun uploadDriverLocation(
        routeId: String,
        update: BusLocationUpdate,
    ) {
        busRef(routeId).setValue(update.toRtdbMap()).await()
    }

    override suspend fun setDriverOnlineStatus(routeId: String, isOnline: Boolean) {
        busRef(routeId).child("isOnline").setValue(isOnline).await()
        busRef(routeId).child("lastUpdated").setValue(System.currentTimeMillis()).await()
    }

    // ── Mapping helpers ──────────────────────────────────────────────────────

    private fun DataSnapshot.toBusUpdate(routeId: String): BusLocationUpdate? {
        if (!exists()) return null
        return runCatching {
            BusLocationUpdate(
                routeId     = routeId,
                busNumber   = child("busNumber").getValue<String>().orEmpty(),
                location    = MapLocation(
                    latitude  = child("latitude").getValue<Double>() ?: 0.0,
                    longitude = child("longitude").getValue<Double>() ?: 0.0,
                    accuracy  = child("accuracy").getValue<Double>()?.toFloat() ?: 0f,
                ),
                speed       = child("speed").getValue<Double>()?.toFloat() ?: 0f,
                heading     = child("heading").getValue<Double>()?.toFloat() ?: 0f,
                isOnline    = child("isOnline").getValue<Boolean>() ?: false,
                lastUpdated = child("lastUpdated").getValue<Long>() ?: System.currentTimeMillis(),
            )
        }.getOrNull()
    }

    private fun BusLocationUpdate.toRtdbMap(): Map<String, Any?> = mapOf(
        "busNumber"   to busNumber,
        "latitude"    to location.latitude,
        "longitude"   to location.longitude,
        "accuracy"    to location.accuracy,
        "speed"       to speed,
        "heading"     to heading,
        "isOnline"    to isOnline,
        "lastUpdated" to System.currentTimeMillis(),
    )
}
