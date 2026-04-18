package com.routex.app.maps.domain.model;

import com.routex.app.student.domain.model.BusStop;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u000bH\u00c6\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/routex/app/maps/domain/model/GeofenceEvent;", "", "stop", "Lcom/routex/app/student/domain/model/BusStop;", "distanceMeters", "", "transition", "Lcom/routex/app/maps/domain/model/GeofenceTransition;", "timestamp", "", "isCampusEntry", "", "(Lcom/routex/app/student/domain/model/BusStop;FLcom/routex/app/maps/domain/model/GeofenceTransition;JZ)V", "getDistanceMeters", "()F", "()Z", "getStop", "()Lcom/routex/app/student/domain/model/BusStop;", "getTimestamp", "()J", "getTransition", "()Lcom/routex/app/maps/domain/model/GeofenceTransition;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class GeofenceEvent {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.model.BusStop stop = null;
    private final float distanceMeters = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.model.GeofenceTransition transition = null;
    private final long timestamp = 0L;
    
    /**
     * True when this event is fired by the KLS GIT campus geofence rather
     * than a regular bus-stop geofence. Consumers should show a distinct
     * "Arriving at College" UI and mark the active trip COMPLETED.
     */
    private final boolean isCampusEntry = false;
    
    public GeofenceEvent(@org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.model.BusStop stop, float distanceMeters, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.GeofenceTransition transition, long timestamp, boolean isCampusEntry) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.student.domain.model.BusStop getStop() {
        return null;
    }
    
    public final float getDistanceMeters() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.GeofenceTransition getTransition() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    /**
     * True when this event is fired by the KLS GIT campus geofence rather
     * than a regular bus-stop geofence. Consumers should show a distinct
     * "Arriving at College" UI and mark the active trip COMPLETED.
     */
    public final boolean isCampusEntry() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.student.domain.model.BusStop component1() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.GeofenceTransition component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.GeofenceEvent copy(@org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.model.BusStop stop, float distanceMeters, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.GeofenceTransition transition, long timestamp, boolean isCampusEntry) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}