package com.routex.app.auth.presentation.onboarding;

import androidx.lifecycle.ViewModel;
import com.routex.app.core.data.UserPreferencesRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/routex/app/auth/presentation/onboarding/OnboardingViewModel;", "Landroidx/lifecycle/ViewModel;", "preferences", "Lcom/routex/app/core/data/UserPreferencesRepository;", "(Lcom/routex/app/core/data/UserPreferencesRepository;)V", "completeOnboarding", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class OnboardingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.core.data.UserPreferencesRepository preferences = null;
    
    @javax.inject.Inject()
    public OnboardingViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.data.UserPreferencesRepository preferences) {
        super();
    }
    
    public final void completeOnboarding() {
    }
}