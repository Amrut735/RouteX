package com.routex.app.student.domain.simulator;

import com.google.android.gms.maps.model.LatLng;
import com.routex.app.maps.domain.model.BusLocationUpdate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n\u00a8\u0006\r"}, d2 = {"Lcom/routex/app/student/domain/simulator/EtaCalculator;", "", "()V", "calculateEta", "Lcom/routex/app/student/domain/simulator/EtaCalculator$EtaResult;", "bus", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "stopLoc", "Lcom/google/android/gms/maps/model/LatLng;", "avgSpeedMs", "", "BusStatus", "EtaResult", "app_debug"})
public final class EtaCalculator {
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.student.domain.simulator.EtaCalculator INSTANCE = null;
    
    private EtaCalculator() {
        super();
    }
    
    /**
     * Determine if a bus has passed a stop, and if not, its ETA.
     * The bus has "Passed" if its current route sequence > stop sequence,
     * OR if the bus heading is diverging significantly away from the exact location,
     * OR simply if the bus has progressed beyond the total path distance offset of the stop.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.student.domain.simulator.EtaCalculator.EtaResult calculateEta(@org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.model.BusLocationUpdate bus, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLng stopLoc, double avgSpeedMs) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/routex/app/student/domain/simulator/EtaCalculator$BusStatus;", "", "(Ljava/lang/String;I)V", "ARRIVING", "NEAR", "PASSED", "app_debug"})
    public static enum BusStatus {
        /*public static final*/ ARRIVING /* = new ARRIVING() */,
        /*public static final*/ NEAR /* = new NEAR() */,
        /*public static final*/ PASSED /* = new PASSED() */;
        
        BusStatus() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.routex.app.student.domain.simulator.EtaCalculator.BusStatus> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/routex/app/student/domain/simulator/EtaCalculator$EtaResult;", "", "estimationMinutes", "", "status", "Lcom/routex/app/student/domain/simulator/EtaCalculator$BusStatus;", "distanceMeters", "", "(ILcom/routex/app/student/domain/simulator/EtaCalculator$BusStatus;D)V", "getDistanceMeters", "()D", "getEstimationMinutes", "()I", "getStatus", "()Lcom/routex/app/student/domain/simulator/EtaCalculator$BusStatus;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class EtaResult {
        private final int estimationMinutes = 0;
        @org.jetbrains.annotations.NotNull()
        private final com.routex.app.student.domain.simulator.EtaCalculator.BusStatus status = null;
        private final double distanceMeters = 0.0;
        
        public EtaResult(int estimationMinutes, @org.jetbrains.annotations.NotNull()
        com.routex.app.student.domain.simulator.EtaCalculator.BusStatus status, double distanceMeters) {
            super();
        }
        
        public final int getEstimationMinutes() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.student.domain.simulator.EtaCalculator.BusStatus getStatus() {
            return null;
        }
        
        public final double getDistanceMeters() {
            return 0.0;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.student.domain.simulator.EtaCalculator.BusStatus component2() {
            return null;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.student.domain.simulator.EtaCalculator.EtaResult copy(int estimationMinutes, @org.jetbrains.annotations.NotNull()
        com.routex.app.student.domain.simulator.EtaCalculator.BusStatus status, double distanceMeters) {
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