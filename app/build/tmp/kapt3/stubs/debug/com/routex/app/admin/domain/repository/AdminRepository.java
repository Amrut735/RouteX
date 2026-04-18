package com.routex.app.admin.domain.repository;

import com.routex.app.admin.domain.model.AnalyticsSnapshot;
import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.admin.domain.model.EmergencyAlert;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0015J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0014\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0013\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u001a\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001d0\u00030\u001cH&J\u001a\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001d0\u00030\u001cH&J\u001a\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001d0\u00030\u001cH&J\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\"J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0003H\u00a6@\u00a2\u0006\u0002\u0010\"J\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\'\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u001d0\u001cH&J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010+\u001a\u00020\u0012H\u00a6@\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010,\u001a\u00020\u0010H\u00a6@\u00a2\u0006\u0002\u0010\"J\u000e\u0010-\u001a\u00020\u0010H\u00a6@\u00a2\u0006\u0002\u0010\"J\u000e\u0010.\u001a\u00020\u0010H\u00a6@\u00a2\u0006\u0002\u0010\"J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u00100\u001a\u00020)H\u00a6@\u00a2\u0006\u0002\u00101J\u001c\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\t\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\r\u001a\u00020\fH\u00a6@\u00a2\u0006\u0002\u0010\u000e\u00a8\u00065"}, d2 = {"Lcom/routex/app/admin/domain/repository/AdminRepository;", "", "addBus", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/admin/domain/model/Bus;", "bus", "(Lcom/routex/app/admin/domain/model/Bus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addDriver", "Lcom/routex/app/admin/domain/model/Driver;", "driver", "(Lcom/routex/app/admin/domain/model/Driver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addRoute", "Lcom/routex/app/admin/domain/model/BusRoute;", "route", "(Lcom/routex/app/admin/domain/model/BusRoute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assignBusToRoute", "", "busId", "", "routeId", "driverId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assignDriver", "deleteBus", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDriver", "deleteRoute", "getAllBuses", "Lkotlinx/coroutines/flow/Flow;", "", "getAllDrivers", "getAllRoutes", "getAnalyticsSnapshot", "Lcom/routex/app/admin/domain/model/AnalyticsSnapshot;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBusById", "getDashboardStats", "Lcom/routex/app/admin/domain/repository/AdminStats;", "getDriverByAuthUid", "uid", "observeActiveAlerts", "Lcom/routex/app/admin/domain/model/EmergencyAlert;", "resolveAlert", "alertId", "seedBusesIfEmpty", "seedDummyDriversIfEmpty", "seedRoutesIfEmpty", "sendEmergencyAlert", "alert", "(Lcom/routex/app/admin/domain/model/EmergencyAlert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBus", "updateDriver", "updateRoute", "app_debug"})
public abstract interface AdminRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> getAllRoutes();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addRoute(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.BusRoute route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.BusRoute>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoute(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.BusRoute route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.Bus>>> getAllBuses();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addBus(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Bus bus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Bus>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBus(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Bus bus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBus(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object assignBusToRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBusById(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Bus>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.Driver>>> getAllDrivers();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDriverByAuthUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object assignDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object seedDummyDriversIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object seedBusesIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object seedRoutesIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendEmergencyAlert(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.EmergencyAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> observeActiveAlerts();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resolveAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAnalyticsSnapshot(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.AnalyticsSnapshot>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDashboardStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.repository.AdminStats>> $completion);
}