package com.routex.app.eta.presentation;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.routex.app.core.data.UserPreferencesRepository;
import com.routex.app.core.notification.FcmTokenRepository;
import com.routex.app.core.notification.NotificationHelper;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.eta.domain.model.BusStatus;
import com.routex.app.eta.domain.model.EtaResult;
import com.routex.app.eta.domain.usecase.ObserveEtaUseCase;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import com.routex.app.student.domain.usecase.MissedBusPrediction;
import com.routex.app.student.domain.usecase.MissedBusPredictionUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010#\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\b\u00109\u001a\u00020:H\u0002J\b\u0010;\u001a\u00020:H\u0014J\u000e\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u00020\u001bJ\u0016\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020\u001eJ\u0016\u0010A\u001a\u00020:2\u0006\u0010=\u001a\u00020\u001bH\u0082@\u00a2\u0006\u0002\u0010BJ\u0018\u0010C\u001a\u00020:2\u0006\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020\u001eH\u0002J\u0018\u0010D\u001a\u00020:2\u0006\u0010E\u001a\u00020\u00162\u0006\u0010?\u001a\u00020&H\u0002J\u001e\u0010F\u001a\u0004\u0018\u00010:2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0082@\u00a2\u0006\u0002\u0010HR\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020&0(X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\"\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010$R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00150\"\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020&0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u001a\u00103\u001a\u000204X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108\u00a8\u0006I"}, d2 = {"Lcom/routex/app/eta/presentation/EtaViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "getRoute", "Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;", "getRoutes", "Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;", "observeEta", "Lcom/routex/app/eta/domain/usecase/ObserveEtaUseCase;", "prefs", "Lcom/routex/app/core/data/UserPreferencesRepository;", "notificationHelper", "Lcom/routex/app/core/notification/NotificationHelper;", "fcmTokenRepository", "Lcom/routex/app/core/notification/FcmTokenRepository;", "missedBusPrediction", "Lcom/routex/app/student/domain/usecase/MissedBusPredictionUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/routex/app/student/domain/usecase/GetRouteByIdUseCase;Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;Lcom/routex/app/eta/domain/usecase/ObserveEtaUseCase;Lcom/routex/app/core/data/UserPreferencesRepository;Lcom/routex/app/core/notification/NotificationHelper;Lcom/routex/app/core/notification/FcmTokenRepository;Lcom/routex/app/student/domain/usecase/MissedBusPredictionUseCase;)V", "_etaState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/eta/domain/model/EtaResult;", "_prediction", "Lcom/routex/app/student/domain/usecase/MissedBusPrediction;", "_routesState", "", "Lcom/routex/app/student/domain/model/Route;", "_selectedRoute", "_selectedStop", "Lcom/routex/app/student/domain/model/BusStop;", "etaJob", "Lkotlinx/coroutines/Job;", "etaState", "Lkotlinx/coroutines/flow/StateFlow;", "getEtaState", "()Lkotlinx/coroutines/flow/StateFlow;", "navRouteId", "", "notifiedThresholds", "", "prediction", "getPrediction", "routesState", "getRoutesState", "savedBoardingStopName", "getSavedBoardingStopName", "selectedRoute", "getSelectedRoute", "selectedStop", "getSelectedStop", "walkingTimeMin", "", "getWalkingTimeMin", "()F", "setWalkingTimeMin", "(F)V", "loadRoutes", "", "onCleared", "onRouteSelected", "route", "onStopSelected", "routeId", "stop", "restoreOrSelectFirstStop", "(Lcom/routex/app/student/domain/model/Route;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startObservingEta", "triggerNotificationsIfNeeded", "eta", "tryRestoreFromPrefs", "routes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EtaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.eta.domain.usecase.ObserveEtaUseCase observeEta = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.data.UserPreferencesRepository prefs = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.notification.NotificationHelper notificationHelper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.notification.FcmTokenRepository fcmTokenRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.MissedBusPredictionUseCase missedBusPrediction = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String navRouteId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> _routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.model.Route> _selectedRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.Route> selectedRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.model.BusStop> _selectedStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> selectedStop = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.eta.domain.model.EtaResult>> _etaState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.eta.domain.model.EtaResult>> etaState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.student.domain.usecase.MissedBusPrediction> _prediction = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.usecase.MissedBusPrediction> prediction = null;
    private float walkingTimeMin = 5.0F;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> savedBoardingStopName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> notifiedThresholds = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job etaJob;
    
    @javax.inject.Inject()
    public EtaViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRouteByIdUseCase getRoute, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.eta.domain.usecase.ObserveEtaUseCase observeEta, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.data.UserPreferencesRepository prefs, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.notification.NotificationHelper notificationHelper, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.notification.FcmTokenRepository fcmTokenRepository, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.MissedBusPredictionUseCase missedBusPrediction) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> getRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.Route> getSelectedRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.model.BusStop> getSelectedStop() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.eta.domain.model.EtaResult>> getEtaState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.student.domain.usecase.MissedBusPrediction> getPrediction() {
        return null;
    }
    
    public final float getWalkingTimeMin() {
        return 0.0F;
    }
    
    public final void setWalkingTimeMin(float p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSavedBoardingStopName() {
        return null;
    }
    
    private final void loadRoutes() {
    }
    
    private final java.lang.Object restoreOrSelectFirstStop(com.routex.app.student.domain.model.Route route, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object tryRestoreFromPrefs(java.util.List<com.routex.app.student.domain.model.Route> routes, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void onRouteSelected(@org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.model.Route route) {
    }
    
    public final void onStopSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.model.BusStop stop) {
    }
    
    private final void startObservingEta(java.lang.String routeId, com.routex.app.student.domain.model.BusStop stop) {
    }
    
    private final void triggerNotificationsIfNeeded(com.routex.app.eta.domain.model.EtaResult eta, java.lang.String routeId) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}