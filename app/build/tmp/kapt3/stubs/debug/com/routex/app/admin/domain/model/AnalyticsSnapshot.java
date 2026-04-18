package com.routex.app.admin.domain.model;

/**
 * Aggregated analytics shown on the admin analytics screen.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\bH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\fH\u00c6\u0003J\u0015\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000eH\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003Ju\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u0003H\u00d6\u0001J\t\u00100\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014\u00a8\u00061"}, d2 = {"Lcom/routex/app/admin/domain/model/AnalyticsSnapshot;", "", "totalTripsToday", "", "activeDriversNow", "avgSpeedKmh", "", "totalDistanceTodayKm", "", "alertsSentToday", "onTimePercentage", "peakHour", "", "routeLoadMap", "", "hourlyActivity", "", "Lcom/routex/app/admin/domain/model/HourlyActivity;", "(IIFDIFLjava/lang/String;Ljava/util/Map;Ljava/util/List;)V", "getActiveDriversNow", "()I", "getAlertsSentToday", "getAvgSpeedKmh", "()F", "getHourlyActivity", "()Ljava/util/List;", "getOnTimePercentage", "getPeakHour", "()Ljava/lang/String;", "getRouteLoadMap", "()Ljava/util/Map;", "getTotalDistanceTodayKm", "()D", "getTotalTripsToday", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class AnalyticsSnapshot {
    private final int totalTripsToday = 0;
    private final int activeDriversNow = 0;
    private final float avgSpeedKmh = 0.0F;
    private final double totalDistanceTodayKm = 0.0;
    private final int alertsSentToday = 0;
    private final float onTimePercentage = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String peakHour = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Integer> routeLoadMap = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.routex.app.admin.domain.model.HourlyActivity> hourlyActivity = null;
    
    public AnalyticsSnapshot(int totalTripsToday, int activeDriversNow, float avgSpeedKmh, double totalDistanceTodayKm, int alertsSentToday, float onTimePercentage, @org.jetbrains.annotations.NotNull()
    java.lang.String peakHour, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> routeLoadMap, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.admin.domain.model.HourlyActivity> hourlyActivity) {
        super();
    }
    
    public final int getTotalTripsToday() {
        return 0;
    }
    
    public final int getActiveDriversNow() {
        return 0;
    }
    
    public final float getAvgSpeedKmh() {
        return 0.0F;
    }
    
    public final double getTotalDistanceTodayKm() {
        return 0.0;
    }
    
    public final int getAlertsSentToday() {
        return 0;
    }
    
    public final float getOnTimePercentage() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPeakHour() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> getRouteLoadMap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.routex.app.admin.domain.model.HourlyActivity> getHourlyActivity() {
        return null;
    }
    
    public AnalyticsSnapshot() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Integer> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.routex.app.admin.domain.model.HourlyActivity> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.admin.domain.model.AnalyticsSnapshot copy(int totalTripsToday, int activeDriversNow, float avgSpeedKmh, double totalDistanceTodayKm, int alertsSentToday, float onTimePercentage, @org.jetbrains.annotations.NotNull()
    java.lang.String peakHour, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Integer> routeLoadMap, @org.jetbrains.annotations.NotNull()
    java.util.List<com.routex.app.admin.domain.model.HourlyActivity> hourlyActivity) {
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