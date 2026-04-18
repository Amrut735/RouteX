package com.routex.app.admin.presentation.analytics;

import androidx.lifecycle.ViewModel;
import com.routex.app.admin.domain.model.AnalyticsSnapshot;
import com.routex.app.admin.domain.usecase.GetAnalyticsUseCase;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/routex/app/admin/presentation/analytics/AnalyticsViewModel;", "Landroidx/lifecycle/ViewModel;", "getAnalytics", "Lcom/routex/app/admin/domain/usecase/GetAnalyticsUseCase;", "(Lcom/routex/app/admin/domain/usecase/GetAnalyticsUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/utils/UiState;", "Lcom/routex/app/admin/domain/model/AnalyticsSnapshot;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "load", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AnalyticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.admin.domain.usecase.GetAnalyticsUseCase getAnalytics = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.model.AnalyticsSnapshot>> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.model.AnalyticsSnapshot>> state = null;
    
    @javax.inject.Inject()
    public AnalyticsViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.admin.domain.usecase.GetAnalyticsUseCase getAnalytics) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<com.routex.app.admin.domain.model.AnalyticsSnapshot>> getState() {
        return null;
    }
    
    public final void load() {
    }
}