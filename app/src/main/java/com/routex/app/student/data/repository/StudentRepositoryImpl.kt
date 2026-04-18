package com.routex.app.student.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.routex.app.core.utils.Resource
import com.routex.app.student.domain.model.BusStop
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.repository.StudentRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

private const val ROUTES_COLLECTION = "routes"

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : StudentRepository {

    override fun getRoutes(): Flow<Resource<List<Route>>> = callbackFlow {
        trySend(Resource.Loading)
        val subscription = firestore.collection(ROUTES_COLLECTION)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(error.message ?: "Failed to load routes"))
                    return@addSnapshotListener
                }
                val routes = snapshot?.documents?.mapNotNull { doc ->
                    doc.toRoute()
                } ?: emptyList()
                trySend(Resource.Success(routes))
            }
        awaitClose { subscription.remove() }
    }

    override suspend fun getRouteById(routeId: String): Resource<Route> = runCatching {
        val snap = firestore.collection(ROUTES_COLLECTION).document(routeId).get().await()
        snap.toRoute() ?: error("Route $routeId not found")
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { Resource.Error(it.message ?: "Failed to load route") },
    )

    @Suppress("UNCHECKED_CAST")
    private fun com.google.firebase.firestore.DocumentSnapshot.toRoute(): Route? {
        if (!exists()) return null
        return try {
            val stopsRaw = get("stops") as? List<Map<String, Any>> ?: emptyList()
            Route(
                id            = id,
                routeNumber   = getString("routeNumber").orEmpty(),
                routeName     = getString("routeName").orEmpty(),
                startPoint    = getString("startPoint").orEmpty(),
                endPoint      = getString("endPoint").orEmpty(),
                busNumber     = getString("busNumber").orEmpty(),
                driverName    = getString("driverName").orEmpty(),
                isActive      = getBoolean("isActive") ?: true,
                scheduleTime  = getString("scheduleTime").orEmpty(),
                totalDistance = getDouble("totalDistance") ?: 0.0,
                estimatedDuration = getLong("estimatedDuration")?.toInt() ?: 0,
                stops         = stopsRaw.map { s ->
                    BusStop(
                        id          = s["id"] as? String ?: "",
                        name        = s["name"] as? String ?: "",
                        latitude    = (s["latitude"] as? Number)?.toDouble() ?: 0.0,
                        longitude   = (s["longitude"] as? Number)?.toDouble() ?: 0.0,
                        arrivalTime = s["arrivalTime"] as? String ?: "",
                        sequence    = (s["sequence"] as? Number)?.toInt() ?: 0,
                    )
                },
            )
        } catch (e: Exception) {
            null
        }
    }
}
