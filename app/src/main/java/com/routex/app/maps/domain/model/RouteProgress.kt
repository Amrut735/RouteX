package com.routex.app.maps.domain.model

import com.google.android.gms.maps.model.LatLng

/**
 * Splits a route into completed (grey) and remaining (blue) polyline segments
 * based on the bus's current position.
 */
data class RouteProgress(
    /** Points from route start → closest segment to bus (rendered grey). */
    val completedPoints: List<LatLng> = emptyList(),

    /** Points from bus position → route end (rendered primary blue). */
    val remainingPoints: List<LatLng> = emptyList(),

    /** Index of the next upcoming stop. */
    val nextStopIndex: Int = 0,

    /** Straight-line distance (metres) to next stop. */
    val distanceToNextStopM: Float = 0f,
)
