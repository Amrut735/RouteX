package com.routex.app.driver.presentation

import android.location.Location
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.location.LocationProvider
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import kotlinx.coroutines.flow.catch
import com.routex.app.student.domain.model.BusStop
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.usecase.ObserveActiveTripForRouteUseCase
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
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * ViewModel for the Driver Map screen.
 *
 * Responsibilities:
 * - Load the assigned route + stops from Firestore.
 * - Stream the driver's live GPS position via [LocationProvider].
 * - Observe the active trip from Firestore for real-time status.
 * - Compute the *next stop* by finding the stop nearest to the driver
 *   that has not been passed yet (closest in forward direction).
 */
@HiltViewModel
class DriverMapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRouteById: GetRouteByIdUseCase,
    private val locationProvider: LocationProvider,
    private val observeActiveTripForRoute: ObserveActiveTripForRouteUseCase,
) : BaseViewModel() {

    private val routeId: String = checkNotNull(savedStateHandle["routeId"])

    // ── Route ─────────────────────────────────────────────────────────────────

    private val _routeState = MutableStateFlow<UiState<Route>>(UiState.Loading)
    val routeState = _routeState.asStateFlow()

    /** Full list of stops derived from the loaded route. */
    val stops = _routeState.map { state ->
        (state as? UiState.Success)?.data?.stops ?: emptyList()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // ── Active trip ───────────────────────────────────────────────────────────

    private val _activeTrip = MutableStateFlow<Trip?>(null)
    val activeTrip = _activeTrip.asStateFlow()

    // ── Driver location ───────────────────────────────────────────────────────

    private val _driverLocation = MutableStateFlow<LatLng?>(null)
    val driverLocation = _driverLocation.asStateFlow()

    // ── Next stop ─────────────────────────────────────────────────────────────

    private val _nextStop = MutableStateFlow<BusStop?>(null)
    val nextStop = _nextStop.asStateFlow()

    private val _nextStopDistanceM = MutableStateFlow<Float>(0f)
    val nextStopDistanceM = _nextStopDistanceM.asStateFlow()

    init {
        loadRoute()
        startLocationStream()
        observeTrip()
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    fun retryLoad() = loadRoute()

    private fun loadRoute() {
        viewModelScope.launch {
            _routeState.value = when (val r = getRouteById(routeId)) {
                is Resource.Success -> {
                    Log.d("ROUTE_SYNC", "DriverMap loaded route ${r.data.id} with ${r.data.stops.size} stops")
                    UiState.Success(r.data)
                }
                is Resource.Error   -> UiState.Error(r.message)
                is Resource.Loading -> UiState.Loading
            }
        }
    }

    private fun startLocationStream() {
        locationProvider.locationUpdates(intervalMs = LocationProvider.DRIVER_INTERVAL_MS)
            .onEach { location ->
                val latLng = LatLng(location.latitude, location.longitude)
                _driverLocation.value = latLng
                updateNextStop(location)
            }
            .catch { e ->
                _routeState.value = UiState.Error("Location access lost. Check permissions. (${e.message})")
            }
            .launchIn(viewModelScope)
    }

    private fun observeTrip() {
        observeActiveTripForRoute(routeId)
            .onEach { _activeTrip.value = it }
            .launchIn(viewModelScope)
    }

    /**
     * Finds the nearest stop that is still "ahead" of the driver.
     * Strategy: sort stops by sequence, then pick the first one within
     * [PASSED_THRESHOLD_M] that the driver hasn't passed. If all stops
     * have been passed, keep the last stop as the destination.
     */
    private fun updateNextStop(location: Location) {
        val currentStops = stops.value
        if (currentStops.isEmpty()) {
            Log.e("ROUTE_SYNC", "Driver updateNextStop: NO STOPS AVAILABLE for route $routeId")
            return
        }

        val sorted = currentStops.sortedBy { it.sequence }
        val next = sorted.firstOrNull { stop ->
            haversineM(
                location.latitude, location.longitude,
                stop.latitude, stop.longitude,
            ) > PASSED_THRESHOLD_M
        } ?: sorted.last()

        if (_nextStop.value?.id != next.id) {
            Log.d("ROUTE_SYNC", "Next stop updated to: ${next.name}")
        }
        
        _nextStop.value = next
        _nextStopDistanceM.value = haversineM(
            location.latitude, location.longitude,
            next.latitude, next.longitude,
        )
    }

    private fun haversineM(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2)
        return (2 * EARTH_RADIUS_M * asin(sqrt(a))).toFloat()
    }

    companion object {
        /** Bus is considered to have passed a stop if within 80 m. */
        private const val PASSED_THRESHOLD_M = 80f
        private const val EARTH_RADIUS_M     = 6_371_000.0
    }
}
