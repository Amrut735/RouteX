package com.routex.app.maps.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003J\t\u0010 \u001a\u00020\rH\u00c6\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0013R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012\u00a8\u0006\'"}, d2 = {"Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "", "routeId", "", "busNumber", "location", "Lcom/routex/app/maps/domain/model/MapLocation;", "speed", "", "heading", "isOnline", "", "lastUpdated", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/routex/app/maps/domain/model/MapLocation;FFZJ)V", "getBusNumber", "()Ljava/lang/String;", "getHeading", "()F", "()Z", "getLastUpdated", "()J", "getLocation", "()Lcom/routex/app/maps/domain/model/MapLocation;", "getRouteId", "getSpeed", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class BusLocationUpdate {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.model.MapLocation location = null;
    private final float speed = 0.0F;
    private final float heading = 0.0F;
    private final boolean isOnline = false;
    private final long lastUpdated = 0L;
    
    public BusLocationUpdate(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.MapLocation location, float speed, float heading, boolean isOnline, long lastUpdated) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.MapLocation getLocation() {
        return null;
    }
    
    public final float getSpeed() {
        return 0.0F;
    }
    
    public final float getHeading() {
        return 0.0F;
    }
    
    public final boolean isOnline() {
        return false;
    }
    
    public final long getLastUpdated() {
        return 0L;
    }
    
    public BusLocationUpdate() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.MapLocation component3() {
        return null;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.BusLocationUpdate copy(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.MapLocation location, float speed, float heading, boolean isOnline, long lastUpdated) {
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