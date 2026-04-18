package com.routex.app.maps.presentation

import android.location.Location
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.location.LocationProvider
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.maps.data.geofencing.GeofenceManager
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.model.GeofenceTransition
import com.routex.app.maps.domain.model.RouteProgress
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBusLocation: GetBusLocationUseCase,
    private val getRouteById: GetRouteByIdUseCase,
    private val locationProvider: LocationProvider,
    private val geofenceManager: GeofenceManager,
) : ViewModel() {

    val routeId: String = savedStateHandle["routeId"] ?: "all"
    val isAllRoutes: Boolean get() = routeId == "all"

    // ── Route metadata ───────────────────────────────────────────────────────

    private val _routeState = MutableStateFlow<UiState<Route?>>(UiState.Loading)
    val routeState = _routeState.asStateFlow()

    // ── Raw bus location from RTDB ───────────────────────────────────────────

    private val _busLocation = MutableStateFlow<BusLocationUpdate?>(null)
    val busLocation = _busLocation.asStateFlow()

    private val _allBusLocations = MutableStateFlow<List<BusLocationUpdate>>(emptyList())
    val allBusLocations = _allBusLocations.asStateFlow()

    // ── User (student) device location ──────────────────────────────────────

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation = _userLocation.asStateFlow()

    // ── Polyline progress (completed + remaining) ────────────────────────────

    private val _routeProgress = MutableStateFlow(RouteProgress())
    val routeProgress = _routeProgress.asStateFlow()

    // ── Geofence events (bus near a stop or campus) ──────────────────────────

    val geofenceEvents = GeofenceManager.events
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    /**
     * True when the bus has entered the KLS GIT campus radius.
     * Drives the "Arriving at College" UI banner in [MapScreen].
     */
    private val _isArrivingAtCampus = MutableStateFlow(false)
    val isArrivingAtCampus = _isArrivingAtCampus.asStateFlow()

    /**
     * Buses that are within [CollegeHub.APPROACHING_RADIUS_M] of the campus.
     * Shown in the "all routes" / admin map view.
     */
    private val _approachingCampusBuses = MutableStateFlow<List<BusLocationUpdate>>(emptyList())
    val approachingCampusBuses = _approachingCampusBuses.asStateFlow()

    // ── Camera follow toggle ─────────────────────────────────────────────────

    private val _followBus = MutableStateFlow(true)
    val followBus = _followBus.asStateFlow()

    // ── Init ─────────────────────────────────────────────────────────────────

    init {
        if (isAllRoutes) {
            observeAllBuses()
            _routeState.value = UiState.Success(null)
        } else {
            loadRoute()
            observeSingleBus()
        }
        observeUserLocation()
        observeCampusGeofenceEvents()
    }

    // ── Route loading ────────────────────────────────────────────────────────

    /** Public so MapScreen can offer a "Retry" button on error. */
    fun retryLoad() = loadRoute()

    private fun loadRoute() {
        viewModelScope.launch {
            when (val result = getRouteById(routeId)) {
                is Resource.Success -> {
                    _routeState.value = UiState.Success(result.data)
                    val stops = result.data.stops
                    if (stops.isNotEmpty()) {
                        runCatching { geofenceManager.registerStopGeofences(stops) }
                    }
                    // Always register the campus geofence so the final
                    // arrival at KLS GIT is detected regardless of route
                    runCatching { geofenceManager.registerCampusGeofence() }
                }
                is Resource.Error -> _routeState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    // ── Bus location streaming ───────────────────────────────────────────────

    private fun observeSingleBus() {
        getBusLocation(routeId)
            .filterNotNull()
            .onEach { update ->
                _busLocation.value = update
                recomputePolyline(update)

                val busLat = update.location.latitude
                val busLng = update.location.longitude

                // Software stop proximity check
                val route = (routeState.value as? UiState.Success)?.data
                route?.stops?.let { stops ->
                    geofenceManager.checkProximity(busLat, busLng, stops)
                }

                // Software campus proximity check — drives "Arriving at College" banner
                val enteredCampus = geofenceManager.checkCampusProximity(busLat, busLng)
                if (enteredCampus) _isArrivingAtCampus.value = true
            }
            .catch { /* swallow stream errors */ }
            .launchIn(viewModelScope)
    }

    private fun observeAllBuses() {
        getBusLocation.allActive()
            .onEach { buses ->
                _allBusLocations.value = buses
                // Derive approaching-campus subset for admin overlay
                val dist = FloatArray(1)
                _approachingCampusBuses.value = buses.filter { bus ->
                    if (bus.location.latitude == 0.0) return@filter false
                    android.location.Location.distanceBetween(
                        bus.location.latitude, bus.location.longitude,
                        CollegeHub.LATITUDE, CollegeHub.LONGITUDE,
                        dist,
                    )
                    dist[0] <= CollegeHub.APPROACHING_RADIUS_M
                }
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    // ── Campus geofence event observer ────────────────────────────────────────

    private fun observeCampusGeofenceEvents() {
        GeofenceManager.events
            .onEach { event ->
                if (event.isCampusEntry && event.transition == GeofenceTransition.ENTER) {
                    _isArrivingAtCampus.value = true
                }
            }
            .launchIn(viewModelScope)
    }

    // ── User location ─────────────────────────────────────────────────────────

    private fun observeUserLocation() {
        locationProvider.locationUpdates(LocationProvider.STUDENT_INTERVAL_MS)
            .map { loc -> LatLng(loc.latitude, loc.longitude) }
            .onEach { _userLocation.value = it }
            .catch { }
            .launchIn(viewModelScope)
    }

    // ── Polyline computation ──────────────────────────────────────────────────

    private fun recomputePolyline(update: BusLocationUpdate) {
        val route = (routeState.value as? UiState.Success)?.data ?: return
        val stops = route.stops.sortedBy { it.sequence }
        if (stops.isEmpty()) return

        val busLat = update.location.latitude
        val busLng = update.location.longitude
        val busLatLng = LatLng(busLat, busLng)
        val stopLatLngs = stops.map { LatLng(it.latitude, it.longitude) }

        // Find the stop index closest to the bus
        val dist = FloatArray(1)
        var minDist = Float.MAX_VALUE
        var closestIdx = 0
        stopLatLngs.forEachIndexed { idx, ll ->
            Location.distanceBetween(busLat, busLng, ll.latitude, ll.longitude, dist)
            if (dist[0] < minDist) {
                minDist = dist[0]
                closestIdx = idx
            }
        }

        // Determine next stop (ahead of bus)
        val nextIdx = if (closestIdx < stopLatLngs.lastIndex) closestIdx + 1 else closestIdx
        Location.distanceBetween(
            busLat, busLng,
            stops[nextIdx].latitude, stops[nextIdx].longitude,
            dist,
        )

        val completed = stopLatLngs.subList(0, closestIdx + 1) + busLatLng
        val remaining = listOf(busLatLng) + stopLatLngs.subList(closestIdx + 1, stopLatLngs.size)

        _routeProgress.value = RouteProgress(
            completedPoints       = completed,
            remainingPoints       = remaining,
            nextStopIndex         = nextIdx,
            distanceToNextStopM   = dist[0],
        )
    }

    // ── Camera control ────────────────────────────────────────────────────────

    fun toggleFollowBus() { _followBus.value = !_followBus.value }
    fun disableFollowBus() { _followBus.value = false }

    // ── Cleanup ───────────────────────────────────────────────────────────────

    override fun onCleared() {
        super.onCleared()
        if (!isAllRoutes) {
            viewModelScope.launch { geofenceManager.removeAll() }
        }
    }
}
