package com.routex.app.core.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationTokenSource;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0087@\u00a2\u0006\u0002\u0010\tJ\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/routex/app/core/location/LocationProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fusedClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "lastLocation", "Landroid/location/Location;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "locationUpdates", "Lkotlinx/coroutines/flow/Flow;", "intervalMs", "", "minIntervalMs", "Companion", "app_debug"})
public final class LocationProvider {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fusedClient = null;
    
    /**
     * Driver GPS upload cadence (3 s)
     */
    public static final long DRIVER_INTERVAL_MS = 3000L;
    
    /**
     * Student passive refresh cadence (5 s)
     */
    public static final long STUDENT_INTERVAL_MS = 5000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.core.location.LocationProvider.Companion Companion = null;
    
    @javax.inject.Inject()
    public LocationProvider(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Emits a continuous stream of [Location] updates at the requested interval.
     * Call only after runtime location permission has been granted.
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<android.location.Location> locationUpdates(long intervalMs, long minIntervalMs) {
        return null;
    }
    
    /**
     * One-shot best available location (non-null or null if unavailable).
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object lastLocation(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.location.Location> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/routex/app/core/location/LocationProvider$Companion;", "", "()V", "DRIVER_INTERVAL_MS", "", "STUDENT_INTERVAL_MS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}