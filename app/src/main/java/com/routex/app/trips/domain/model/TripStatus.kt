package com.routex.app.trips.domain.model

import androidx.compose.ui.graphics.Color

enum class TripStatus(
    val label: String,
    val description: String,
    val color: Color,
) {
    NOT_STARTED(
        label       = "Not Started",
        description = "Trip has not begun yet",
        color       = Color(0xFF9E9E9E),
    ),
    RUNNING(
        label       = "Running",
        description = "Trip is currently in progress",
        color       = Color(0xFF2ECC71),
    ),
    DELAYED(
        label       = "Delayed",
        description = "Bus has been stationary for too long",
        color       = Color(0xFFE53935),
    ),
    COMPLETED(
        label       = "Completed",
        description = "Trip has been completed",
        color       = Color(0xFF1E88E5),
    );

    companion object {
        fun fromString(value: String): TripStatus = when (value.lowercase().trim()) {
            "running"     -> RUNNING
            "completed"   -> COMPLETED
            "delayed"     -> DELAYED
            else          -> NOT_STARTED
        }
    }
}
