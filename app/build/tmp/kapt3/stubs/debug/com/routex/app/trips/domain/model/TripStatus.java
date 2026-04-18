package com.routex.app.trips.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R\u0019\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006\u0013"}, d2 = {"Lcom/routex/app/trips/domain/model/TripStatus;", "", "label", "", "description", "color", "Landroidx/compose/ui/graphics/Color;", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;J)V", "getColor-0d7_KjU", "()J", "J", "getDescription", "()Ljava/lang/String;", "getLabel", "NOT_STARTED", "RUNNING", "DELAYED", "COMPLETED", "Companion", "app_debug"})
public enum TripStatus {
    /*public static final*/ NOT_STARTED /* = new NOT_STARTED(null, null, 0L) */,
    /*public static final*/ RUNNING /* = new RUNNING(null, null, 0L) */,
    /*public static final*/ DELAYED /* = new DELAYED(null, null, 0L) */,
    /*public static final*/ COMPLETED /* = new COMPLETED(null, null, 0L) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final long color = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.trips.domain.model.TripStatus.Companion Companion = null;
    
    TripStatus(java.lang.String label, java.lang.String description, long color) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.routex.app.trips.domain.model.TripStatus> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/routex/app/trips/domain/model/TripStatus$Companion;", "", "()V", "fromString", "Lcom/routex/app/trips/domain/model/TripStatus;", "value", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.trips.domain.model.TripStatus fromString(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            return null;
        }
    }
}