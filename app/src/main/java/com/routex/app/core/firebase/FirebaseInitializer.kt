package com.routex.app.core.firebase

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * One-time setup helpers called from the application or an admin screen.
 *
 * Responsibilities:
 *  - Enable RTDB offline persistence (already done in AppModule, referenced here for clarity)
 *  - Seed a demo route + bus_location node so the map has data to display immediately
 */
@Singleton
class FirebaseInitializer @Inject constructor(
    private val db: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
) {
    /**
     * Seeds a demo route into Firestore (idempotent — uses merge).
     * Call once from an admin screen or remove once real data exists.
     */
    suspend fun seedDemoRoute() {
        val routeId = "route_demo_01"

        val stops = listOf(
            mapOf(
                "id" to "stop_01", "name" to "Main Gate",
                "latitude" to 12.9716, "longitude" to 77.5946,
                "arrivalTime" to "8:00 AM", "sequence" to 1,
            ),
            mapOf(
                "id" to "stop_02", "name" to "Library Block",
                "latitude" to 12.9720, "longitude" to 77.5952,
                "arrivalTime" to "8:05 AM", "sequence" to 2,
            ),
            mapOf(
                "id" to "stop_03", "name" to "Cafeteria",
                "latitude" to 12.9730, "longitude" to 77.5960,
                "arrivalTime" to "8:10 AM", "sequence" to 3,
            ),
            mapOf(
                "id" to "stop_04", "name" to "Sports Complex",
                "latitude" to 12.9740, "longitude" to 77.5970,
                "arrivalTime" to "8:15 AM", "sequence" to 4,
            ),
        )

        val routeData = mapOf(
            "routeNumber"       to "R01",
            "routeName"         to "Campus Circular",
            "startPoint"        to "Main Gate",
            "endPoint"          to "Sports Complex",
            "busNumber"         to "BUS-01",
            "driverName"        to "Demo Driver",
            "isActive"          to true,
            "scheduleTime"      to "8:00 AM",
            "totalDistance"     to 3.2,
            "estimatedDuration" to 20,
            "stops"             to stops,
        )

        firestore.collection("routes").document(routeId)
            .set(routeData, SetOptions.merge())
            .await()

        // Seed an initial RTDB bus_location so the map marker is visible immediately
        db.getReference("bus_locations/$routeId").setValue(
            mapOf(
                "busNumber"   to "BUS-01",
                "latitude"    to 12.9716,
                "longitude"   to 77.5946,
                "accuracy"    to 5.0,
                "speed"       to 0.0,
                "heading"     to 0.0,
                "isOnline"    to false,
                "lastUpdated" to ServerValue.TIMESTAMP,
            ),
        ).await()
    }

    /**
     * Returns the RTDB server timestamp map — use wherever you need server-side time.
     */
    companion object {
        @JvmStatic
        fun serverTimestamp(): Map<String, String> = ServerValue.TIMESTAMP
    }
}
