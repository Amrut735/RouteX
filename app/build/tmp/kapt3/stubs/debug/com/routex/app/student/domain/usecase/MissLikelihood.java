package com.routex.app.student.domain.usecase;

import com.routex.app.eta.domain.model.BusStatus;
import com.routex.app.eta.domain.model.EtaResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/routex/app/student/domain/usecase/MissLikelihood;", "", "label", "", "colorHex", "", "emoji", "(Ljava/lang/String;ILjava/lang/String;JLjava/lang/String;)V", "getColorHex", "()J", "getEmoji", "()Ljava/lang/String;", "getLabel", "WILL_MISS", "TIGHT", "BUS_AT_STOP", "ON_TRACK", "DELAYED", "PLENTY_OF_TIME", "UNKNOWN", "app_debug"})
public enum MissLikelihood {
    /*public static final*/ WILL_MISS /* = new WILL_MISS(null, 0L, null) */,
    /*public static final*/ TIGHT /* = new TIGHT(null, 0L, null) */,
    /*public static final*/ BUS_AT_STOP /* = new BUS_AT_STOP(null, 0L, null) */,
    /*public static final*/ ON_TRACK /* = new ON_TRACK(null, 0L, null) */,
    /*public static final*/ DELAYED /* = new DELAYED(null, 0L, null) */,
    /*public static final*/ PLENTY_OF_TIME /* = new PLENTY_OF_TIME(null, 0L, null) */,
    /*public static final*/ UNKNOWN /* = new UNKNOWN(null, 0L, null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    private final long colorHex = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String emoji = null;
    
    MissLikelihood(java.lang.String label, long colorHex, java.lang.String emoji) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    public final long getColorHex() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmoji() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.routex.app.student.domain.usecase.MissLikelihood> getEntries() {
        return null;
    }
}