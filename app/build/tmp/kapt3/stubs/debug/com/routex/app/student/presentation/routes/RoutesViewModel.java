package com.routex.app.student.presentation.routes;

import androidx.lifecycle.ViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lcom/routex/app/student/presentation/routes/RoutesViewModel;", "Landroidx/lifecycle/ViewModel;", "getRoutes", "Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;", "(Lcom/routex/app/student/domain/usecase/GetRoutesUseCase;)V", "_allRoutes", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/routex/app/student/domain/model/Route;", "_routesState", "Lcom/routex/app/core/utils/UiState;", "_searchQuery", "", "routesState", "Lkotlinx/coroutines/flow/StateFlow;", "getRoutesState", "()Lkotlinx/coroutines/flow/StateFlow;", "searchQuery", "getSearchQuery", "applySearch", "", "query", "routes", "observeRoutes", "observeSearch", "onSearchQueryChange", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RoutesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> _routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> routesState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.student.domain.model.Route>> _allRoutes = null;
    
    @javax.inject.Inject()
    public RoutesViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.student.domain.usecase.GetRoutesUseCase getRoutes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.student.domain.model.Route>>> getRoutesState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    private final void observeRoutes() {
    }
    
    private final void observeSearch() {
    }
    
    private final void applySearch(java.lang.String query, java.util.List<com.routex.app.student.domain.model.Route> routes) {
    }
    
    public final void onSearchQueryChange(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
}