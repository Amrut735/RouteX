package com.routex.app.trips.domain.usecase;

import com.routex.app.core.utils.Resource;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.repository.TripRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0086B\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/routex/app/trips/domain/usecase/StartTripUseCase;", "", "repository", "Lcom/routex/app/trips/domain/repository/TripRepository;", "(Lcom/routex/app/trips/domain/repository/TripRepository;)V", "invoke", "Lcom/routex/app/core/utils/Resource;", "Lcom/routex/app/trips/domain/model/Trip;", "trip", "(Lcom/routex/app/trips/domain/model/Trip;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class StartTripUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.repository.TripRepository repository = null;
    
    @javax.inject.Inject()
    public StartTripUseCase(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.repository.TripRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.model.Trip trip, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.routex.app.core.utils.Resource<com.routex.app.trips.domain.model.Trip>> $completion) {
        return null;
    }
}