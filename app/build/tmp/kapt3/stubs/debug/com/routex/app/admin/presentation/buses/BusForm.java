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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/routex/app/admin/presentation/buses/BusForm;", "", "id", "", "number", "model", "capacity", "licensePlate", "assignedRouteId", "assignedDriverId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAssignedDriverId", "()Ljava/lang/String;", "getAssignedRouteId", "getCapacity", "getId", "getLicensePlate", "getModel", "getNumber", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BusForm {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String number = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String model = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String capacity = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String licensePlate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String assignedRouteId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String assignedDriverId = null;
    
    public BusForm(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String capacity, @org.jetbrains.annotations.NotNull()
    java.lang.String licensePlate, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedDriverId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCapacity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLicensePlate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAssignedRouteId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAssignedDriverId() {
        return null;
    }
    
    public BusForm() {
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
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.admin.presentation.buses.BusForm copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String capacity, @org.jetbrains.annotations.NotNull()
    java.lang.String licensePlate, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedRouteId, @org.jetbrains.annotations.NotNull()
    java.lang.String assignedDriverId) {
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