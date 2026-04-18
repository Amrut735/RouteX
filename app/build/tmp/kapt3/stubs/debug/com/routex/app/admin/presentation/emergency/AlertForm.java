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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/routex/app/admin/presentation/emergency/AlertForm;", "", "selectedRouteId", "", "busNumber", "message", "type", "Lcom/routex/app/admin/domain/model/EmergencyType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/routex/app/admin/domain/model/EmergencyType;)V", "getBusNumber", "()Ljava/lang/String;", "getMessage", "getSelectedRouteId", "getType", "()Lcom/routex/app/admin/domain/model/EmergencyType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AlertForm {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String selectedRouteId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.model.EmergencyType type = null;
    
    public AlertForm(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.EmergencyType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.admin.domain.model.EmergencyType getType() {
        return null;
    }
    
    public AlertForm() {
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
    public final com.routex.app.admin.domain.model.EmergencyType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.admin.presentation.emergency.AlertForm copy(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.EmergencyType type) {
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