package com.routex.app.student.presentation.boarding;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;
import com.routex.app.core.utils.Resource;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.simulator.EtaCalculator;
import com.routex.app.student.domain.simulator.LocalRouteSimulator;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001b\u001a\u00020\u001cH\u0002J.\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0006\u0010!\u001a\u00020\fH\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/routex/app/student/presentation/boarding/AvailableBusesViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getRouteById", "Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;)V", "_buses", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/routex/app/student/presentation/boarding/BoardingBusModel;", "_targetStop", "Lcom/routex/app/student/domain/model/BusStop;", "buses", "Lkotlinx/coroutines/flow/StateFlow;", "getBuses", "()Lkotlinx/coroutines/flow/StateFlow;", "routeId", "", "getRouteId", "()Ljava/lang/String;", "simulator", "Lcom/routex/app/student/domain/simulator/LocalRouteSimulator;", "stopId", "getStopId", "targetStop", "getTargetStop", "loadData", "", "startSimulation", "rId", "rNum", "allStops", "target", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AvailableBusesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String stopId = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.simulator.LocalRouteSimulator simulator = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.model.BusStop> _targetStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> targetStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.student.presentation.boarding.BoardingBusModel>> _buses = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.student.presentation.boarding.BoardingBusModel>> buses = null;
    
    @javax.inject.Inject()
    public AvailableBusesViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRouteById) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStopId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> getTargetStop() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.student.presentation.boarding.BoardingBusModel>> getBuses() {
        return null;
    }
    
    private final void loadData() {
    }
    
    private final void startSimulation(java.lang.String rId, java.lang.String rNum, java.util.List<com.routex.app.student.domain.model.BusStop> allStops, com.routex.app.student.domain.model.BusStop target) {
    }
}