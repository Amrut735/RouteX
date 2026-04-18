package com.routex.app.maps.presentation;

import android.location.Location;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.maps.data.geofencing.GeofenceManager;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.GeofenceTransition;
import com.routex.app.maps.domain.model.RouteProgress;
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u00106\u001a\u000207J\b\u00108\u001a\u000207H\u0002J\b\u00109\u001a\u000207H\u0002J\b\u0010:\u001a\u000207H\u0002J\b\u0010;\u001a\u000207H\u0002J\b\u0010<\u001a\u000207H\u0002J\b\u0010=\u001a\u000207H\u0014J\u0010\u0010>\u001a\u0002072\u0006\u0010?\u001a\u00020\u0010H\u0002J\u0006\u0010@\u001a\u000207J\u0006\u0010A\u001a\u000207R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00190\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010 R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\'0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010)\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010,\u001a\u00020-\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00170\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u001f\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00190\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u0019\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010 \u00a8\u0006B"}, d2 = {"Lcom/routex/app/maps/presentation/MapViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getBusLocation", "Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;", "getRouteById", "Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;", "locationProvider", "Lcom/routex/app/core/location/LocationProvider;", "geofenceManager", "Lcom/routex/app/maps/data/geofencing/GeofenceManager;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;Lcom/routex/app/core/location/LocationProvider;Lcom/routex/app/maps/data/geofencing/GeofenceManager;)V", "_allBusLocations", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "_approachingCampusBuses", "_busLocation", "_followBus", "", "_isArrivingAtCampus", "_routeProgress", "Lcom/routex/app/maps/domain/model/RouteProgress;", "_routeState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/student/domain/model/Route;", "_userLocation", "Lcom/google/android/gms/maps/model/LatLng;", "allBusLocations", "Lkotlinx/coroutines/flow/StateFlow;", "getAllBusLocations", "()Lkotlinx/coroutines/flow/StateFlow;", "approachingCampusBuses", "getApproachingCampusBuses", "busLocation", "followBus", "getFollowBus", "geofenceEvents", "Lcom/routex/app/maps/domain/model/GeofenceEvent;", "getGeofenceEvents", "isAllRoutes", "()Z", "isArrivingAtCampus", "routeId", "", "getRouteId", "()Ljava/lang/String;", "routeProgress", "getRouteProgress", "routeState", "getRouteState", "userLocation", "getUserLocation", "disableFollowBus", "", "loadRoute", "observeAllBuses", "observeCampusGeofenceEvents", "observeSingleBus", "observeUserLocation", "onCleared", "recomputePolyline", "update", "retryLoad", "toggleFollowBus", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MapViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.location.LocationProvider locationProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.data.geofencing.GeofenceManager geofenceManager = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> _routeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> routeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.maps.domain.model.BusLocationUpdate> _busLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.BusLocationUpdate> busLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> _allBusLocations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> allBusLocations = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.google.android.gms.maps.model.LatLng> _userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.maps.domain.model.RouteProgress> _routeProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.RouteProgress> routeProgress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.GeofenceEvent> geofenceEvents = null;
    
    /**
     * True when the bus has entered the KLS GIT campus radius.
     * Drives the "Arriving at College" UI banner in [MapScreen].
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isArrivingAtCampus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isArrivingAtCampus = null;
    
    /**
     * Buses that are within [CollegeHub.APPROACHING_RADIUS_M] of the campus.
     * Shown in the "all routes" / admin map view.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> _approachingCampusBuses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> approachingCampusBuses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _followBus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> followBus = null;
    
    @javax.inject.Inject()
    public MapViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.location.LocationProvider locationProvider, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.data.geofencing.GeofenceManager geofenceManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    public final boolean isAllRoutes() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> getRouteState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.BusLocationUpdate> getBusLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> getAllBusLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> getUserLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.RouteProgress> getRouteProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.maps.domain.model.GeofenceEvent> getGeofenceEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isArrivingAtCampus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> getApproachingCampusBuses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getFollowBus() {
        return null;
    }
    
    /**
     * Public so MapScreen can offer a "Retry" button on error.
     */
    public final void retryLoad() {
    }
    
    private final void loadRoute() {
    }
    
    private final void observeSingleBus() {
    }
    
    private final void observeAllBuses() {
    }
    
    private final void observeCampusGeofenceEvents() {
    }
    
    private final void observeUserLocation() {
    }
    
    private final void recomputePolyline(com.routex.app.maps.domain.model.BusLocationUpdate update) {
    }
    
    public final void toggleFollowBus() {
    }
    
    public final void disableFollowBus() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}