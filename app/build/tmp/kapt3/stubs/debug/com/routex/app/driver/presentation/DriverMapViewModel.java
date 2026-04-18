package com.routex.app.driver.presentation;

import android.location.Location;
import android.util.Log;
import androidx.lifecycle.SavedStateHandle;
import com.google.android.gms.maps.model.LatLng;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.usecase.ObserveActiveTripForRouteUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

/**
 * ViewModel for the Driver Map screen.
 *
 * Responsibilities:
 * - Load the assigned route + stops from Firestore.
 * - Stream the driver's live GPS position via [LocationProvider].
 * - Observe the active trip from Firestore for real-time status.
 * - Compute the *next stop* by finding the stop nearest to the driver
 *  that has not been passed yet (closest in forward direction).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 62\u00020\u0001:\u00016B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ(\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0002J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020/H\u0002J\u0006\u00101\u001a\u00020/J\b\u00102\u001a\u00020/H\u0002J\u0010\u00103\u001a\u00020/2\u0006\u00104\u001a\u000205H\u0002R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00130\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110&0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001a\u00a8\u00067"}, d2 = {"Lcom/routex/app/driver/presentation/DriverMapViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getRouteById", "Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;", "locationProvider", "Lcom/routex/app/core/location/LocationProvider;", "observeActiveTripForRoute", "Lcom/routex/app/trips/domain/usecase/ObserveActiveTripForRouteUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;Lcom/routex/app/core/location/LocationProvider;Lcom/routex/app/trips/domain/usecase/ObserveActiveTripForRouteUseCase;)V", "_activeTrip", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/trips/domain/model/Trip;", "_driverLocation", "Lcom/google/android/gms/maps/model/LatLng;", "_nextStop", "Lcom/routex/app/student/domain/model/BusStop;", "_nextStopDistanceM", "", "_routeState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/student/domain/model/Route;", "activeTrip", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveTrip", "()Lkotlinx/coroutines/flow/StateFlow;", "driverLocation", "getDriverLocation", "nextStop", "getNextStop", "nextStopDistanceM", "getNextStopDistanceM", "routeId", "", "routeState", "getRouteState", "stops", "", "getStops", "haversineM", "lat1", "", "lon1", "lat2", "lon2", "loadRoute", "", "observeTrip", "retryLoad", "startLocationStream", "updateNextStop", "location", "Landroid/location/Location;", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DriverMapViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.location.LocationProvider locationProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.ObserveActiveTripForRouteUseCase observeActiveTripForRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> _routeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> routeState = null;
    
    /**
     * Full list of stops derived from the loaded route.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.student.domain.model.BusStop>> stops = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.trips.domain.model.Trip> _activeTrip = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.Trip> activeTrip = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.google.android.gms.maps.model.LatLng> _driverLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> driverLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.model.BusStop> _nextStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> nextStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Float> _nextStopDistanceM = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Float> nextStopDistanceM = null;
    
    /**
     * Bus is considered to have passed a stop if within 80 m.
     */
    private static final float PASSED_THRESHOLD_M = 80.0F;
    private static final double EARTH_RADIUS_M = 6371000.0;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.driver.presentation.DriverMapViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public DriverMapViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.location.LocationProvider locationProvider, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.ObserveActiveTripForRouteUseCase observeActiveTripForRoute) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> getRouteState() {
        return null;
    }
    
    /**
     * Full list of stops derived from the loaded route.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.student.domain.model.BusStop>> getStops() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.Trip> getActiveTrip() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> getDriverLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> getNextStop() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Float> getNextStopDistanceM() {
        return null;
    }
    
    public final void retryLoad() {
    }
    
    private final void loadRoute() {
    }
    
    private final void startLocationStream() {
    }
    
    private final void observeTrip() {
    }
    
    /**
     * Finds the nearest stop that is still "ahead" of the driver.
     * Strategy: sort stops by sequence, then pick the first one within
     * [PASSED_THRESHOLD_M] that the driver hasn't passed. If all stops
     * have been passed, keep the last stop as the destination.
     */
    private final void updateNextStop(android.location.Location location) {
    }
    
    private final float haversineM(double lat1, double lon1, double lat2, double lon2) {
        return 0.0F;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/routex/app/driver/presentation/DriverMapViewModel$Companion;", "", "()V", "EARTH_RADIUS_M", "", "PASSED_THRESHOLD_M", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}