package com.routex.app.auth.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val preferences: UserPreferencesRepository,
) : ViewModel() {

    fun completeOnboarding() {
        viewModelScope.launch {
            preferences.setHasSeenOnboarding(true)
        }
    }
}
