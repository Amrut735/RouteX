package com.routex.app.admin.domain.model;

import com.routex.app.student.domain.model.BusStop;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b*\b\u0086\b\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u000eH\u00c6\u0003J\t\u0010/\u001a\u00020\u0010H\u00c6\u0003J\t\u00100\u001a\u00020\u0012H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00150\tH\u00c6\u0003J\t\u00103\u001a\u00020\u0017H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\u00ab\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u00c6\u0001J\u0013\u0010=\u001a\u00020\u000e2\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010?\u001a\u00020\u0012H\u00d6\u0001J\t\u0010@\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010#R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,\u00a8\u0006A"}, d2 = {"Lcom/routex/app/admin/domain/model/BusRoute;", "", "id", "", "routeNumber", "routeName", "startPoint", "endPoint", "stopNames", "", "busNumber", "driverId", "driverName", "isActive", "", "totalDistance", "", "estimatedDuration", "", "scheduleTime", "stops", "Lcom/routex/app/student/domain/model/BusStop;", "createdAt", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZDILjava/lang/String;Ljava/util/List;J)V", "getBusNumber", "()Ljava/lang/String;", "getCreatedAt", "()J", "getDriverId", "getDriverName", "getEndPoint", "getEstimatedDuration", "()I", "getId", "()Z", "getRouteName", "getRouteNumber", "getScheduleTime", "getStartPoint", "getStopNames", "()Ljava/util/List;", "getStops", "getTotalDistance", "()D", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class BusRoute {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String startPoint = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String endPoint = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> stopNames = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String driverId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String driverName = null;
    private final boolean isActive = false;
    private final double totalDistance = 0.0;
    private final int estimatedDuration = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String scheduleTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.routex.app.student.domain.model.BusStop> stops = null;
    private final long createdAt = 0L;
    
    public BusRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String routeNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String routeName, @org.jetbrains.annotations.NotNull()
    java.lang.String startPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String endPoint, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> stopNames, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    java.lang.String driverName, boolean isActive, double totalDistance, int estimatedDuration, @org.jetbrains.annotations.NotNull()
    java.lang.String scheduleTime, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.student.domain.model.BusStop> stops, long createdAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStartPoint() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEndPoint() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getStopNames() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverName() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final double getTotalDistance() {
        return 0.0;
    }
    
    public final int getEstimatedDuration() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getScheduleTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.routex.app.student.domain.model.BusStop> getStops() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public BusRoute() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final double component11() {
        return 0.0;
    }
    
    public final int component12() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.routex.app.student.domain.model.BusStop> component14() {
        return null;
    }
    
    public final long component15() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.admin.domain.model.BusRoute copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String routeNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String routeName, @org.jetbrains.annotations.NotNull()
    java.lang.String startPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String endPoint, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> stopNames, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    java.lang.String driverName, boolean isActive, double totalDistance, int estimatedDuration, @org.jetbrains.annotations.NotNull()
    java.lang.String scheduleTime, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.student.domain.model.BusStop> stops, long createdAt) {
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