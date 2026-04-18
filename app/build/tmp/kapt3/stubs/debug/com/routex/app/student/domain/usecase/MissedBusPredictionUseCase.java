package com.routex.app.student.domain.usecase;

import com.routex.app.eta.domain.model.BusStatus;
import com.routex.app.eta.domain.model.EtaResult;
import javax.inject.Inject;

/**
 * Predicts whether the student is likely to miss their bus, and suggests
 * the next available action.
 *
 * Inputs:
 *  - [etaResult]        : current ETA snapshot from [ObserveEtaUseCase]
 *  - [walkingTimeMin]   : estimated walking time to the boarding stop (default 5 min)
 *  - [bufferMin]        : extra time margin the student wants (default 2 min)
 *
 * Output: [MissedBusPrediction]
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0086\u0002\u00a8\u0006\n"}, d2 = {"Lcom/routex/app/student/domain/usecase/MissedBusPredictionUseCase;", "", "()V", "invoke", "Lcom/routex/app/student/domain/usecase/MissedBusPrediction;", "etaResult", "Lcom/routex/app/eta/domain/model/EtaResult;", "walkingTimeMin", "", "bufferMin", "app_debug"})
public final class MissedBusPredictionUseCase {
    
    @javax.inject.Inject()
    public MissedBusPredictionUseCase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.student.domain.usecase.MissedBusPrediction invoke(@org.jetbrains.annotations.NotNull()
    com.routex.app.eta.domain.model.EtaResult etaResult, float walkingTimeMin, float bufferMin) {
        return null;
    }
}