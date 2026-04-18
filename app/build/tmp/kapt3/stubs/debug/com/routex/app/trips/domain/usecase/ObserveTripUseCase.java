package com.routex.app.trips.domain.usecase;

import com.routex.app.core.utils.Resource;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.repository.TripRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/routex/app/trips/domain/usecase/ObserveTripUseCase;", "", "repository", "Lcom/routex/app/trips/domain/repository/TripRepository;", "(Lcom/routex/app/trips/domain/repository/TripRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/routex/app/trips/domain/model/Trip;", "tripId", "", "app_debug"})
public final class ObserveTripUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.repository.TripRepository repository = null;
    
    @javax.inject.Inject()
    public ObserveTripUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.repository.TripRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.routex.app.trips.domain.model.Trip> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String tripId) {
        return null;
    }
}