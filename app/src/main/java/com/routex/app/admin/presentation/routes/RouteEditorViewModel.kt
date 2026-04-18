package com.routex.app.admin.presentation.routes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.usecase.AddRouteUseCase
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase
import com.routex.app.admin.domain.usecase.UpdateRouteUseCase
import com.routex.app.student.domain.model.BusStop
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapStop(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String = "",
    val latLng: LatLng = LatLng(0.0, 0.0),
    val sequence: Int = 0,
    val arrivalTime: String = "",
)

@HiltViewModel
class RouteEditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addRoute: AddRouteUseCase,
    private val updateRoute: UpdateRouteUseCase,
    private val getAllRoutes: GetAllRoutesUseCase,
) : ViewModel() {

    private val editRouteId: String? = savedStateHandle["routeId"]

    // ── Map stops (the heart of the editor) ──────────────────────────────────
    private val _stops = MutableStateFlow<List<MapStop>>(emptyList())
    val stops: StateFlow<List<MapStop>> = _stops.asStateFlow()

    // ── Route metadata form ───────────────────────────────────────────────────
    private val _routeNumber  = MutableStateFlow("")
    val routeNumber: StateFlow<String> = _routeNumber.asStateFlow()

    private val _routeName    = MutableStateFlow("")
    val routeName: StateFlow<String> = _routeName.asStateFlow()

    private val _busNumber    = MutableStateFlow("")
    val busNumber: StateFlow<String> = _busNumber.asStateFlow()

    private val _driverName   = MutableStateFlow("")
    val driverName: StateFlow<String> = _driverName.asStateFlow()

    private val _scheduleTime = MutableStateFlow("")
    val scheduleTime: StateFlow<String> = _scheduleTime.asStateFlow()

    // ── Selected stop for rename dialog ──────────────────────────────────────
    private val _editingStop = MutableStateFlow<MapStop?>(null)
    val editingStop: StateFlow<MapStop?> = _editingStop.asStateFlow()

    // ── Action state ──────────────────────────────────────────────────────────
    private val _saveState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val saveState: StateFlow<UiState<Unit>> = _saveState.asStateFlow()

    // ── Show form panel ───────────────────────────────────────────────────────
    private val _showFormPanel = MutableStateFlow(false)
    val showFormPanel: StateFlow<Boolean> = _showFormPanel.asStateFlow()

    init {
        if (editRouteId != null) loadExistingRoute(editRouteId)
    }

    private fun loadExistingRoute(routeId: String) {
        viewModelScope.launch {
            val resource = getAllRoutes().first()
            if (resource is Resource.Success) {
                val route = resource.data.find { it.id == routeId } ?: return@launch
                _routeNumber.value  = route.routeNumber
                _routeName.value    = route.routeName
                _busNumber.value    = route.busNumber
                _driverName.value   = route.driverName
                _scheduleTime.value = route.scheduleTime
                _stops.value = route.stops.map { s ->
                    MapStop(
                        id = s.id,
                        name = s.name,
                        latLng = LatLng(s.latitude, s.longitude),
                        sequence = s.sequence,
                        arrivalTime = s.arrivalTime
                    )
                }
            }
        }
    }

    // ── Map tap handler ───────────────────────────────────────────────────────

    fun onMapTapped(latLng: LatLng) {
        val seq  = _stops.value.size + 1
        val stop = MapStop(
            name     = "Stop $seq",
            latLng   = latLng,
            sequence = seq,
        )
        _stops.value = _stops.value + stop
        // Auto-open rename dialog for the new stop
        _editingStop.value = stop
    }

    // ── Stop management ───────────────────────────────────────────────────────

    fun removeStop(stopId: String) {
        _stops.value = _stops.value
            .filter { it.id != stopId }
            .mapIndexed { idx, s -> s.copy(sequence = idx + 1) }
    }

    fun startEditStop(stop: MapStop) { _editingStop.value = stop }

    fun confirmRenameStop(id: String, newName: String, arrivalTime: String) {
        _stops.value = _stops.value.map {
            if (it.id == id) it.copy(name = newName, arrivalTime = arrivalTime) else it
        }
        _editingStop.value = null
    }

    fun cancelEditStop() { _editingStop.value = null }

    // ── Form fields ───────────────────────────────────────────────────────────
    fun onRouteNumberChange(v: String)  { _routeNumber.value = v }
    fun onRouteNameChange(v: String)    { _routeName.value = v }
    fun onBusNumberChange(v: String)    { _busNumber.value = v }
    fun onDriverNameChange(v: String)   { _driverName.value = v }
    fun onScheduleTimeChange(v: String) { _scheduleTime.value = v }

    fun toggleFormPanel() { _showFormPanel.value = !_showFormPanel.value }

    // ── Undo last stop ────────────────────────────────────────────────────────
    fun undoLastStop() {
        val current = _stops.value
        if (current.isNotEmpty()) _stops.value = current.dropLast(1)
    }

    fun clearAllStops() { _stops.value = emptyList() }

    // ── Save route ────────────────────────────────────────────────────────────

    fun saveRoute(onSuccess: () -> Unit) {
        val stops = _stops.value
        if (_routeNumber.value.isBlank() || _routeName.value.isBlank()) {
            _saveState.value = UiState.Error("Route number and name are required")
            return
        }
        if (stops.isEmpty()) {
            _saveState.value = UiState.Error("Add at least one stop on the map")
            return
        }

        viewModelScope.launch {
            _saveState.value = UiState.Loading
            val route = BusRoute(
                id                = editRouteId ?: "",
                routeNumber       = _routeNumber.value.trim(),
                routeName         = _routeName.value.trim(),
                startPoint        = stops.first().name,
                endPoint          = stops.last().name,
                busNumber         = _busNumber.value.trim(),
                driverName        = _driverName.value.trim(),
                scheduleTime      = _scheduleTime.value.trim(),
                stopNames         = stops.map { it.name },
                stops             = stops.map { s ->
                    BusStop(
                        id = s.id,
                        name = s.name,
                        latitude = s.latLng.latitude,
                        longitude = s.latLng.longitude,
                        sequence = s.sequence,
                        arrivalTime = s.arrivalTime
                    )
                },
                isActive          = true,
                totalDistance     = computeApproxDistanceKm(stops),
                estimatedDuration = (stops.size * 5),  // rough: 5 min per stop
            )

            val result = if (editRouteId != null) updateRoute(route) else addRoute(route)
            _saveState.value = when (result) {
                is Resource.Success -> UiState.Success(Unit)
                is Resource.Error   -> UiState.Error(result.message)
                else -> UiState.Idle
            }
            if (result is Resource.Success) onSuccess()
        }
    }

    fun resetSaveState() { _saveState.value = UiState.Idle }

    // ── Haversine approximate total distance ──────────────────────────────────

    private fun computeApproxDistanceKm(stops: List<MapStop>): Double {
        if (stops.size < 2) return 0.0
        var total = 0.0
        for (i in 0 until stops.size - 1) {
            val a = stops[i].latLng
            val b = stops[i + 1].latLng
            total += haversine(a.latitude, a.longitude, b.latitude, b.longitude)
        }
        return total
    }

    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2)
        return 2 * R * Math.asin(Math.sqrt(a))
    }
}
