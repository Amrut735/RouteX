package com.routex.app.admin.domain.model

data class EmergencyAlert(
    val id: String = "",
    val routeId: String = "",
    val busNumber: String = "",
    val message: String = "",
    val type: EmergencyType = EmergencyType.GENERAL,
    val sentBy: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isActive: Boolean = true,
)

enum class EmergencyType(val label: String, val emoji: String) {
    BREAKDOWN("Breakdown",     "🔧"),
    ACCIDENT( "Accident",      "⚠️"),
    DELAY(    "Major Delay",   "⏰"),
    FULL(     "Bus Full",      "🚌"),
    GENERAL(  "General Alert", "📢"),
}
