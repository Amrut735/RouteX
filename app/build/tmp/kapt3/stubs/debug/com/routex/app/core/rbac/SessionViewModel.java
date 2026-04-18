package com.routex.app.core.rbac;

import androidx.lifecycle.ViewModel;
import com.routex.app.auth.domain.model.User;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.repository.AuthRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/routex/app/core/rbac/SessionViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/routex/app/auth/domain/repository/AuthRepository;", "(Lcom/routex/app/auth/domain/repository/AuthRepository;)V", "_session", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/core/rbac/SessionState;", "currentUser", "Lcom/routex/app/auth/domain/model/User;", "getCurrentUser", "()Lcom/routex/app/auth/domain/model/User;", "session", "Lkotlinx/coroutines/flow/StateFlow;", "getSession", "()Lkotlinx/coroutines/flow/StateFlow;", "observeSession", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SessionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.rbac.SessionState> _session = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.rbac.SessionState> session = null;
    
    @javax.inject.Inject()
    public SessionViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.rbac.SessionState> getSession() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.routex.app.auth.domain.model.User getCurrentUser() {
        return null;
    }
    
    private final void observeSession() {
    }
}