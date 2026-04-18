package com.routex.app.driver.presentation;

import android.content.Context;
import android.os.Build;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.admin.domain.usecase.GetBusByIdUseCase;
import com.routex.app.admin.domain.usecase.GetDriverByAuthUidUseCase;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.network.NetworkMonitor;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.driver.data.service.LocationTrackingService;
import com.routex.app.driver.domain.repository.DriverRepository;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.usecase.EndTripUseCase;
import com.routex.app.trips.domain.usecase.ObserveTripUseCase;
import com.routex.app.trips.domain.usecase.StartTripUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001Ba\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u000207J\u0010\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020\u001fH\u0002J\b\u0010;\u001a\u000207H\u0002J\u0010\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u001fH\u0002J\u0010\u0010>\u001a\u0002072\u0006\u0010?\u001a\u00020\u001fH\u0002J\u0010\u0010@\u001a\u0002072\u0006\u0010A\u001a\u00020\u001fH\u0002J\u0014\u0010\b\u001a\u0002072\f\u0010B\u001a\b\u0012\u0004\u0012\u0002070CJ\u0006\u0010D\u001a\u000207J\b\u0010E\u001a\u000207H\u0002R\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0(\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001f0(\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0(\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010*R\u0019\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0(\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010*R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0(\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010*R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020&0(\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010*R\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020&0(\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010*R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"Lcom/routex/app/driver/presentation/DriverViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "context", "Landroid/content/Context;", "getCurrentUser", "Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;", "getRoutes", "Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;", "signOut", "Lcom/routex/app/auth/domain/usecase/SignOutUseCase;", "startTripUseCase", "Lcom/routex/app/trips/domain/usecase/StartTripUseCase;", "endTripUseCase", "Lcom/routex/app/trips/domain/usecase/EndTripUseCase;", "observeTripUseCase", "Lcom/routex/app/trips/domain/usecase/ObserveTripUseCase;", "getDriverByAuthUid", "Lcom/routex/app/admin/domain/usecase/GetDriverByAuthUidUseCase;", "getBusById", "Lcom/routex/app/admin/domain/usecase/GetBusByIdUseCase;", "driverRepository", "Lcom/routex/app/driver/domain/repository/DriverRepository;", "networkMonitor", "Lcom/routex/app/core/network/NetworkMonitor;", "(Landroid/content/Context;Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;Lcom/routex/app/auth/domain/usecase/SignOutUseCase;Lcom/routex/app/trips/domain/usecase/StartTripUseCase;Lcom/routex/app/trips/domain/usecase/EndTripUseCase;Lcom/routex/app/trips/domain/usecase/ObserveTripUseCase;Lcom/routex/app/admin/domain/usecase/GetDriverByAuthUidUseCase;Lcom/routex/app/admin/domain/usecase/GetBusByIdUseCase;Lcom/routex/app/driver/domain/repository/DriverRepository;Lcom/routex/app/core/network/NetworkMonitor;)V", "_activeTrip", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/trips/domain/model/Trip;", "_assignedRoute", "Lcom/routex/app/student/domain/model/Route;", "_busNumber", "", "_currentUser", "Lcom/routex/app/auth/domain/model/User;", "_driverRecord", "Lcom/routex/app/admin/domain/model/Driver;", "_errorMessage", "_isTracking", "", "activeTrip", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveTrip", "()Lkotlinx/coroutines/flow/StateFlow;", "assignedRoute", "getAssignedRoute", "busNumber", "getBusNumber", "currentUser", "driverRecord", "getDriverRecord", "errorMessage", "getErrorMessage", "isOffline", "isTracking", "clearError", "", "endTrip", "loadDriverRecord", "uid", "loadUser", "observeTrip", "tripId", "resolveAssignedRoute", "routeId", "resolveBusNumber", "busId", "onDone", "Lkotlin/Function0;", "startTrip", "stopTrackingService", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DriverViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignOutUseCase signOut = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.StartTripUseCase startTripUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.EndTripUseCase endTripUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.ObserveTripUseCase observeTripUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetDriverByAuthUidUseCase getDriverByAuthUid = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetBusByIdUseCase getBusById = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.driver.domain.repository.DriverRepository driverRepository = null;
    
    /**
     * True when the device has no internet connectivity.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOffline = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.domain.model.User> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.domain.model.User> currentUser = null;
    
    /**
     * The driver's Firestore record — contains assignedBusId, assignedRouteId, etc.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.domain.model.Driver> _driverRecord = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.Driver> driverRecord = null;
    
    /**
     * Resolved Route object from the driver's assignedRouteId.
     * Null if not yet assigned or route not found.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.model.Route> _assignedRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.Route> assignedRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isTracking = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isTracking = null;
    
    /**
     * The currently active trip, updated in real time from Firestore.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.trips.domain.model.Trip> _activeTrip = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.Trip> activeTrip = null;
    
    /**
     * One-shot error message for the UI snackbar.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    
    @javax.inject.Inject()
    public DriverViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignOutUseCase signOut, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.StartTripUseCase startTripUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.EndTripUseCase endTripUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.ObserveTripUseCase observeTripUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetDriverByAuthUidUseCase getDriverByAuthUid, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetBusByIdUseCase getBusById, @org.jetbrains.annotations.NotNull()
    com.routex.app.driver.domain.repository.DriverRepository driverRepository, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.network.NetworkMonitor networkMonitor) {
        super();
    }
    
    /**
     * True when the device has no internet connectivity.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOffline() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.domain.model.User> getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.Driver> getDriverRecord() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.Route> getAssignedRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isTracking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.Trip> getActiveTrip() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    private final void loadUser() {
    }
    
    private final void loadDriverRecord(java.lang.String uid) {
    }
    
    private final void resolveAssignedRoute(java.lang.String routeId) {
    }
    
    private final void resolveBusNumber(java.lang.String busId) {
    }
    
    public final void clearError() {
    }
    
    /**
     * Starts a new trip using the admin-assigned bus and route.
     * No manual input needed — everything comes from the driver's Firestore record.
     */
    public final void startTrip() {
    }
    
    /**
     * Ends the active trip in Firestore, then stops the tracking service.
     */
    public final void endTrip() {
    }
    
    private final void observeTrip(java.lang.String tripId) {
    }
    
    private final void stopTrackingService() {
    }
    
    /**
     * Safely signs the driver out, ending any active trip first.
     */
    public final void signOut(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
}