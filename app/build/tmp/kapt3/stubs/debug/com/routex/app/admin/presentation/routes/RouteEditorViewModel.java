package com.routex.app.admin.presentation.routes;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.usecase.AddRouteUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.UpdateRouteUseCase;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u00100\u001a\u00020\u0015J\u0006\u00101\u001a\u00020\u0015J\u0016\u00102\u001a\u0002032\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0002J\u001e\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\rJ(\u00108\u001a\u0002032\u0006\u00109\u001a\u0002032\u0006\u0010:\u001a\u0002032\u0006\u0010;\u001a\u0002032\u0006\u0010<\u001a\u000203H\u0002J\u0010\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\rH\u0002J\u000e\u0010?\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\rJ\u000e\u0010A\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\rJ\u000e\u0010B\u001a\u00020\u00152\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\rJ\u000e\u0010F\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\rJ\u000e\u0010G\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\rJ\u000e\u0010H\u001a\u00020\u00152\u0006\u0010I\u001a\u00020\rJ\u0006\u0010J\u001a\u00020\u0015J\u0014\u0010K\u001a\u00020\u00152\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00150MJ\u000e\u0010N\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u0010J\u0006\u0010P\u001a\u00020\u0015J\u0006\u0010Q\u001a\u00020\u0015R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001a0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0010\u0010!\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001eR\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001a0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006R"}, d2 = {"Lcom/routex/app/admin/presentation/routes/RouteEditorViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "addRoute", "Lcom/routex/app/admin/domain/usecase/AddRouteUseCase;", "updateRoute", "Lcom/routex/app/admin/domain/usecase/UpdateRouteUseCase;", "getAllRoutes", "Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/admin/domain/usecase/AddRouteUseCase;Lcom/routex/app/admin/domain/usecase/UpdateRouteUseCase;Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;)V", "_busNumber", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_driverName", "_editingStop", "Lcom/routex/app/admin/presentation/routes/MapStop;", "_routeName", "_routeNumber", "_saveState", "Lcom/routex/app/core/utils/UiState;", "", "_scheduleTime", "_showFormPanel", "", "_stops", "", "busNumber", "Lkotlinx/coroutines/flow/StateFlow;", "getBusNumber", "()Lkotlinx/coroutines/flow/StateFlow;", "driverName", "getDriverName", "editRouteId", "editingStop", "getEditingStop", "routeName", "getRouteName", "routeNumber", "getRouteNumber", "saveState", "getSaveState", "scheduleTime", "getScheduleTime", "showFormPanel", "getShowFormPanel", "stops", "getStops", "cancelEditStop", "clearAllStops", "computeApproxDistanceKm", "", "confirmRenameStop", "id", "newName", "arrivalTime", "haversine", "lat1", "lon1", "lat2", "lon2", "loadExistingRoute", "routeId", "onBusNumberChange", "v", "onDriverNameChange", "onMapTapped", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "onRouteNameChange", "onRouteNumberChange", "onScheduleTimeChange", "removeStop", "stopId", "resetSaveState", "saveRoute", "onSuccess", "Lkotlin/Function0;", "startEditStop", "stop", "toggleFormPanel", "undoLastStop", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RouteEditorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AddRouteUseCase addRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.UpdateRouteUseCase updateRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String editRouteId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.admin.presentation.routes.MapStop>> _stops = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.presentation.routes.MapStop>> stops = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _routeNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> routeNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _routeName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> routeName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _driverName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> driverName = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _scheduleTime = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> scheduleTime = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.presentation.routes.MapStop> _editingStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.routes.MapStop> editingStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> _saveState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> saveState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showFormPanel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showFormPanel = null;
    
    @javax.inject.Inject()
    public RouteEditorViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AddRouteUseCase addRoute, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.UpdateRouteUseCase updateRoute, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.presentation.routes.MapStop>> getStops() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getRouteNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getRouteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getDriverName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getScheduleTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.routes.MapStop> getEditingStop() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> getSaveState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowFormPanel() {
        return null;
    }
    
    private final void loadExistingRoute(java.lang.String routeId) {
    }
    
    public final void onMapTapped(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLng latLng) {
    }
    
    public final void removeStop(@org.jetbrains.annotations.NotNull()
    java.lang.String stopId) {
    }
    
    public final void startEditStop(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.routes.MapStop stop) {
    }
    
    public final void confirmRenameStop(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String newName, @org.jetbrains.annotations.NotNull()
    java.lang.String arrivalTime) {
    }
    
    public final void cancelEditStop() {
    }
    
    public final void onRouteNumberChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onRouteNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onBusNumberChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onDriverNameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void onScheduleTimeChange(@org.jetbrains.annotations.NotNull()
    java.lang.String v) {
    }
    
    public final void toggleFormPanel() {
    }
    
    public final void undoLastStop() {
    }
    
    public final void clearAllStops() {
    }
    
    public final void saveRoute(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    public final void resetSaveState() {
    }
    
    private final double computeApproxDistanceKm(java.util.List<com.routex.app.admin.presentation.routes.MapStop> stops) {
        return 0.0;
    }
    
    private final double haversine(double lat1, double lon1, double lat2, double lon2) {
        return 0.0;
    }
}