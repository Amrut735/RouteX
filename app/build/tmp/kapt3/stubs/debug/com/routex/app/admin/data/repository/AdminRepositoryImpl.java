package com.routex.app.admin.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.admin.data.source.AlertDataSource;
import com.routex.app.admin.data.source.BusDataSource;
import com.routex.app.admin.data.source.DriverDataSource;
import com.routex.app.admin.data.source.RouteAdminDataSource;
import com.routex.app.admin.domain.model.AnalyticsSnapshot;
import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.admin.domain.model.EmergencyAlert;
import com.routex.app.admin.domain.model.HourlyActivity;
import com.routex.app.admin.domain.repository.AdminRepository;
import com.routex.app.admin.domain.repository.AdminStats;
import com.routex.app.core.utils.Resource;
import kotlinx.coroutines.flow.Flow;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Thin orchestration layer that delegates to focused data sources.
 * Each data source owns a single Firestore collection, keeping this
 * class under 60 lines and each source easily unit-testable.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\u0018\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0019J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010 J,\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010%\u001a\u00020&H\u0002J\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u001f\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u001c\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u001e\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u001a\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0#0\u000e0,H\u0016J\u001a\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130#0\u000e0,H\u0016J\u001a\u0010.\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170#0\u000e0,H\u0016J\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u000eH\u0096@\u00a2\u0006\u0002\u00101J\u001c\u00102\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u0014\u00103\u001a\b\u0012\u0004\u0012\u0002040\u000eH\u0096@\u00a2\u0006\u0002\u00101J\u001c\u00105\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u00106\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u0014\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080#0,H\u0016J\u0010\u00109\u001a\u00020\u001d2\u0006\u0010:\u001a\u00020&H\u0002J\u001c\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010<\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010(J\u000e\u0010=\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u00101J\u000e\u0010>\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u00101J\u000e\u0010?\u001a\u00020\u001bH\u0096@\u00a2\u0006\u0002\u00101J\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010A\u001a\u000208H\u0096@\u00a2\u0006\u0002\u0010BJ\u001c\u0010C\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u0010\u001a\u00020\u000fH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u001c\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u0018\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"Lcom/routex/app/admin/data/repository/AdminRepositoryImpl;", "Lcom/routex/app/admin/domain/repository/AdminRepository;", "routeSource", "Lcom/routex/app/admin/data/source/RouteAdminDataSource;", "busSource", "Lcom/routex/app/admin/data/source/BusDataSource;", "driverSource", "Lcom/routex/app/admin/data/source/DriverDataSource;", "alertSource", "Lcom/routex/app/admin/data/source/AlertDataSource;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/routex/app/admin/data/source/RouteAdminDataSource;Lcom/routex/app/admin/data/source/BusDataSource;Lcom/routex/app/admin/data/source/DriverDataSource;Lcom/routex/app/admin/data/source/AlertDataSource;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "addBus", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/admin/domain/model/Bus;", "bus", "(Lcom/routex/app/admin/domain/model/Bus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addDriver", "Lcom/routex/app/admin/domain/model/Driver;", "driver", "(Lcom/routex/app/admin/domain/model/Driver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addRoute", "Lcom/routex/app/admin/domain/model/BusRoute;", "route", "(Lcom/routex/app/admin/domain/model/BusRoute;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assignBusToRoute", "", "busId", "", "routeId", "driverId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "assignDriver", "buildHourlyActivity", "", "Lcom/routex/app/admin/domain/model/HourlyActivity;", "routeCount", "", "deleteBus", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDriver", "deleteRoute", "getAllBuses", "Lkotlinx/coroutines/flow/Flow;", "getAllDrivers", "getAllRoutes", "getAnalyticsSnapshot", "Lcom/routex/app/admin/domain/model/AnalyticsSnapshot;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBusById", "getDashboardStats", "Lcom/routex/app/admin/domain/repository/AdminStats;", "getDriverByAuthUid", "uid", "observeActiveAlerts", "Lcom/routex/app/admin/domain/model/EmergencyAlert;", "peakHourLabel", "hour", "resolveAlert", "alertId", "seedBusesIfEmpty", "seedDummyDriversIfEmpty", "seedRoutesIfEmpty", "sendEmergencyAlert", "alert", "(Lcom/routex/app/admin/domain/model/EmergencyAlert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBus", "updateDriver", "updateRoute", "app_debug"})
public final class AdminRepositoryImpl implements com.routex.app.admin.domain.repository.AdminRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.data.source.RouteAdminDataSource routeSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.data.source.BusDataSource busSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.data.source.DriverDataSource driverSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.data.source.AlertDataSource alertSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    @javax.inject.Inject()
    public AdminRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.data.source.RouteAdminDataSource routeSource, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.data.source.BusDataSource busSource, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.data.source.DriverDataSource driverSource, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.data.source.AlertDataSource alertSource, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> getAllRoutes() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addRoute(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.BusRoute route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.BusRoute>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateRoute(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.BusRoute route, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.Bus>>> getAllBuses() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addBus(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Bus bus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Bus>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateBus(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Bus bus, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteBus(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object assignBusToRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getBusById(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Bus>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.routex.app.core.utils.Resource<java.util.List<com.routex.app.admin.domain.model.Driver>>> getAllDrivers() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateDriver(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Driver driver, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDriverByAuthUid(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Driver>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object seedDummyDriversIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object seedBusesIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object seedRoutesIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Atomic: update driver + bus fields in a single batch commit.
     */
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object assignDriver(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendEmergencyAlert(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.EmergencyAlert alert, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> observeActiveAlerts() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object resolveAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String alertId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAnalyticsSnapshot(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.AnalyticsSnapshot>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDashboardStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.admin.domain.repository.AdminStats>> $completion) {
        return null;
    }
    
    private final java.lang.String peakHourLabel(int hour) {
        return null;
    }
    
    private final java.util.List<com.routex.app.admin.domain.model.HourlyActivity> buildHourlyActivity(int routeCount) {
        return null;
    }
}