package com.routex.app.maps.domain.usecase;

import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.repository.MapsRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/routex/app/maps/domain/usecase/GetBusLocationUseCase;", "", "repository", "Lcom/routex/app/maps/domain/repository/MapsRepository;", "(Lcom/routex/app/maps/domain/repository/MapsRepository;)V", "allActive", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "invoke", "routeId", "", "app_debug"})
public final class GetBusLocationUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.maps.domain.repository.MapsRepository repository = null;
    
    @javax.inject.Inject()
    public GetBusLocationUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.maps.domain.repository.MapsRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.routex.app.maps.domain.model.BusLocationUpdate> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId) {
        return null;
    }
    
    /**
     * All online buses — used for the "all routes" map view.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.routex.app.maps.domain.model.BusLocationUpdate>> allActive() {
        return null;
    }
}