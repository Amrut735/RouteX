package com.routex.app.maps.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.MapLocation;
import com.routex.app.maps.domain.repository.MapsRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0016J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u0004\u0018\u00010\f*\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019*\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/routex/app/maps/data/repository/MapsRepositoryImpl;", "Lcom/routex/app/maps/domain/repository/MapsRepository;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "busRef", "Lcom/google/firebase/database/DatabaseReference;", "routeId", "", "getAllActiveBusLocations", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "getBusLocation", "setDriverOnlineStatus", "", "isOnline", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadDriverLocation", "update", "(Ljava/lang/String;Lcom/routex/app/maps/domain/model/BusLocationUpdate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toBusUpdate", "Lcom/google/firebase/database/DataSnapshot;", "toRtdbMap", "", "", "app_debug"})
public final class MapsRepositoryImpl implements com.routex.app.maps.domain.repository.MapsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    
    @javax.inject.Inject()
    public MapsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    private final com.google.firebase.database.DatabaseReference busRef(java.lang.String routeId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.maps.domain.model.BusLocationUpdate> getBusLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> getAllActiveBusLocations() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadDriverLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.BusLocationUpdate update, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setDriverOnlineStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, boolean isOnline, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final com.routex.app.maps.domain.model.BusLocationUpdate toBusUpdate(com.google.firebase.database.DataSnapshot $this$toBusUpdate, java.lang.String routeId) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toRtdbMap(com.routex.app.maps.domain.model.BusLocationUpdate $this$toRtdbMap) {
        return null;
    }
}