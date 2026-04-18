package com.routex.app.eta.domain.usecase;

import com.routex.app.eta.domain.model.EtaResult;
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
import com.routex.app.student.domain.model.BusStop;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

/**
 * Bridges the live bus location stream from Firebase RTDB with [CalculateEtaUseCase],
 * emitting a fresh [EtaResult] every time the driver uploads a new location.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/routex/app/eta/domain/usecase/ObserveEtaUseCase;", "", "getBusLocation", "Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;", "calculateEta", "Lcom/routex/app/eta/domain/usecase/CalculateEtaUseCase;", "(Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;Lcom/routex/app/eta/domain/usecase/CalculateEtaUseCase;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/routex/app/eta/domain/model/EtaResult;", "routeId", "", "stop", "Lcom/routex/app/student/domain/model/BusStop;", "app_debug"})
public final class ObserveEtaUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.eta.domain.usecase.CalculateEtaUseCase calculateEta = null;
    
    @javax.inject.Inject()
    public ObserveEtaUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.usecase.GetBusLocationUseCase getBusLocation, @org.jetbrains.annotations.NotNull()
    com.routex.app.eta.domain.usecase.CalculateEtaUseCase calculateEta) {
        super();
    }
    
    /**
     * @param routeId  Firebase RTDB key for this route's bus.
     * @param stop     The student's selected boarding stop.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.routex.app.eta.domain.model.EtaResult> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.model.BusStop stop) {
        return null;
    }
}