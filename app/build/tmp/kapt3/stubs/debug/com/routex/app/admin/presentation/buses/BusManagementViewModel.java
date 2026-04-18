package com.routex.app.admin.presentation.buses;

import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.admin.domain.usecase.AddBusUseCase;
import com.routex.app.admin.domain.usecase.AddDriverUseCase;
import com.routex.app.admin.domain.usecase.AssignBusUseCase;
import com.routex.app.admin.domain.usecase.AssignDriverUseCase;
import com.routex.app.admin.domain.usecase.DeleteBusUseCase;
import com.routex.app.admin.domain.usecase.DeleteDriverUseCase;
import com.routex.app.admin.domain.usecase.GetAllBusesUseCase;
import com.routex.app.admin.domain.usecase.GetAllDriversUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.SeedBusesUseCase;
import com.routex.app.admin.domain.usecase.SeedDriversUseCase;
import com.routex.app.admin.domain.usecase.SeedRoutesUseCase;
import com.routex.app.admin.domain.usecase.UpdateBusUseCase;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\\Bg\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010B\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020DJ\u001e\u0010G\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020D2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DJ\u000e\u0010\n\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020DJ\u0006\u0010H\u001a\u00020\u001eJ\u0006\u0010I\u001a\u00020\u001eJ\b\u0010J\u001a\u00020\u001eH\u0002J\b\u0010K\u001a\u00020\u001eH\u0002J\b\u0010L\u001a\u00020\u001eH\u0002J\u000e\u0010M\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020 J\u000e\u0010O\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020%J\u0006\u0010P\u001a\u00020\u001eJ\u0006\u0010Q\u001a\u00020\u001eJ\u000e\u0010R\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020#J\u0006\u0010T\u001a\u00020\u001eJ\u0006\u0010U\u001a\u00020\u001eJ\u0006\u0010V\u001a\u00020\u001eJ2\u0010W\u001a\b\u0012\u0004\u0012\u0002HY0X\"\u0004\b\u0000\u0010Y*\b\u0012\u0004\u0012\u00020#0X2\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u0002HY0[H\u0002R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\'0\"0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\"0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0/\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020 0/\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00101R#\u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u001d0/\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00101R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020%0/\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00101R#\u00108\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\'0\"0\u001d0/\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00101R\u0019\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0/\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00101R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010<\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\"0\u001d0/\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u00101R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020,0/\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00101R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020,0/\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u00101R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006]"}, d2 = {"Lcom/routex/app/admin/presentation/buses/BusManagementViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "getAllBuses", "Lcom/routex/app/admin/domain/usecase/GetAllBusesUseCase;", "getAllRoutes", "Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;", "addBus", "Lcom/routex/app/admin/domain/usecase/AddBusUseCase;", "updateBus", "Lcom/routex/app/admin/domain/usecase/UpdateBusUseCase;", "deleteBus", "Lcom/routex/app/admin/domain/usecase/DeleteBusUseCase;", "assignBus", "Lcom/routex/app/admin/domain/usecase/AssignBusUseCase;", "addDriverUseCase", "Lcom/routex/app/admin/domain/usecase/AddDriverUseCase;", "getAllDriversUseCase", "Lcom/routex/app/admin/domain/usecase/GetAllDriversUseCase;", "assignDriverUseCase", "Lcom/routex/app/admin/domain/usecase/AssignDriverUseCase;", "seedDriversUseCase", "Lcom/routex/app/admin/domain/usecase/SeedDriversUseCase;", "seedBusesUseCase", "Lcom/routex/app/admin/domain/usecase/SeedBusesUseCase;", "seedRoutesUseCase", "Lcom/routex/app/admin/domain/usecase/SeedRoutesUseCase;", "(Lcom/routex/app/admin/domain/usecase/GetAllBusesUseCase;Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;Lcom/routex/app/admin/domain/usecase/AddBusUseCase;Lcom/routex/app/admin/domain/usecase/UpdateBusUseCase;Lcom/routex/app/admin/domain/usecase/DeleteBusUseCase;Lcom/routex/app/admin/domain/usecase/AssignBusUseCase;Lcom/routex/app/admin/domain/usecase/AddDriverUseCase;Lcom/routex/app/admin/domain/usecase/GetAllDriversUseCase;Lcom/routex/app/admin/domain/usecase/AssignDriverUseCase;Lcom/routex/app/admin/domain/usecase/SeedDriversUseCase;Lcom/routex/app/admin/domain/usecase/SeedBusesUseCase;Lcom/routex/app/admin/domain/usecase/SeedRoutesUseCase;)V", "_actionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "", "_busForm", "Lcom/routex/app/admin/presentation/buses/BusForm;", "_busesState", "", "Lcom/routex/app/admin/domain/model/Bus;", "_driverForm", "Lcom/routex/app/admin/presentation/buses/BusManagementViewModel$DriverForm;", "_driversState", "Lcom/routex/app/admin/domain/model/Driver;", "_editingBus", "_routesState", "Lcom/routex/app/admin/domain/model/BusRoute;", "_showBusDialog", "", "_showDriverDialog", "actionState", "Lkotlinx/coroutines/flow/StateFlow;", "getActionState", "()Lkotlinx/coroutines/flow/StateFlow;", "busForm", "getBusForm", "busesState", "getBusesState", "driverForm", "getDriverForm", "driversState", "getDriversState", "editingBus", "getEditingBus", "routesState", "getRoutesState", "showBusDialog", "getShowBusDialog", "showDriverDialog", "getShowDriverDialog", "assignBusToRoute", "busId", "", "routeId", "driverId", "assignDriverToBus", "dismissBusDialog", "dismissDriverDialog", "observeBuses", "observeDrivers", "observeRoutes", "onBusFormChange", "form", "onDriverFormChange", "openAddBusDialog", "openAddDriverDialog", "openEditBusDialog", "bus", "resetActionState", "submitBus", "submitDriver", "map", "Lcom/routex/app/core/utils/Resource;", "T", "transform", "Lkotlin/Function1;", "DriverForm", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class BusManagementViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllBusesUseCase getAllBuses = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AddBusUseCase addBus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.UpdateBusUseCase updateBus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.DeleteBusUseCase deleteBus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AssignBusUseCase assignBus = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AddDriverUseCase addDriverUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllDriversUseCase getAllDriversUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AssignDriverUseCase assignDriverUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.SeedDriversUseCase seedDriversUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.SeedBusesUseCase seedBusesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.SeedRoutesUseCase seedRoutesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Bus>>> _busesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Bus>>> busesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> _routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showBusDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showBusDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.domain.model.Bus> _editingBus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.Bus> editingBus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.presentation.buses.BusForm> _busForm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.buses.BusForm> busForm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showDriverDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showDriverDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm> _driverForm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm> driverForm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Driver>>> _driversState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Driver>>> driversState = null;
    
    @javax.inject.Inject()
    public BusManagementViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllBusesUseCase getAllBuses, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AddBusUseCase addBus, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.UpdateBusUseCase updateBus, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.DeleteBusUseCase deleteBus, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AssignBusUseCase assignBus, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AddDriverUseCase addDriverUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllDriversUseCase getAllDriversUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AssignDriverUseCase assignDriverUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.SeedDriversUseCase seedDriversUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.SeedBusesUseCase seedBusesUseCase, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.SeedRoutesUseCase seedRoutesUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Bus>>> getBusesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> getRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> getActionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowBusDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.Bus> getEditingBus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.buses.BusForm> getBusForm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowDriverDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm> getDriverForm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.Driver>>> getDriversState() {
        return null;
    }
    
    private final void observeBuses() {
    }
    
    private final void observeDrivers() {
    }
    
    private final void observeRoutes() {
    }
    
    public final void openAddBusDialog() {
    }
    
    public final void openEditBusDialog(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.Bus bus) {
    }
    
    public final void dismissBusDialog() {
    }
    
    public final void onBusFormChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.buses.BusForm form) {
    }
    
    public final void submitBus() {
    }
    
    public final void deleteBus(@org.jetbrains.annotations.NotNull()
    java.lang.String busId) {
    }
    
    public final void assignDriverToBus(@org.jetbrains.annotations.NotNull()
    java.lang.String driverId, @org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId) {
    }
    
    public final void assignBusToRoute(@org.jetbrains.annotations.NotNull()
    java.lang.String busId, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String driverId) {
    }
    
    public final void resetActionState() {
    }
    
    public final void openAddDriverDialog() {
    }
    
    public final void dismissDriverDialog() {
    }
    
    public final void onDriverFormChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm form) {
    }
    
    public final void submitDriver() {
    }
    
    private final <T extends java.lang.Object>com.routex.app.core.utils.Resource<T> map(com.routex.app.core.utils.Resource<com.routex.app.admin.domain.model.Bus> $this$map, kotlin.jvm.functions.Function1<? super com.routex.app.admin.domain.model.Bus, ? extends T> transform) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/routex/app/admin/presentation/buses/BusManagementViewModel$DriverForm;", "", "name", "", "email", "driverCode", "phoneNumber", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDriverCode", "()Ljava/lang/String;", "getEmail", "getName", "getPhoneNumber", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class DriverForm {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String email = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String driverCode = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String phoneNumber = null;
        
        public DriverForm(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
        java.lang.String phoneNumber) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDriverCode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPhoneNumber() {
            return null;
        }
        
        public DriverForm() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        java.lang.String driverCode, @org.jetbrains.annotations.NotNull()
        java.lang.String phoneNumber) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}