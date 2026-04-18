package com.routex.app.admin.domain.model

/** Aggregated analytics shown on the admin analytics screen. */
data class AnalyticsSnapshot(
    val totalTripsToday: Int = 0,
    val activeDriversNow: Int = 0,
    val avgSpeedKmh: Float = 0f,
    val totalDistanceTodayKm: Double = 0.0,
    val alertsSentToday: Int = 0,
    val onTimePercentage: Float = 0f,
    val peakHour: String = "",
    val routeLoadMap: Map<String, Int> = emptyMap(),   // routeName → subscriber count
    val hourlyActivity: List<HourlyActivity> = emptyList(),
)

data class HourlyActivity(
    val hour: Int = 0,      // 0..23
    val tripCount: Int = 0,
    val avgOccupancy: Int = 0,
)
