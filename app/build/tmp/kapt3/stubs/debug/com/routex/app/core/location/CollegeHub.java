package com.routex.app.core.location;

/**
 * Central hub: KLS Gogte Institute of Technology, Belagavi, Karnataka.
 *
 * Acts as:
 * - The fixed final destination for every route.
 * - The geofence center whose entry marks a trip COMPLETED.
 * - The admin base map centre.
 *
 * Coordinates: 15.8149° N, 74.4872° E  (Requested)
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/routex/app/core/location/CollegeHub;", "", "()V", "APPROACHING_RADIUS_M", "", "GEOFENCE_RADIUS_M", "GEOFENCE_REQUEST_ID", "", "LATITUDE", "", "LONGITUDE", "NAME", "NEAR_CAMPUS_RADIUS_M", "SHORT_NAME", "app_debug"})
public final class CollegeHub {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "KLS Gogte Institute of Technology";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SHORT_NAME = "KLS GIT";
    public static final double LATITUDE = 15.8149;
    public static final double LONGITUDE = 74.4872;
    
    /**
     * Radius (metres) of the campus arrival geofence.
     * Intentionally wider than bus-stop geofences (200 m) so the trip is
     * completed just as the bus turns into the campus gate.
     */
    public static final float GEOFENCE_RADIUS_M = 300.0F;
    
    /**
     * Unique requestId used inside the GeofencingClient and as a prefix for
     * detecting campus-specific events in the broadcast receiver.
     * Format mirrors the bus-stop format:  "id|lat|lng|name"
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GEOFENCE_REQUEST_ID = "campus_git|15.8149|74.4872|KLS GIT";
    
    /**
     * Distance in metres within which we show "Approaching campus" in the UI.
     */
    public static final float APPROACHING_RADIUS_M = 1000.0F;
    
    /**
     * Distance in metres at which admin dashboard flags a bus as "near campus".
     */
    public static final float NEAR_CAMPUS_RADIUS_M = 1500.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.core.location.CollegeHub INSTANCE = null;
    
    private CollegeHub() {
        super();
    }
}