package com.routex.app.auth.presentation.splash;

import androidx.lifecycle.ViewModel;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.core.data.UserPreferencesRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/routex/app/auth/presentation/splash/SplashViewModel;", "Landroidx/lifecycle/ViewModel;", "getCurrentUser", "Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;", "preferences", "Lcom/routex/app/core/data/UserPreferencesRepository;", "(Lcom/routex/app/auth/domain/usecase/GetCurrentUserUseCase;Lcom/routex/app/core/data/UserPreferencesRepository;)V", "_destination", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/auth/presentation/splash/SplashDestination;", "destination", "Lkotlinx/coroutines/flow/StateFlow;", "getDestination", "()Lkotlinx/coroutines/flow/StateFlow;", "resolve", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SplashViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.data.UserPreferencesRepository preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.auth.presentation.splash.SplashDestination> _destination = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.splash.SplashDestination> destination = null;
    
    @javax.inject.Inject()
    public SplashViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.usecase.GetCurrentUserUseCase getCurrentUser, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.data.UserPreferencesRepository preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.auth.presentation.splash.SplashDestination> getDestination() {
        return null;
    }
    
    private final void resolve() {
    }
}