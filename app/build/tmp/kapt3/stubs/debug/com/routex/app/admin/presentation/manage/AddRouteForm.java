package com.routex.app.admin.presentation.manage;

import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.usecase.AddRouteUseCase;
import com.routex.app.admin.domain.usecase.DeleteRouteUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.core.base.BaseViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006!"}, d2 = {"Lcom/routex/app/admin/presentation/manage/AddRouteForm;", "", "routeNumber", "", "routeName", "startPoint", "endPoint", "busNumber", "driverName", "scheduleTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBusNumber", "()Ljava/lang/String;", "getDriverName", "getEndPoint", "getRouteName", "getRouteNumber", "getScheduleTime", "getStartPoint", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AddRouteForm {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String routeName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String startPoint = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String endPoint = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String busNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String driverName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String scheduleTime = null;
    
    public AddRouteForm(@org.jetbrains.annotations.NotNull()
    java.lang.String routeNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String routeName, @org.jetbrains.annotations.NotNull()
    java.lang.String startPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String endPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String driverName, @org.jetbrains.annotations.NotNull()
    java.lang.String scheduleTime) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRouteName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStartPoint() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEndPoint() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBusNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getScheduleTime() {
        return null;
    }
    
    public AddRouteForm() {
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
    public final com.routex.app.admin.presentation.manage.AddRouteForm copy(@org.jetbrains.annotations.NotNull()
    java.lang.String routeNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String routeName, @org.jetbrains.annotations.NotNull()
    java.lang.String startPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String endPoint, @org.jetbrains.annotations.NotNull()
    java.lang.String busNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String driverName, @org.jetbrains.annotations.NotNull()
    java.lang.String scheduleTime) {
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