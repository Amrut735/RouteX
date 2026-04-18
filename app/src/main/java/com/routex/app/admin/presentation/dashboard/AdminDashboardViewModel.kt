package com.routex.app.admin.presentation.dashboard

import androidx.lifecycle.viewModelScope
import android.location.Location
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.repository.AdminRepository
import com.routex.app.admin.domain.repository.AdminStats
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase
import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase
import com.routex.app.auth.domain.usecase.SignOutUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.firebase.FirebaseInitializer
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDashboardViewModel @Inject constructor(
    private val getAllRoutes: GetAllRoutesUseCase,
    private val adminRepository: AdminRepository,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val signOut: SignOutUseCase,
    private val firebaseInitializer: FirebaseInitializer,
    private val getBusLocation: GetBusLocationUseCase,
) : BaseViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _statsState = MutableStateFlow<UiState<AdminStats>>(UiState.Loading)
    val statsState = _statsState.asStateFlow()

    private val _recentRoutesState = MutableStateFlow<UiState<List<BusRoute>>>(UiState.Loading)
    val recentRoutesState = _recentRoutesState.asStateFlow()

    /** Buses currently within [CollegeHub.NEAR_CAMPUS_RADIUS_M] of the college. */
    private val _approachingCampusBuses = MutableStateFlow<List<BusLocationUpdate>>(emptyList())
    val approachingCampusBuses = _approachingCampusBuses.asStateFlow()

    init {
        loadUser()
        loadStats()
        observeRoutes()
        observeApproachingBuses()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUser()
        }
    }

    private fun loadStats() {
        viewModelScope.launch {
            _statsState.value = UiState.Loading
            when (val result = adminRepository.getDashboardStats()) {
                is Resource.Success -> _statsState.value = UiState.Success(result.data)
                is Resource.Error   -> _statsState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    private fun observeRoutes() {
        getAllRoutes().onEach { resource ->
            _recentRoutesState.value = when (resource) {
                is Resource.Loading -> UiState.Loading
                is Resource.Success -> UiState.Success(resource.data.take(5))
                is Resource.Error   -> UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeApproachingBuses() {
        val dist = FloatArray(1)
        getBusLocation.allActive()
            .onEach { buses ->
                _approachingCampusBuses.value = buses.filter { bus ->
                    if (bus.location.latitude == 0.0) return@filter false
                    Location.distanceBetween(
                        bus.location.latitude, bus.location.longitude,
                        CollegeHub.LATITUDE, CollegeHub.LONGITUDE,
                        dist,
                    )
                    dist[0] <= CollegeHub.NEAR_CAMPUS_RADIUS_M
                }
            }
            .launchIn(viewModelScope)
    }

    fun signOut(onDone: () -> Unit) {
        viewModelScope.launch {
            signOut.invoke()
            onDone()
        }
    }

    fun refresh() {
        loadStats()
    }

    /** Seeds demo route + RTDB node — call once to bootstrap a fresh project. */
    fun seedDemoData(onDone: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = runCatching { firebaseInitializer.seedDemoRoute() }.isSuccess
            onDone(success)
        }
    }
}
