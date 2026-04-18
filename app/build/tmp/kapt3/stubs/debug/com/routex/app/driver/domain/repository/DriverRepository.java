package com.routex.app.driver.domain.repository;

import com.routex.app.core.utils.Resource;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\tJL\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/routex/app/driver/domain/repository/DriverRepository;", "", "setOnlineStatus", "Lcom/routex/app/core/utils/Resource;", "", "routeId", "", "isOnline", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadLocation", "latitude", "", "longitude", "speed", "", "heading", "accuracy", "busNumber", "(Ljava/lang/String;DDFFFLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface DriverRepository {
    
    /**
     * Write the driver's GPS position to Firebase RTDB.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, double latitude, double longitude, float speed, float heading, float accuracy, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    /**
     * Mark the driver's bus as online/offline in RTDB.
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setOnlineStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, boolean isOnline, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
}