package com.routex.app.maps.data.geofencing;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.maps.domain.model.GeofenceEvent;
import com.routex.app.maps.domain.model.GeofenceTransition;
import com.routex.app.student.domain.model.BusStop;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.SharedFlow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u000e\u0010\u0013\u001a\u00020\u000fH\u0087@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0087@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001d"}, d2 = {"Lcom/routex/app/maps/data/geofencing/GeofenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "client", "Lcom/google/android/gms/location/GeofencingClient;", "buildPendingIntent", "Landroid/app/PendingIntent;", "checkCampusProximity", "", "busLat", "", "busLng", "checkProximity", "", "stops", "", "Lcom/routex/app/student/domain/model/BusStop;", "registerCampusGeofence", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerStopGeofences", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAll", "Lkotlin/Result;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "removeAll-IoAF18A", "Companion", "app_debug"})
public final class GeofenceManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.GeofencingClient client = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableSharedFlow<com.routex.app.maps.domain.model.GeofenceEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.SharedFlow<com.routex.app.maps.domain.model.GeofenceEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.maps.data.geofencing.GeofenceManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public GeofenceManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Registers a single geofence for KLS GIT campus.
     * Call this once after the map loads (alongside [registerStopGeofences]).
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registerCampusGeofence(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registerStopGeofences(@org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.student.domain.model.BusStop> stops, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Computes whether [busLocation] is within [GEOFENCE_RADIUS_M] of any stop
     * and emits a [GeofenceEvent] for the nearest qualifying stop.
     * Called from MapViewModel on each bus location update.
     */
    public final void checkProximity(double busLat, double busLng, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.student.domain.model.BusStop> stops) {
    }
    
    /**
     * Software-based campus proximity check — mirrors the OS geofence.
     * Called from [MapViewModel] on each bus location update.
     * Returns true if the bus is within the campus arrival radius.
     */
    public final boolean checkCampusProximity(double busLat, double busLng) {
        return false;
    }
    
    private final android.app.PendingIntent buildPendingIntent() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/routex/app/maps/data/geofencing/GeofenceManager$Companion;", "", "()V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/routex/app/maps/domain/model/GeofenceEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "onGeofenceEvent", "", "transition", "", "geofences", "", "Lcom/google/android/gms/location/Geofence;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.coroutines.flow.SharedFlow<com.routex.app.maps.domain.model.GeofenceEvent> getEvents() {
            return null;
        }
        
        /**
         * Called by [GeofenceBroadcastReceiver].
         */
        public final void onGeofenceEvent(int transition, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends com.google.android.gms.location.Geofence> geofences) {
        }
    }
}