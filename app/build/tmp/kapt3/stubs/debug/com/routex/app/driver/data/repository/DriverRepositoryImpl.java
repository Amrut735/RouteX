package com.routex.app.driver.data.repository;

import com.routex.app.core.utils.Resource;
import com.routex.app.driver.domain.repository.DriverRepository;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.MapLocation;
import com.routex.app.maps.domain.repository.MapsRepository;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJL\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/routex/app/driver/data/repository/DriverRepositoryImpl;", "Lcom/routex/app/driver/domain/repository/DriverRepository;", "mapsRepository", "Lcom/routex/app/maps/domain/repository/MapsRepository;", "(Lcom/routex/app/maps/domain/repository/MapsRepository;)V", "setOnlineStatus", "Lcom/routex/app/core/utils/Resource;", "", "routeId", "", "isOnline", "", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadLocation", "latitude", "", "longitude", "speed", "", "heading", "accuracy", "busNumber", "(Ljava/lang/String;DDFFFLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DriverRepositoryImpl implements com.routex.app.driver.domain.repository.DriverRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.repository.MapsRepository mapsRepository = null;
    
    @javax.inject.Inject()
    public DriverRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.repository.MapsRepository mapsRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object uploadLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, double latitude, double longitude, float speed, float heading, float accuracy, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object setOnlineStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, boolean isOnline, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<kotlin.Unit>> $completion) {
        return null;
    }
}