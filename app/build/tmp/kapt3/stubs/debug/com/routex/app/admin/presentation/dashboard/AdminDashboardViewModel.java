package com.routex.app.admin.presentation.dashboard;

import android.location.Location;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.repository.AdminRepository;
import com.routex.app.admin.domain.repository.AdminStats;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.firebase.FirebaseInitializer;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020$H\u0002J\b\u0010&\u001a\u00020$H\u0002J\b\u0010\'\u001a\u00020$H\u0002J\u0006\u0010(\u001a\u00020$J\u001a\u0010)\u001a\u00020$2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020$0+J\u0014\u0010\b\u001a\u00020$2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020$0-R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00110\u00160\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00160\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00110\u00160\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00160\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001d\u00a8\u0006."}, d2 = {"Lcom/routex/app/admin/presentation/dashboard/AdminDashboardViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "getAllRoutes", "Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;", "adminRepository", "Lcom/routex/app/admin/domain/repository/AdminRepository;", "getCurrentUser", "Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;", "signOut", "Lcom/routex/app/auth/domain/usecase/SignOutUseCase;", "firebaseInitializer", "Lcom/routex/app/core/firebase/FirebaseInitializer;", "getBusLocation", "Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;", "(Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;Lcom/routex/app/admin/domain/repository/AdminRepository;Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;Lcom/routex/app/auth/domain/usecase/SignOutUseCase;Lcom/routex/app/core/firebase/FirebaseInitializer;Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;)V", "_approachingCampusBuses", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "_currentUser", "Lcom/routex/app/auth/domain/model/User;", "_recentRoutesState", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/admin/domain/model/BusRoute;", "_statsState", "Lcom/routex/app/admin/domain/repository/AdminStats;", "approachingCampusBuses", "Lkotlinx/coroutines/flow/StateFlow;", "getApproachingCampusBuses", "()Lkotlinx/coroutines/flow/StateFlow;", "currentUser", "recentRoutesState", "getRecentRoutesState", "statsState", "getStatsState", "loadStats", "", "loadUser", "observeApproachingBuses", "observeRoutes", "refresh", "seedDemoData", "onDone", "Lkotlin/Function1;", "", "Lkotlin/Function0;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AdminDashboardViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.repository.AdminRepository adminRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.SignOutUseCase signOut = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.firebase.FirebaseInitializer firebaseInitializer = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.domain.model.User> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.domain.model.User> currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.repository.AdminStats>> _statsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.repository.AdminStats>> statsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> _recentRoutesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> recentRoutesState = null;
    
    /**
     * Buses currently within [CollegeHub.NEAR_CAMPUS_RADIUS_M] of the college.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> _approachingCampusBuses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> approachingCampusBuses = null;
    
    @javax.inject.Inject()
    public AdminDashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.repository.AdminRepository adminRepository, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser, @org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.SignOutUseCase signOut, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.firebase.FirebaseInitializer firebaseInitializer, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.domain.model.User> getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.repository.AdminStats>> getStatsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> getRecentRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> getApproachingCampusBuses() {
        return null;
    }
    
    private final void loadUser() {
    }
    
    private final void loadStats() {
    }
    
    private final void observeRoutes() {
    }
    
    private final void observeApproachingBuses() {
    }
    
    public final void signOut(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final void refresh() {
    }
    
    /**
     * Seeds demo route + RTDB node — call once to bootstrap a fresh project.
     */
    public final void seedDemoData(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onDone) {
    }
}