package com.routex.app.maps.domain.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Splits a route into completed (grey) and remaining (blue) polyline segments
 * based on the bus's current position.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\tH\u00c6\u0003J=\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/routex/app/maps/domain/model/RouteProgress;", "", "completedPoints", "", "Lcom/google/android/gms/maps/model/LatLng;", "remainingPoints", "nextStopIndex", "", "distanceToNextStopM", "", "(Ljava/util/List;Ljava/util/List;IF)V", "getCompletedPoints", "()Ljava/util/List;", "getDistanceToNextStopM", "()F", "getNextStopIndex", "()I", "getRemainingPoints", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class RouteProgress {
    
    /**
     * Points from route start → closest segment to bus (rendered grey).
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.google.android.gms.maps.model.LatLng> completedPoints = null;
    
    /**
     * Points from bus position → route end (rendered primary blue).
     */
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.google.android.gms.maps.model.LatLng> remainingPoints = null;
    
    /**
     * Index of the next upcoming stop.
     */
    private final int nextStopIndex = 0;
    
    /**
     * Straight-line distance (metres) to next stop.
     */
    private final float distanceToNextStopM = 0.0F;
    
    public RouteProgress(@org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> completedPoints, @org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> remainingPoints, int nextStopIndex, float distanceToNextStopM) {
        super();
    }
    
    /**
     * Points from route start → closest segment to bus (rendered grey).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.google.android.gms.maps.model.LatLng> getCompletedPoints() {
        return null;
    }
    
    /**
     * Points from bus position → route end (rendered primary blue).
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.google.android.gms.maps.model.LatLng> getRemainingPoints() {
        return null;
    }
    
    /**
     * Index of the next upcoming stop.
     */
    public final int getNextStopIndex() {
        return 0;
    }
    
    /**
     * Straight-line distance (metres) to next stop.
     */
    public final float getDistanceToNextStopM() {
        return 0.0F;
    }
    
    public RouteProgress() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.google.android.gms.maps.model.LatLng> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.google.android.gms.maps.model.LatLng> component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.maps.domain.model.RouteProgress copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> completedPoints, @org.jetbrains.annotations.NotNull()
    java.util.List<com.google.android.gms.maps.model.LatLng> remainingPoints, int nextStopIndex, float distanceToNextStopM) {
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