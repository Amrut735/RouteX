package com.routex.app.admin.data.source

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.student.domain.model.BusStop
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouteAdminDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    private val col get() = firestore.collection("routes")

    fun getAllRoutes(): Flow<Resource<List<BusRoute>>> = callbackFlow {
        trySend(Resource.Loading)
        val sub = col.addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(Resource.Error(error.message ?: "Failed to load routes"))
                return@addSnapshotListener
            }
            val routes = snapshot?.documents?.mapNotNull { doc ->
                runCatching {
                    @Suppress("UNCHECKED_CAST")
                    BusRoute(
                        id                = doc.id,
                        routeNumber       = doc.getString("routeNumber").orEmpty(),
                        routeName         = doc.getString("routeName").orEmpty(),
                        startPoint        = doc.getString("startPoint").orEmpty(),
                        endPoint          = doc.getString("endPoint").orEmpty(),
                        busNumber         = doc.getString("busNumber").orEmpty(),
                        driverId          = doc.getString("driverId").orEmpty(),
                        driverName        = doc.getString("driverName").orEmpty(),
                        isActive          = doc.getBoolean("isActive") ?: true,
                        totalDistance     = doc.getDouble("totalDistance") ?: 0.0,
                        estimatedDuration = doc.getLong("estimatedDuration")?.toInt() ?: 0,
                        scheduleTime      = doc.getString("scheduleTime").orEmpty(),
                        stopNames         = (doc.get("stopNames") as? List<String>) ?: emptyList(),
                        stops             = (doc.get("stops") as? List<Map<String, Any>> ?: emptyList()).map { s ->
                            BusStop(
                                id          = s["id"] as? String ?: "",
                                name        = s["name"] as? String ?: "",
                                latitude    = (s["latitude"] as? Number)?.toDouble() ?: 0.0,
                                longitude   = (s["longitude"] as? Number)?.toDouble() ?: 0.0,
                                arrivalTime = s["arrivalTime"] as? String ?: "",
                                sequence    = (s["sequence"] as? Number)?.toInt() ?: 0,
                            )
                        },
                        createdAt         = doc.getLong("createdAt") ?: 0,
                    ).also {
                        Log.d("ROUTE_SYNC", "Loaded route ${doc.id} with ${it.stops.size} stops")
                        if (it.stops.isEmpty()) Log.e("ROUTE_SYNC", "ERROR: Route ${doc.id} has 0 stops!")
                    }
                }.getOrNull()
            } ?: emptyList()
            trySend(Resource.Success(routes))
        }
        awaitClose { sub.remove() }
    }

    suspend fun addRoute(route: BusRoute): Resource<BusRoute> = runCatching {
        val ref = if (route.id.isBlank()) col.document() else col.document(route.id)
        val newRoute = route.copy(id = ref.id)
        ref.set(newRoute.toMap()).await()
        newRoute
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to add route") },
    )

    suspend fun updateRoute(route: BusRoute): Resource<Unit> = runCatching {
        col.document(route.id).set(route.toMap()).await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to update route") },
    )

    suspend fun deleteRoute(routeId: String): Resource<Unit> = runCatching {
        col.document(routeId).delete().await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to delete route") },
    )

    /**
     * Seeds 8 Belagavi college bus routes if they don't already exist.
     * Uses merge logic — checks routeNumber before inserting.
     * Implements intelligent stops spaced logically.
     */
    suspend fun seedRoutesIfEmpty() {
        runCatching {
            val existing = col.get().await()
            val existingNumbers = existing.documents
                .mapNotNull { it.getString("routeNumber") }
                .toSet()

            val predefinedCoords = mapOf(
                "KLS Gogte Institute of Technology" to Pair(15.8149, 74.4872),
                "Sambra" to Pair(15.8576, 74.6200),
                "Udyambag" to Pair(15.8282, 74.4988),
                "Hindwadi" to Pair(15.8465, 74.5097),
                "Angol Road" to Pair(15.8362, 74.5123),
                "Vadgaon" to Pair(15.8365, 74.5323),
                "Shahapur" to Pair(15.8461, 74.5241),
                "Mahesh Nagar" to Pair(15.8410, 74.5300),
                "Govindapura" to Pair(15.8385, 74.5350),
                "CBT" to Pair(15.8569, 74.5161),
                "Raviwar Peth" to Pair(15.8540, 74.5210),
                "Maratha Colony" to Pair(15.8480, 74.5180),
                "Tilakwadi" to Pair(15.8436, 74.5011),
                "Nehru Nagar" to Pair(15.8753, 74.5163),
                "Clock Tower" to Pair(15.8601, 74.5190),
                "Channamma Circle" to Pair(15.8617, 74.5113),
                "Mahantesh Nagar" to Pair(15.8722, 74.5361),
                "Vidya Nagar" to Pair(15.8522, 74.5191),
                "RPD Cross" to Pair(15.8398, 74.4981),
                "Hanuman Nagar" to Pair(15.8732, 74.5211),
                "Kanabargi" to Pair(15.8821, 74.5361),
                "Angol" to Pair(15.8322, 74.5121),
                "Bhagya Nagar" to Pair(15.8415, 74.5085),
                "Bhagya Nagar Cross" to Pair(15.8430, 74.5050),
                "Khanapur" to Pair(15.6322, 74.5211),
                "Khanapur Circle" to Pair(15.6380, 74.5210),
            )

            data class SeedRoute(
                val number: String, val name: String,
                val start: String, val end: String,
                val stops: List<String>, val distance: Double,
                val duration: Int, val schedule: String,
            )

            val seeds = listOf(
                SeedRoute(
                    "R01", "GIT – Sambra",
                    "KLS Gogte Institute of Technology", "Sambra",
                    listOf("KLS Gogte Institute of Technology", "Angol Road", "Hindwadi", "Udyambag", "Sambra"),
                    14.5, 40, "07:30 AM",
                ),
                SeedRoute(
                    "R02", "GIT – Vadgaon via Shahapur",
                    "KLS Gogte Institute of Technology", "Vadgaon",
                    listOf("KLS Gogte Institute of Technology", "Shahapur", "Mahesh Nagar", "Govindapura", "Vadgaon"),
                    12.0, 35, "08:30 AM",
                ),
                SeedRoute(
                    "R03", "GIT – CBT",
                    "KLS Gogte Institute of Technology", "Central Bus Terminal",
                    listOf("KLS Gogte Institute of Technology", "Tilakwadi", "Maratha Colony", "Raviwar Peth", "CBT"),
                    8.5, 25, "01:20 PM",
                ),
                SeedRoute(
                    "R04", "GIT – Nehru Nagar via Channamma Circle",
                    "KLS Gogte Institute of Technology", "Nehru Nagar",
                    listOf("KLS Gogte Institute of Technology", "Tilakwadi", "Channamma Circle", "Clock Tower", "Nehru Nagar"),
                    9.0, 30, "04:00 PM",
                ),
                SeedRoute(
                    "R05", "GIT – Mahantesh Nagar via RPD",
                    "KLS Gogte Institute of Technology", "Mahantesh Nagar",
                    listOf("KLS Gogte Institute of Technology", "RPD Cross", "Vidya Nagar", "Mahantesh Nagar"),
                    7.5, 22, "05:30 PM",
                ),
                SeedRoute(
                    "R06", "GIT – Hanuman Nagar",
                    "KLS Gogte Institute of Technology", "Hanuman Nagar",
                    listOf("KLS Gogte Institute of Technology", "Angol", "Udyambag", "Kanabargi", "Hanuman Nagar"),
                    11.0, 32, "07:30 AM",
                ),
                SeedRoute(
                    "R07", "GIT – Bhagya Nagar",
                    "KLS Gogte Institute of Technology", "Bhagya Nagar",
                    listOf("KLS Gogte Institute of Technology", "Tilakwadi", "Bhagya Nagar Cross", "Bhagya Nagar"),
                    6.5, 20, "05:30 PM",
                ),
                SeedRoute(
                    "R08", "GIT – Khanapur",
                    "KLS Gogte Institute of Technology", "Khanapur",
                    listOf("KLS Gogte Institute of Technology", "Angol Road", "Hindwadi", "Khanapur Circle", "Khanapur"),
                    18.0, 50, "04:00 PM",
                ),
            )

            val missing = seeds.filter { s ->
                val existingDoc = existing.documents.find { it.getString("routeNumber") == s.number }
                existingDoc == null || existingDoc.get("stops") == null
            }
            if (missing.isEmpty()) return

            val batch = firestore.batch()
            missing.forEach { s ->
                val ref = col.document()
                val stopObjects = s.stops.mapIndexed { index, stopName ->
                    val coords = predefinedCoords[stopName] ?: Pair(0.0, 0.0)
                    mapOf(
                        "id" to java.util.UUID.randomUUID().toString(),
                        "name" to stopName,
                        "latitude" to coords.first,
                        "longitude" to coords.second,
                        "sequence" to index,
                        "arrivalTime" to ""
                    )
                }
                
                batch.set(ref, mapOf(
                    "id"                to ref.id,
                    "routeNumber"       to s.number,
                    "routeName"         to s.name,
                    "startPoint"        to s.start,
                    "endPoint"          to s.end,
                    "stopNames"         to s.stops,
                    "stops"             to stopObjects,
                    "busNumber"         to "",
                    "driverId"          to "",
                    "driverName"        to "",
                    "isActive"          to true,
                    "totalDistance"     to s.distance,
                    "estimatedDuration" to s.duration,
                    "scheduleTime"      to s.schedule,
                    "createdAt"         to System.currentTimeMillis(),
                ))
            }
            batch.commit().await()
        }
    }

    private fun BusRoute.toMap(): Map<String, Any?> = mapOf(
        "id" to id, "routeNumber" to routeNumber, "routeName" to routeName,
        "startPoint" to startPoint, "endPoint" to endPoint, "stopNames" to stopNames,
        "stops" to stops.map { s ->
            mapOf(
                "id" to s.id,
                "name" to s.name,
                "latitude" to s.latitude,
                "longitude" to s.longitude,
                "sequence" to s.sequence,
                "arrivalTime" to s.arrivalTime
            )
        },
        "busNumber" to busNumber, "driverId" to driverId, "driverName" to driverName,
        "isActive" to isActive, "totalDistance" to totalDistance,
        "estimatedDuration" to estimatedDuration, "scheduleTime" to scheduleTime,
        "createdAt" to createdAt,
    )
}
