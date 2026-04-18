package com.routex.app.eta.domain.model;

/**
 * Represents the operational status of the bus relative to the student's selected stop.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/routex/app/eta/domain/model/BusStatus;", "", "label", "", "description", "colorHex", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;J)V", "getColorHex", "()J", "getDescription", "()Ljava/lang/String;", "getLabel", "ON_TIME", "APPROACHING", "ARRIVING", "DELAYED", "STOPPED", "OFFLINE", "app_debug"})
public enum BusStatus {
    /*public static final*/ ON_TIME /* = new ON_TIME(null, null, 0L) */,
    /*public static final*/ APPROACHING /* = new APPROACHING(null, null, 0L) */,
    /*public static final*/ ARRIVING /* = new ARRIVING(null, null, 0L) */,
    /*public static final*/ DELAYED /* = new DELAYED(null, null, 0L) */,
    /*public static final*/ STOPPED /* = new STOPPED(null, null, 0L) */,
    /*public static final*/ OFFLINE /* = new OFFLINE(null, null, 0L) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final long colorHex = 0L;
    
    BusStatus(java.lang.String label, java.lang.String description, long colorHex) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final long getColorHex() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.routex.app.eta.domain.model.BusStatus> getEntries() {
        return null;
    }
}