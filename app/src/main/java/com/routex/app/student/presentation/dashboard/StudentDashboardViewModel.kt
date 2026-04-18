package com.routex.app.student.presentation.dashboard

import androidx.lifecycle.viewModelScope
import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase
import com.routex.app.auth.domain.usecase.SignOutUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.network.NetworkMonitor
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRoutesUseCase
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentDashboardViewModel @Inject constructor(
    private val getRoutes: GetRoutesUseCase,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val signOut: SignOutUseCase,
    private val observeAllTrips: ObserveAllTripsUseCase,
    networkMonitor: NetworkMonitor,
) : BaseViewModel() {

    /** True when the device has no internet. Drives the OfflineBanner. */
    val isOffline = networkMonitor.isConnected
        .map { !it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _routesState = MutableStateFlow<UiState<List<Route>>>(UiState.Loading)
    val routesState = _routesState.asStateFlow()

    /** Active (RUNNING / DELAYED) trips visible to the student. */
    private val _liveTrips = MutableStateFlow<List<Trip>>(emptyList())
    val liveTrips = _liveTrips.asStateFlow()

    init {
        loadUser()
        observeRoutes()
        observeLiveTrips()
    }

    private fun loadUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUser()
        }
    }

    private fun observeRoutes() {
        getRoutes().onEach { resource ->
            _routesState.value = when (resource) {
                is Resource.Loading -> UiState.Loading
                is Resource.Success -> UiState.Success(resource.data.take(3))
                is Resource.Error   -> UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeLiveTrips() {
        observeAllTrips().onEach { resource ->
            if (resource is Resource.Success) {
                _liveTrips.value = resource.data.filter { it.isActive }
            }
        }.launchIn(viewModelScope)
    }

    fun signOut(onDone: () -> Unit) {
        viewModelScope.launch {
            signOut.invoke()
            onDone()
        }
    }
}
