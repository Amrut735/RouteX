package com.routex.app.maps.domain.repository;

import com.routex.app.maps.domain.model.BusLocationUpdate;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/routex/app/maps/domain/repository/MapsRepository;", "", "getAllActiveBusLocations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "getBusLocation", "routeId", "", "setDriverOnlineStatus", "", "isOnline", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadDriverLocation", "update", "(Ljava/lang/String;Lcom/routex/app/maps/domain/model/BusLocationUpdate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface MapsRepository {
    
    /**
     * Real-time bus location for a single route (Firebase RTDB).
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.maps.domain.model.BusLocationUpdate> getBusLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId);
    
    /**
     * Real-time location stream for ALL online buses.
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> getAllActiveBusLocations();
    
    /**
     * Driver: atomically writes the current location + metadata to RTDB.
     * Called from the background service every 3 s.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadDriverLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.BusLocationUpdate update, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Driver: mark the bus online/offline when the service starts/stops.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setDriverOnlineStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, boolean isOnline, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}