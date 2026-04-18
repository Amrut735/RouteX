package com.routex.app.student.presentation.boarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.routex.app.core.utils.Resource
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.student.domain.model.BusStop
import com.routex.app.student.domain.simulator.EtaCalculator
import com.routex.app.student.domain.simulator.LocalRouteSimulator
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BoardingBusModel(
    val update: BusLocationUpdate,
    val etaResult: EtaCalculator.EtaResult
)

@HiltViewModel
class AvailableBusesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRouteById: GetRouteByIdUseCase,
) : ViewModel() {

    val routeId: String = savedStateHandle["routeId"] ?: ""
    val stopId: String = savedStateHandle["stopId"] ?: ""

    private val simulator = LocalRouteSimulator()

    private val _targetStop = MutableStateFlow<BusStop?>(null)
    val targetStop = _targetStop.asStateFlow()

    private val _buses = MutableStateFlow<List<BoardingBusModel>>(emptyList())
    val buses = _buses.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            when (val result = getRouteById(routeId)) {
                is Resource.Success -> {
                    val route = result.data
                    val stop = route.stops.find { it.id == stopId }
                    if (stop != null) {
                        _targetStop.value = stop
                        startSimulation(route.id, route.routeNumber, route.stops, stop)
                    }
                }
                else -> Unit
            }
        }
    }

    private fun startSimulation(rId: String, rNum: String, allStops: List<BusStop>, target: BusStop) {
        val stopLatLng = LatLng(target.latitude, target.longitude)
        val targetSequence = target.sequence

        simulator.simulateBuses(rId, rNum, allStops).onEach { updates ->
            val modeledBuses = updates.map { bus ->
                // Basic check for direction and progression
                // For a robust simulation, EtaCalculator handles the core passing logic.
                // We supplement it: if a bus is moving beyond the stop index relative offsets, we mark it passed.
                // Here we simply use EtaCalculator directly.
                var eta = EtaCalculator.calculateEta(bus, stopLatLng)

                // Refined heuristic for Bidirectional passed check:
                // If it's very close and moving away, we mark PASSED.
                // For simplicity in mock: assume arriving if ETA > 0.
                if (eta.distanceMeters < 50) {
                    eta = eta.copy(status = EtaCalculator.BusStatus.NEAR)
                }

                BoardingBusModel(bus, eta)
            }.sortedBy { it.etaResult.distanceMeters } // Closest first

            _buses.value = modeledBuses
        }.launchIn(viewModelScope)
    }
}
