package com.routex.app.admin.presentation.emergency;

import androidx.lifecycle.ViewModel;
import com.routex.app.admin.domain.model.EmergencyAlert;
import com.routex.app.admin.domain.model.EmergencyType;
import com.routex.app.admin.domain.usecase.ObserveActiveAlertsUseCase;
import com.routex.app.admin.domain.usecase.ResolveAlertUseCase;
import com.routex.app.admin.domain.usecase.SendEmergencyAlertUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010$\u001a\u00020\u0015J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0010J\u0006\u0010&\u001a\u00020\u0015J\u0006\u0010\'\u001a\u00020\u0015J\u000e\u0010\u0006\u001a\u00020\u00152\u0006\u0010(\u001a\u00020)J\u000e\u0010\u0002\u001a\u00020\u00152\u0006\u0010*\u001a\u00020)R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\r0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b\u00a8\u0006+"}, d2 = {"Lcom/routex/app/admin/presentation/emergency/EmergencyViewModel;", "Landroidx/lifecycle/ViewModel;", "sendAlert", "Lcom/routex/app/admin/domain/usecase/SendEmergencyAlertUseCase;", "observeAlerts", "Lcom/routex/app/admin/domain/usecase/ObserveActiveAlertsUseCase;", "resolveAlert", "Lcom/routex/app/admin/domain/usecase/ResolveAlertUseCase;", "getAllRoutes", "Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;", "(Lcom/routex/app/admin/domain/usecase/SendEmergencyAlertUseCase;Lcom/routex/app/admin/domain/usecase/ObserveActiveAlertsUseCase;Lcom/routex/app/admin/domain/usecase/ResolveAlertUseCase;Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;)V", "_activeAlerts", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/routex/app/admin/domain/model/EmergencyAlert;", "_form", "Lcom/routex/app/admin/presentation/emergency/AlertForm;", "_routes", "Lcom/routex/app/admin/domain/model/BusRoute;", "_sendState", "Lcom/routex/app/core/utils/UiState;", "", "_showDialog", "", "activeAlerts", "Lkotlinx/coroutines/flow/StateFlow;", "getActiveAlerts", "()Lkotlinx/coroutines/flow/StateFlow;", "form", "getForm", "routes", "getRoutes", "sendState", "getSendState", "showDialog", "getShowDialog", "closeDialog", "onFormChange", "openDialog", "resetSendState", "alertId", "", "adminName", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EmergencyViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.SendEmergencyAlertUseCase sendAlert = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.ObserveActiveAlertsUseCase observeAlerts = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.ResolveAlertUseCase resolveAlert = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> _activeAlerts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> activeAlerts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.admin.domain.model.BusRoute>> _routes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.domain.model.BusRoute>> routes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.presentation.emergency.AlertForm> _form = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.emergency.AlertForm> form = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> _sendState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> sendState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showDialog = null;
    
    @javax.inject.Inject()
    public EmergencyViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.SendEmergencyAlertUseCase sendAlert, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.ObserveActiveAlertsUseCase observeAlerts, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.ResolveAlertUseCase resolveAlert, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.domain.model.EmergencyAlert>> getActiveAlerts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.routex.app.admin.domain.model.BusRoute>> getRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.emergency.AlertForm> getForm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> getSendState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowDialog() {
        return null;
    }
    
    public final void openDialog() {
    }
    
    public final void closeDialog() {
    }
    
    public final void onFormChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.emergency.AlertForm form) {
    }
    
    public final void sendAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String adminName) {
    }
    
    public final void resolveAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String alertId) {
    }
    
    public final void resetSendState() {
    }
}