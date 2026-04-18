package com.routex.app.eta.domain.model;

/**
 * Complete ETA snapshot returned by [CalculateEtaUseCase] for a single bus update.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\fH\u00c6\u0003J\t\u0010+\u001a\u00020\fH\u00c6\u0003J\t\u0010,\u001a\u00020\u000fH\u00c6\u0003Jc\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010.\u001a\u00020\u00192\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u000201H\u00d6\u0001J\t\u00102\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0018\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u001c\u001a\u00020\u00198F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014\u00a8\u00063"}, d2 = {"Lcom/routex/app/eta/domain/model/EtaResult;", "", "etaMinutes", "", "distanceKm", "", "status", "Lcom/routex/app/eta/domain/model/BusStatus;", "effectiveSpeedKmh", "rawSpeedKmh", "trafficFactor", "stopName", "", "etaFormatted", "timestamp", "", "(FDLcom/routex/app/eta/domain/model/BusStatus;FFFLjava/lang/String;Ljava/lang/String;J)V", "getDistanceKm", "()D", "getEffectiveSpeedKmh", "()F", "getEtaFormatted", "()Ljava/lang/String;", "getEtaMinutes", "isAtStop", "", "()Z", "getRawSpeedKmh", "shouldAlert", "getShouldAlert", "getStatus", "()Lcom/routex/app/eta/domain/model/BusStatus;", "getStopName", "getTimestamp", "()J", "getTrafficFactor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class EtaResult {
    
    /**
     * Minutes until the bus reaches the selected boarding stop (0 = arrived).
     */
    private final float etaMinutes = 0.0F;
    
    /**
     * Straight-line distance in km from the bus to the boarding stop.
     */
    private final double distanceKm = 0.0;
    
    /**
     * Operational status derived from speed, ETA trend, and proximity.
     */
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.eta.domain.model.BusStatus status = null;
    
    /**
     * Effective speed used in this calculation (km/h), after traffic adjustment.
     */
    private final float effectiveSpeedKmh = 0.0F;
    
    /**
     * Raw bus speed from GPS (km/h).
     */
    private final float rawSpeedKmh = 0.0F;
    
    /**
     * Traffic multiplier applied (1.0 = free flow, >1 = slower).
     */
    private final float trafficFactor = 0.0F;
    
    /**
     * Name of the student's selected boarding stop.
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String stopName = null;
    
    /**
     * Formatted ETA string, e.g. "4 min" or "Arriving".
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String etaFormatted = null;
    private final long timestamp = 0L;
    
    public EtaResult(float etaMinutes, double distanceKm, @org.jetbrains.annotations.NotNull()
    com.routex.app.eta.domain.model.BusStatus status, float effectiveSpeedKmh, float rawSpeedKmh, float trafficFactor, @org.jetbrains.annotations.NotNull()
    java.lang.String stopName, @org.jetbrains.annotations.NotNull()
    java.lang.String etaFormatted, long timestamp) {
        super();
    }
    
    /**
     * Minutes until the bus reaches the selected boarding stop (0 = arrived).
     */
    public final float getEtaMinutes() {
        return 0.0F;
    }
    
    /**
     * Straight-line distance in km from the bus to the boarding stop.
     */
    public final double getDistanceKm() {
        return 0.0;
    }
    
    /**
     * Operational status derived from speed, ETA trend, and proximity.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.eta.domain.model.BusStatus getStatus() {
        return null;
    }
    
    /**
     * Effective speed used in this calculation (km/h), after traffic adjustment.
     */
    public final float getEffectiveSpeedKmh() {
        return 0.0F;
    }
    
    /**
     * Raw bus speed from GPS (km/h).
     */
    public final float getRawSpeedKmh() {
        return 0.0F;
    }
    
    /**
     * Traffic multiplier applied (1.0 = free flow, >1 = slower).
     */
    public final float getTrafficFactor() {
        return 0.0F;
    }
    
    /**
     * Name of the student's selected boarding stop.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStopName() {
        return null;
    }
    
    /**
     * Formatted ETA string, e.g. "4 min" or "Arriving".
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEtaFormatted() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final boolean isAtStop() {
        return false;
    }
    
    public final boolean getShouldAlert() {
        return false;
    }
    
    public final float component1() {
        return 0.0F;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.eta.domain.model.BusStatus component3() {
        return null;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.eta.domain.model.EtaResult copy(float etaMinutes, double distanceKm, @org.jetbrains.annotations.NotNull()
    com.routex.app.eta.domain.model.BusStatus status, float effectiveSpeedKmh, float rawSpeedKmh, float trafficFactor, @org.jetbrains.annotations.NotNull()
    java.lang.String stopName, @org.jetbrains.annotations.NotNull()
    java.lang.String etaFormatted, long timestamp) {
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