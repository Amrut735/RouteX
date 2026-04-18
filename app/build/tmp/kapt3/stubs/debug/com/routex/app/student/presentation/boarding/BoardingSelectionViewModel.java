package com.routex.app.student.presentation.boarding;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016\u00a8\u0006\u001c"}, d2 = {"Lcom/routex/app/student/presentation/boarding/BoardingSelectionViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getRouteById", "Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;", "locationProvider", "Lcom/routex/app/core/location/LocationProvider;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;Lcom/routex/app/core/location/LocationProvider;)V", "_routeState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/student/domain/model/Route;", "_userLocation", "Landroid/location/Location;", "routeId", "", "getRouteId", "()Ljava/lang/String;", "routeState", "Lkotlinx/coroutines/flow/StateFlow;", "getRouteState", "()Lkotlinx/coroutines/flow/StateFlow;", "userLocation", "getUserLocation", "loadRoute", "", "startLocationStream", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BoardingSelectionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.location.LocationProvider locationProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> _routeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> routeState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<android.location.Location> _userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<android.location.Location> userLocation = null;
    
    @javax.inject.Inject()
    public BoardingSelectionViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.location.LocationProvider locationProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.student.domain.model.Route>> getRouteState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<android.location.Location> getUserLocation() {
        return null;
    }
    
    private final void loadRoute() {
    }
    
    private final void startLocationStream() {
    }
}