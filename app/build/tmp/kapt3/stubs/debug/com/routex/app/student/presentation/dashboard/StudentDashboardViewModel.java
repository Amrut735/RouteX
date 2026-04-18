package com.routex.app.student.presentation.dashboard;

import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.network.NetworkMonitor;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0002J\u0014\u0010\u0006\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020 0$R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00110\u00140\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/routex/app/student/presentation/dashboard/StudentDashboardViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "getRoutes", "Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;", "getCurrentUser", "Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;", "signOut", "Lcom/routex/app/auth/domain/usecase/SignOutUseCase;", "observeAllTrips", "Lcom/routex/app/trips/domain/usecase/ObserveAllTripsUseCase;", "networkMonitor", "Lcom/routex/app/core/network/NetworkMonitor;", "(Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;Lcom/routex/app/auth/domain/usecase/SignOutUseCase;Lcom/routex/app/trips/domain/usecase/ObserveAllTripsUseCase;Lcom/routex/app/core/network/NetworkMonitor;)V", "_currentUser", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/auth/domain/model/User;", "_liveTrips", "", "Lcom/routex/app/trips/domain/model/Trip;", "_routesState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/student/domain/model/Route;", "currentUser", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "isOffline", "", "liveTrips", "getLiveTrips", "routesState", "getRoutesState", "loadUser", "", "observeLiveTrips", "observeRoutes", "onDone", "Lkotlin/Function0;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StudentDashboardViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignOutUseCase signOut = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase observeAllTrips = null;
    
    /**
     * True when the device has no internet. Drives the OfflineBanner.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOffline = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.domain.model.User> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.domain.model.User> currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> _routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> routesState = null;
    
    /**
     * Active (RUNNING / DELAYED) trips visible to the student.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.trips.domain.model.Trip>> _liveTrips = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.trips.domain.model.Trip>> liveTrips = null;
    
    @javax.inject.Inject()
    public StudentDashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignOutUseCase signOut, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase observeAllTrips, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.network.NetworkMonitor networkMonitor) {
        super();
    }
    
    /**
     * True when the device has no internet. Drives the OfflineBanner.
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
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> getRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.trips.domain.model.Trip>> getLiveTrips() {
        return null;
    }
    
    private final void loadUser() {
    }
    
    private final void observeRoutes() {
    }
    
    private final void observeLiveTrips() {
    }
    
    public final void signOut(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
}