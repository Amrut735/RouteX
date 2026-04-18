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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010$\u001a\u00020\fJ\u000e\u0010\u0006\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u0010J\u0006\u0010&\u001a\u00020\fJ\b\u0010\'\u001a\u00020\fH\u0002J\u000e\u0010(\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010)\u001a\u00020\fJ\u0006\u0010*\u001a\u00020\fJ\u0006\u0010+\u001a\u00020\fJ\u0006\u0010,\u001a\u00020\fR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00120\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R#\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00120\u000b0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019\u00a8\u0006-"}, d2 = {"Lcom/routex/app/admin/presentation/manage/ManageRoutesViewModel;", "Lcom/routex/app/core/base/BaseViewModel;", "getAllRoutes", "Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;", "addRoute", "Lcom/routex/app/admin/domain/usecase/AddRouteUseCase;", "deleteRoute", "Lcom/routex/app/admin/domain/usecase/DeleteRouteUseCase;", "(Lcom/routex/app/admin/domain/usecase/GetAllRoutesUseCase;Lcom/routex/app/admin/domain/usecase/AddRouteUseCase;Lcom/routex/app/admin/domain/usecase/DeleteRouteUseCase;)V", "_actionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "", "_form", "Lcom/routex/app/admin/presentation/manage/AddRouteForm;", "_lastDeletedRoute", "Lcom/routex/app/admin/domain/model/BusRoute;", "_routesState", "", "_showAddDialog", "", "_showUndoEvent", "actionState", "Lkotlinx/coroutines/flow/StateFlow;", "getActionState", "()Lkotlinx/coroutines/flow/StateFlow;", "form", "getForm", "lastDeletedRoute", "getLastDeletedRoute", "routesState", "getRoutesState", "showAddDialog", "getShowAddDialog", "showUndoEvent", "getShowUndoEvent", "clearUndoState", "route", "dismissDialog", "observeRoutes", "onFormChange", "resetActionState", "showDialog", "submitAddRoute", "undoDelete", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ManageRoutesViewModel extends com.routex.app.core.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.AddRouteUseCase addRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.DeleteRouteUseCase deleteRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> _routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> _actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> actionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showAddDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showAddDialog = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.presentation.manage.AddRouteForm> _form = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.manage.AddRouteForm> form = null;
    
    /**
     * The last route that was deleted — held until undo window passes.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.admin.domain.model.BusRoute> _lastDeletedRoute = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.BusRoute> lastDeletedRoute = null;
    
    /**
     * Signals to the UI to show an undo snackbar. Reset after consumed.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _showUndoEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> showUndoEvent = null;
    
    @javax.inject.Inject()
    public ManageRoutesViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAllRoutesUseCase getAllRoutes, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.AddRouteUseCase addRoute, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.DeleteRouteUseCase deleteRoute) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.admin.domain.model.BusRoute>>> getRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<kotlin.Unit>> getActionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowAddDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.presentation.manage.AddRouteForm> getForm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.admin.domain.model.BusRoute> getLastDeletedRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getShowUndoEvent() {
        return null;
    }
    
    private final void observeRoutes() {
    }
    
    public final void onFormChange(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.manage.AddRouteForm form) {
    }
    
    public final void showDialog() {
    }
    
    public final void dismissDialog() {
    }
    
    public final void submitAddRoute() {
    }
    
    /**
     * Deletes a route from Firestore but keeps a copy locally so the user
     * can undo within the snackbar window.
     */
    public final void deleteRoute(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.model.BusRoute route) {
    }
    
    /**
     * Called when the user taps UNDO.
     * Re-inserts the previously deleted route using the same document ID
     * so all existing references (bus assignments, etc.) remain valid.
     */
    public final void undoDelete() {
    }
    
    /**
     * Call after the snackbar has been dismissed without undo.
     */
    public final void clearUndoState() {
    }
    
    public final void resetActionState() {
    }
}