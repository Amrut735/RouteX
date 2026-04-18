package com.routex.app.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase
import com.routex.app.core.data.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SplashDestination {
    data object Onboarding      : SplashDestination()
    data object Login           : SplashDestination()
    data object StudentDashboard: SplashDestination()
    data object AdminDashboard  : SplashDestination()
    data object DriverDashboard : SplashDestination()
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUserUseCase,
    private val preferences: UserPreferencesRepository,
) : ViewModel() {

    private val _destination = MutableStateFlow<SplashDestination?>(null)
    val destination = _destination.asStateFlow()

    init {
        resolve()
    }

    private fun resolve() {
        viewModelScope.launch {
            // Minimum splash duration for a polished feel
            delay(1_500)

            val hasSeenOnboarding = preferences.hasSeenOnboarding.first()
            if (!hasSeenOnboarding) {
                _destination.value = SplashDestination.Onboarding
                return@launch
            }

            if (!getCurrentUser.isLoggedIn()) {
                _destination.value = SplashDestination.Login
                return@launch
            }

            val user = getCurrentUser()
            _destination.value = when (user?.role) {
                UserRole.ADMIN  -> SplashDestination.AdminDashboard
                UserRole.DRIVER -> SplashDestination.DriverDashboard
                else            -> SplashDestination.StudentDashboard
            }
        }
    }
}
