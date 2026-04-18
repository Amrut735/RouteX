package com.routex.app.core.ui;

import androidx.lifecycle.ViewModel;
import com.routex.app.core.data.UserPreferencesRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/routex/app/core/ui/ThemeViewModel;", "Landroidx/lifecycle/ViewModel;", "preferencesRepository", "Lcom/routex/app/core/data/UserPreferencesRepository;", "(Lcom/routex/app/core/data/UserPreferencesRepository;)V", "isDarkTheme", "Lkotlinx/coroutines/flow/StateFlow;", "", "()Lkotlinx/coroutines/flow/StateFlow;", "toggleTheme", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ThemeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.data.UserPreferencesRepository preferencesRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isDarkTheme = null;
    
    @javax.inject.Inject()
    public ThemeViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.data.UserPreferencesRepository preferencesRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isDarkTheme() {
        return null;
    }
    
    public final void toggleTheme() {
    }
}