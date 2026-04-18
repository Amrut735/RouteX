package com.routex.app.eta.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.data.UserPreferencesRepository
import com.routex.app.core.notification.FcmTokenRepository
import com.routex.app.core.notification.NotificationHelper
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.eta.domain.model.BusStatus
import com.routex.app.eta.domain.model.EtaResult
import com.routex.app.eta.domain.usecase.ObserveEtaUseCase
import com.routex.app.student.domain.model.BusStop
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase
import com.routex.app.student.domain.usecase.GetRoutesUseCase
import com.routex.app.student.domain.usecase.MissedBusPrediction
import com.routex.app.student.domain.usecase.MissedBusPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EtaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRoute: GetRouteByIdUseCase,
    private val getRoutes: GetRoutesUseCase,
    private val observeEta: ObserveEtaUseCase,
    private val prefs: UserPreferencesRepository,
    private val notificationHelper: NotificationHelper,
    private val fcmTokenRepository: FcmTokenRepository,
    private val missedBusPrediction: MissedBusPredictionUseCase,
) : ViewModel() {

    // Route passed as nav argument; "all" means open picker
    private val navRouteId: String = savedStateHandle["routeId"] ?: "all"

    // ── Route list ────────────────────────────────────────────────────────────
    private val _routesState = MutableStateFlow<UiState<List<Route>>>(UiState.Loading)
    val routesState: StateFlow<UiState<List<Route>>> = _routesState.asStateFlow()

    // ── Selected route / stop ─────────────────────────────────────────────────
    private val _selectedRoute = MutableStateFlow<Route?>(null)
    val selectedRoute: StateFlow<Route?> = _selectedRoute.asStateFlow()

    private val _selectedStop = MutableStateFlow<BusStop?>(null)
    val selectedStop: StateFlow<BusStop?> = _selectedStop.asStateFlow()

    // ── ETA result ────────────────────────────────────────────────────────────
    private val _etaState = MutableStateFlow<UiState<EtaResult>>(UiState.Idle)
    val etaState: StateFlow<UiState<EtaResult>> = _etaState.asStateFlow()

    // ── Missed bus prediction ─────────────────────────────────────────────────
    private val _prediction = MutableStateFlow<MissedBusPrediction?>(null)
    val prediction: StateFlow<MissedBusPrediction?> = _prediction.asStateFlow()

    var walkingTimeMin: Float = 5f

    // ── Saved boarding stop name for UI hints ─────────────────────────────────
    val savedBoardingStopName: StateFlow<String> = prefs.boardingStopName
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    // Avoid spamming notifications for the same threshold
    private val notifiedThresholds = mutableSetOf<String>()

    private var etaJob: Job? = null

    // ─────────────────────────────────────────────────────────────────────────

    init {
        loadRoutes()
    }

    private fun loadRoutes() {
        viewModelScope.launch {
            _routesState.value = UiState.Loading

            if (navRouteId != "all") {
                // Single route from RoutesScreen
                when (val result = getRoute(navRouteId)) {
                    is Resource.Success -> {
                        val route = result.data
                        _routesState.value = UiState.Success(listOf(route))
                        restoreOrSelectFirstStop(route)
                    }
                    is Resource.Error -> _routesState.value = UiState.Error(result.message)
                    else -> Unit
                }
            } else {
                // Load all routes; also try to restore the persisted boarding stop
                getRoutes()
                    .catch { e -> _routesState.value = UiState.Error(e.message ?: "Failed to load") }
                    .collect { resource ->
                        when (resource) {
                            is Resource.Loading -> _routesState.value = UiState.Loading
                            is Resource.Error   -> _routesState.value = UiState.Error(resource.message)
                            is Resource.Success -> {
                                _routesState.value = UiState.Success(resource.data)
                                if (_selectedRoute.value == null) {
                                    tryRestoreFromPrefs(resource.data)
                                        ?: resource.data.firstOrNull()?.let { onRouteSelected(it) }
                                }
                            }
                        }
                    }
            }
        }
    }

    private suspend fun restoreOrSelectFirstStop(route: Route) {
        val savedStopId = prefs.boardingStopId.first()
        val stop = if (savedStopId.isNotBlank()) {
            route.stops.find { it.id == savedStopId } ?: route.stops.firstOrNull()
        } else {
            route.stops.firstOrNull()
        }
        _selectedRoute.value = route
        if (stop != null) onStopSelected(route.id, stop)
    }

    private suspend fun tryRestoreFromPrefs(routes: List<Route>): Unit? {
        val savedRouteId = prefs.boardingRouteId.first()
        val savedStopId  = prefs.boardingStopId.first()
        if (savedRouteId.isBlank() || savedStopId.isBlank()) return null

        val route = routes.find { it.id == savedRouteId } ?: return null
        val stop  = route.stops.find { it.id == savedStopId } ?: route.stops.firstOrNull() ?: return null
        _selectedRoute.value = route
        onStopSelected(route.id, stop)
        return Unit
    }

    // ── Public selection actions ──────────────────────────────────────────────

    fun onRouteSelected(route: Route) {
        if (_selectedRoute.value?.id == route.id) return
        _selectedRoute.value = route
        notifiedThresholds.clear()
        val firstStop = route.stops.firstOrNull()
        if (firstStop != null) {
            onStopSelected(route.id, firstStop)
        } else {
            _selectedStop.value = null
            etaJob?.cancel()
            _etaState.value = UiState.Idle
        }
    }

    fun onStopSelected(routeId: String, stop: BusStop) {
        _selectedStop.value = stop
        notifiedThresholds.clear()
        startObservingEta(routeId, stop)
        viewModelScope.launch {
            prefs.setBoardingStop(routeId, stop.id, stop.name)
            fcmTokenRepository.saveStopSubscription(routeId, stop.id, stop.name)
        }
    }

    // ── ETA observation ───────────────────────────────────────────────────────

    private fun startObservingEta(routeId: String, stop: BusStop) {
        etaJob?.cancel()
        _etaState.value = UiState.Loading

        etaJob = viewModelScope.launch {
            observeEta(routeId, stop)
                .catch { e -> _etaState.value = UiState.Error(e.message ?: "ETA unavailable") }
                .collect { eta ->
                    _etaState.value = UiState.Success(eta)
                    _prediction.value = missedBusPrediction(eta, walkingTimeMin)
                    triggerNotificationsIfNeeded(eta, routeId)
                }
        }
    }

    // ── Local notification triggers ───────────────────────────────────────────

    private fun triggerNotificationsIfNeeded(eta: EtaResult, routeId: String) {
        if (eta.status == BusStatus.OFFLINE) return
        val stop = eta.stopName

        when {
            eta.isAtStop && notifiedThresholds.add("arriving") ->
                notificationHelper.showEtaAlert(routeId,
                    "Bus has arrived!", "Your bus is now at $stop.")

            eta.etaMinutes in 0f..2f && notifiedThresholds.add("2min") ->
                notificationHelper.showEtaAlert(routeId,
                    "Bus is almost here!",
                    "Arriving at $stop in ~${eta.etaMinutes.toInt()} min.")

            eta.etaMinutes in 2f..5f && notifiedThresholds.add("5min") ->
                notificationHelper.showEtaAlert(routeId,
                    "Bus arriving in 5 minutes",
                    "Head to $stop now. ETA: ${eta.etaFormatted}.")

            eta.etaMinutes in 5f..10f && notifiedThresholds.add("10min") ->
                notificationHelper.showEtaAlert(routeId,
                    "Bus arriving in ~10 minutes",
                    "Get ready! $stop — ETA: ${eta.etaFormatted}.")

            eta.status == BusStatus.DELAYED && notifiedThresholds.add("delayed") ->
                notificationHelper.showEtaAlert(routeId,
                    "Bus is delayed",
                    "Running late to $stop. New ETA: ${eta.etaFormatted}.")
        }
    }

    override fun onCleared() {
        super.onCleared()
        etaJob?.cancel()
    }
}
