package com.routex.app.eta.domain.model

/**
 * Represents the operational status of the bus relative to the student's selected stop.
 */
enum class BusStatus(
    val label: String,
    val description: String,
    val colorHex: Long,           // used for status chip background
) {
    ON_TIME(
        label       = "On Time",
        description = "Bus is on schedule",
        colorHex    = 0xFF2ECC71,
    ),
    APPROACHING(
        label       = "Approaching",
        description = "Bus is arriving in under 2 minutes",
        colorHex    = 0xFF3498DB,
    ),
    ARRIVING(
        label       = "Arriving",
        description = "Bus is at your stop now",
        colorHex    = 0xFF9B59B6,
    ),
    DELAYED(
        label       = "Delayed",
        description = "Bus is running behind schedule",
        colorHex    = 0xFFE67E22,
    ),
    STOPPED(
        label       = "Stopped",
        description = "Bus is temporarily stopped",
        colorHex    = 0xFFE74C3C,
    ),
    OFFLINE(
        label       = "Offline",
        description = "Bus tracking unavailable",
        colorHex    = 0xFF95A5A6,
    ),
}
