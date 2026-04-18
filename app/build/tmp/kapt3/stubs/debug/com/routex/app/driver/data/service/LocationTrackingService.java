package com.routex.app.driver.data.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.routex.app.MainActivity;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.driver.domain.repository.DriverRepository;
import com.routex.app.maps.data.geofencing.GeofenceNotificationHelper;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.repository.TripRepository;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020\bH\u0002J\u0018\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*H\u0002J\b\u0010,\u001a\u00020#H\u0002J\u0014\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00101\u001a\u00020#H\u0016J\"\u00102\u001a\u0002032\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u000203H\u0016J\u0010\u00106\u001a\u00020#2\u0006\u0010$\u001a\u000203H\u0002J\b\u00107\u001a\u00020#H\u0002J\b\u00108\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u00a8\u0006:"}, d2 = {"Lcom/routex/app/driver/data/service/LocationTrackingService;", "Landroid/app/Service;", "()V", "activeBusNumber", "", "activeRouteId", "activeTripId", "campusArrivalFired", "", "driverRepository", "Lcom/routex/app/driver/domain/repository/DriverRepository;", "getDriverRepository", "()Lcom/routex/app/driver/domain/repository/DriverRepository;", "setDriverRepository", "(Lcom/routex/app/driver/domain/repository/DriverRepository;)V", "isCurrentlyDelayed", "lastMovedAtMs", "", "locationProvider", "Lcom/routex/app/core/location/LocationProvider;", "getLocationProvider", "()Lcom/routex/app/core/location/LocationProvider;", "setLocationProvider", "(Lcom/routex/app/core/location/LocationProvider;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "trackingJob", "Lkotlinx/coroutines/Job;", "tripRepository", "Lcom/routex/app/trips/domain/repository/TripRepository;", "getTripRepository", "()Lcom/routex/app/trips/domain/repository/TripRepository;", "setTripRepository", "(Lcom/routex/app/trips/domain/repository/TripRepository;)V", "checkAndUpdateDelayStatus", "", "speedKmh", "", "accuracy", "hasSpeed", "checkCampusArrival", "busLat", "", "busLng", "createNotificationChannel", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onStartCommand", "", "flags", "startId", "refreshNotification", "startForegroundWithNotification", "startTracking", "Companion", "app_debug"})
public final class LocationTrackingService extends android.app.Service {
    @javax.inject.Inject()
    public com.routex.app.core.location.LocationProvider locationProvider;
    @javax.inject.Inject()
    public com.routex.app.driver.domain.repository.DriverRepository driverRepository;
    @javax.inject.Inject()
    public com.routex.app.trips.domain.repository.TripRepository tripRepository;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job trackingJob;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activeRouteId = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activeBusNumber = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activeTripId = "";
    private long lastMovedAtMs = 0L;
    private boolean isCurrentlyDelayed = false;
    private boolean campusArrivalFired = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "ACTION_START_TRACKING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "ACTION_STOP_TRACKING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ROUTE_ID = "EXTRA_ROUTE_ID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_BUS_NUMBER = "EXTRA_BUS_NUMBER";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TRIP_ID = "EXTRA_TRIP_ID";
    
    /**
     * Bus must be stationary for this long (ms) before trip is marked DELAYED.
     */
    private static final long DELAY_THRESHOLD_MS = 180000L;
    
    /**
     * Speeds below this (km/h) are considered "stationary".
     */
    private static final float MOVING_THRESHOLD_KMH = 3.0F;
    
    /**
     * GPS accuracy threshold (metres).  Only apply delay logic when the fix
     * is at least this accurate — filters out false-zero speeds in tunnels,
     * parking garages, or dense urban canyons with poor satellite visibility.
     */
    private static final float MIN_GPS_ACCURACY_M = 50.0F;
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "routex_driver_channel";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "LocationTrackingService";
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.driver.data.service.LocationTrackingService.Companion Companion = null;
    
    public LocationTrackingService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.core.location.LocationProvider getLocationProvider() {
        return null;
    }
    
    public final void setLocationProvider(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.location.LocationProvider p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.driver.domain.repository.DriverRepository getDriverRepository() {
        return null;
    }
    
    public final void setDriverRepository(@org.jetbrains.annotations.NotNull()
    com.routex.app.driver.domain.repository.DriverRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.trips.domain.repository.TripRepository getTripRepository() {
        return null;
    }
    
    public final void setTripRepository(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.repository.TripRepository p0) {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    private final void startForegroundWithNotification() {
    }
    
    private final void startTracking() {
    }
    
    /**
     * Detects when the bus has been stationary longer than [DELAY_THRESHOLD_MS].
     *
     * **Accuracy gate:** Only runs when the GPS fix meets both conditions:
     * - [accuracy] < [MIN_GPS_ACCURACY_M] (fix is precise enough to trust)
     * - [hasSpeed] is true (GPS hardware is reporting speed, not just 0.0f default)
     *
     * Without this gate, tunnels/parking garages produce GPS fixes with
     * speed = 0.0 and accuracy > 100 m, which would falsely trigger DELAYED.
     */
    private final void checkAndUpdateDelayStatus(float speedKmh, float accuracy, boolean hasSpeed) {
    }
    
    /**
     * Checks whether the bus has entered the KLS GIT campus radius.
     * If yes (and not previously fired for this trip):
     * 1. Ends the active trip in Firestore (marks COMPLETED).
     * 2. Posts a "Arrived at KLS GIT" system notification.
     * 3. Stops the tracking service — the trip is done.
     */
    private final void checkCampusArrival(double busLat, double busLng) {
    }
    
    private final void refreshNotification(int speedKmh) {
    }
    
    private final void createNotificationChannel() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/routex/app/driver/data/service/LocationTrackingService$Companion;", "", "()V", "ACTION_START", "", "ACTION_STOP", "CHANNEL_ID", "DELAY_THRESHOLD_MS", "", "EXTRA_BUS_NUMBER", "EXTRA_ROUTE_ID", "EXTRA_TRIP_ID", "MIN_GPS_ACCURACY_M", "", "MOVING_THRESHOLD_KMH", "NOTIFICATION_ID", "", "TAG", "startIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "routeId", "busNumber", "tripId", "stopIntent", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent startIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String routeId, @org.jetbrains.annotations.NotNull()
        java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
        java.lang.String tripId) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent stopIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}