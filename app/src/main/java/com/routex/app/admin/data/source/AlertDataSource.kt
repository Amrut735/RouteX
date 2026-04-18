package com.routex.app.admin.data.source

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.admin.domain.model.EmergencyType
import com.routex.app.core.notification.NotificationHelper
import com.routex.app.core.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlertDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val notificationHelper: NotificationHelper,
) {
    private val col get() = firestore.collection("emergency_alerts")

    suspend fun sendEmergencyAlert(alert: EmergencyAlert): Resource<Unit> = runCatching {
        val ref      = col.document()
        val newAlert = alert.copy(id = ref.id)
        ref.set(newAlert.toMap()).await()
        notificationHelper.showEtaAlert(
            routeId = alert.routeId,
            title   = "${alert.type.emoji} ${alert.type.label} — Bus ${alert.busNumber}",
            body    = alert.message,
        )
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to send alert") },
    )

    fun observeActiveAlerts(): Flow<List<EmergencyAlert>> = callbackFlow {
        val sub = col
            .whereEqualTo("isActive", true)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(20)
            .addSnapshotListener { snapshot, _ ->
                val alerts = snapshot?.documents?.mapNotNull { doc ->
                    runCatching {
                        EmergencyAlert(
                            id        = doc.id,
                            routeId   = doc.getString("routeId").orEmpty(),
                            busNumber = doc.getString("busNumber").orEmpty(),
                            message   = doc.getString("message").orEmpty(),
                            type      = EmergencyType.valueOf(doc.getString("type") ?: "GENERAL"),
                            sentBy    = doc.getString("sentBy").orEmpty(),
                            timestamp = doc.getLong("timestamp") ?: 0,
                            isActive  = doc.getBoolean("isActive") ?: true,
                        )
                    }.getOrNull()
                } ?: emptyList()
                trySend(alerts)
            }
        awaitClose { sub.remove() }
    }

    suspend fun resolveAlert(alertId: String): Resource<Unit> = runCatching {
        col.document(alertId).update("isActive", false).await()
    }.fold(
        onSuccess = { Resource.Success(Unit) },
        onFailure = { Resource.Error(it.message ?: "Failed to resolve alert") },
    )

    private fun EmergencyAlert.toMap(): Map<String, Any?> = mapOf(
        "id" to id, "routeId" to routeId, "busNumber" to busNumber,
        "message" to message, "type" to type.name, "sentBy" to sentBy,
        "timestamp" to timestamp, "isActive" to isActive,
    )
}
