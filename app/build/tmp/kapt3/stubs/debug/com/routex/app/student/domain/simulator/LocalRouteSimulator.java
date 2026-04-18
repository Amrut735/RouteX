package com.routex.app.student.domain.simulator;

import com.google.android.gms.maps.model.LatLng;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.MapLocation;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\tH\u0002J\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J,\u0010\r\u001a\u00020\u000e2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0010\u001a\u00020\nH\u0002J0\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\t0\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\t\u00a8\u0006\u001b"}, d2 = {"Lcom/routex/app/student/domain/simulator/LocalRouteSimulator;", "", "()V", "calculateHeading", "", "p1", "Lcom/google/android/gms/maps/model/LatLng;", "p2", "calculatePathDistances", "", "", "points", "distanceBetween", "interpolatePosition", "Lcom/routex/app/student/domain/simulator/LocalRouteSimulator$SimulatedPosition;", "distances", "targetDist", "simulateBuses", "Lkotlinx/coroutines/flow/Flow;", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "routeId", "", "routeNumber", "stops", "Lcom/routex/app/student/domain/model/BusStop;", "SimulatedBus", "SimulatedPosition", "app_debug"})
public final class LocalRouteSimulator {
    
    public LocalRouteSimulator() {
        super();
    }
    
    /**
     * Generates a continuous real-time flow of 3 simulated buses moving along the route's stops.
     * The buses move bidirectionally with staggered timings and logical spacing.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> simulateBuses(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeNumber, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.student.domain.model.BusStop> stops) {
        return null;
    }
    
    private final java.util.List<java.lang.Double> calculatePathDistances(java.util.List<com.google.android.gms.maps.model.LatLng> points) {
        return null;
    }
    
    private final com.routex.app.student.domain.simulator.LocalRouteSimulator.SimulatedPosition interpolatePosition(java.util.List<com.google.android.gms.maps.model.LatLng> points, java.util.List<java.lang.Double> distances, double targetDist) {
        return null;
    }
    
    private final double distanceBetween(com.google.android.gms.maps.model.LatLng p1, com.google.android.gms.maps.model.LatLng p2) {
        return 0.0;
    }
    
    private final float calculateHeading(com.google.android.gms.maps.model.LatLng p1, com.google.android.gms.maps.model.LatLng p2) {
        return 0.0F;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lcom/routex/app/student/domain/simulator/LocalRouteSimulator$SimulatedBus;", "", "id", "", "busNumber", "offsetMeters", "", "movingForward", "", "(Ljava/lang/String;Ljava/lang/String;DZ)V", "getBusNumber", "()Ljava/lang/String;", "getId", "getMovingForward", "()Z", "setMovingForward", "(Z)V", "getOffsetMeters", "()D", "setOffsetMeters", "(D)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    static final class SimulatedBus {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String id = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String busNumber = null;
        private double offsetMeters;
        private boolean movingForward;
        
        public SimulatedBus(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String busNumber, double offsetMeters, boolean movingForward) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBusNumber() {
            return null;
        }
        
        public final double getOffsetMeters() {
            return 0.0;
        }
        
        public final void setOffsetMeters(double p0) {
        }
        
        public final boolean getMovingForward() {
            return false;
        }
        
        public final void setMovingForward(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.student.domain.simulator.LocalRouteSimulator.SimulatedBus copy(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.NotNull()
        java.lang.String busNumber, double offsetMeters, boolean movingForward) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lcom/routex/app/student/domain/simulator/LocalRouteSimulator$SimulatedPosition;", "", "latitude", "", "longitude", "heading", "", "(DDF)V", "getHeading", "()F", "getLatitude", "()D", "getLongitude", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    static final class SimulatedPosition {
        private final double latitude = 0.0;
        private final double longitude = 0.0;
        private final float heading = 0.0F;
        
        public SimulatedPosition(double latitude, double longitude, float heading) {
            super();
        }
        
        public final double getLatitude() {
            return 0.0;
        }
        
        public final double getLongitude() {
            return 0.0;
        }
        
        public final float getHeading() {
            return 0.0F;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.student.domain.simulator.LocalRouteSimulator.SimulatedPosition copy(double latitude, double longitude, float heading) {
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
}