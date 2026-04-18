package com.routex.app.core.location

/**
 * Central hub: KLS Gogte Institute of Technology, Belagavi, Karnataka.
 *
 * Acts as:
 *  - The fixed final destination for every route.
 *  - The geofence center whose entry marks a trip COMPLETED.
 *  - The admin base map centre.
 *
 * Coordinates: 15.8149° N, 74.4872° E  (Requested)
 */
object CollegeHub {

    const val NAME        = "KLS Gogte Institute of Technology"
    const val SHORT_NAME  = "KLS GIT"
    const val LATITUDE    = 15.8149
    const val LONGITUDE   = 74.4872

    /**
     * Radius (metres) of the campus arrival geofence.
     * Intentionally wider than bus-stop geofences (200 m) so the trip is
     * completed just as the bus turns into the campus gate.
     */
    const val GEOFENCE_RADIUS_M = 300f

    /**
     * Unique requestId used inside the GeofencingClient and as a prefix for
     * detecting campus-specific events in the broadcast receiver.
     * Format mirrors the bus-stop format:  "id|lat|lng|name"
     */
    const val GEOFENCE_REQUEST_ID =
        "campus_git|$LATITUDE|$LONGITUDE|$SHORT_NAME"

    /** Distance in metres within which we show "Approaching campus" in the UI. */
    const val APPROACHING_RADIUS_M = 1_000f

    /** Distance in metres at which admin dashboard flags a bus as "near campus". */
    const val NEAR_CAMPUS_RADIUS_M = 1_500f
}
