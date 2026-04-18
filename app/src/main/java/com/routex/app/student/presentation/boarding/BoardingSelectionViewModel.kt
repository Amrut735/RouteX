package com.routex.app.student.presentation.boarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.location.LocationProvider
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardingSelectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRouteById: GetRouteByIdUseCase,
    private val locationProvider: LocationProvider
) : ViewModel() {

    val routeId: String = savedStateHandle["routeId"] ?: ""

    private val _routeState = MutableStateFlow<UiState<Route>>(UiState.Loading)
    val routeState = _routeState.asStateFlow()

    private val _userLocation = MutableStateFlow<android.location.Location?>(null)
    val userLocation = _userLocation.asStateFlow()

    init {
        loadRoute()
        startLocationStream()
    }

    private fun loadRoute() {
        viewModelScope.launch {
            _routeState.value = UiState.Loading
            when (val result = getRouteById(routeId)) {
                is Resource.Success -> _routeState.value = UiState.Success(result.data)
                is Resource.Error -> _routeState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    private fun startLocationStream() {
        locationProvider.locationUpdates(intervalMs = 5000L)
            .onEach { _userLocation.value = it }
            .launchIn(viewModelScope)
    }
}
