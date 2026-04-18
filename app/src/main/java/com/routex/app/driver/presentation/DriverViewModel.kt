package com.routex.app.driver.presentation

import android.content.Context
import android.os.Build
import androidx.lifecycle.viewModelScope
import com.routex.app.admin.domain.model.Driver
import com.routex.app.admin.domain.usecase.GetBusByIdUseCase
import com.routex.app.admin.domain.usecase.GetDriverByAuthUidUseCase
import com.routex.app.auth.domain.model.User
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase
import com.routex.app.auth.domain.usecase.SignOutUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.network.NetworkMonitor
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.driver.data.service.LocationTrackingService
import com.routex.app.driver.domain.repository.DriverRepository
import com.routex.app.core.location.CollegeHub
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRoutesUseCase
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.usecase.EndTripUseCase
import com.routex.app.trips.domain.usecase.ObserveTripUseCase
import com.routex.app.trips.domain.usecase.StartTripUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
class DriverViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val getRoutes: GetRoutesUseCase,
    private val signOut: SignOutUseCase,
    private val startTripUseCase: StartTripUseCase,
    private val endTripUseCase: EndTripUseCase,
    private val observeTripUseCase: ObserveTripUseCase,
    private val getDriverByAuthUid: GetDriverByAuthUidUseCase,
    private val getBusById: GetBusByIdUseCase,
    private val driverRepository: DriverRepository,
    networkMonitor: NetworkMonitor,
) : BaseViewModel() {

    /** True when the device has no internet connectivity. */
    val isOffline = networkMonitor.isConnected
        .map { !it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    /** The driver's Firestore record — contains assignedBusId, assignedRouteId, etc. */
    private val _driverRecord = MutableStateFlow<Driver?>(null)
    val driverRecord = _driverRecord.asStateFlow()

    /**
     * Resolved Route object from the driver's assignedRouteId.
     * Null if not yet assigned or route not found.
     */
    private val _assignedRoute = MutableStateFlow<Route?>(null)
    val assignedRoute = _assignedRoute.asStateFlow()

    private val _busNumber = MutableStateFlow("")
    val busNumber = _busNumber.asStateFlow()

    private val _isTracking = MutableStateFlow(false)
    val isTracking = _isTracking.asStateFlow()

    /** The currently active trip, updated in real time from Firestore. */
    private val _activeTrip = MutableStateFlow<Trip?>(null)
    val activeTrip = _activeTrip.asStateFlow()

    /** One-shot error message for the UI snackbar. */
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val user = getCurrentUser()
            _currentUser.value = user
            if (user != null) {
                loadDriverRecord(user.uid)
            }
        }
    }

    private fun loadDriverRecord(uid: String) {
        viewModelScope.launch {
            when (val result = getDriverByAuthUid(uid)) {
                is Resource.Success -> {
                    val driver = result.data
                    _driverRecord.value = driver
                    // If driver has an assigned route, resolve it
                    if (driver.assignedRouteId.isNotBlank()) {
                        resolveAssignedRoute(driver.assignedRouteId)
                    }
                    if (driver.assignedBusId.isNotBlank()) {
                        resolveBusNumber(driver.assignedBusId)
                    }
                }
                is Resource.Error -> {
                    // Driver may not have linked their record yet — not a fatal error
                    _errorMessage.value = "No assignment found. Contact admin to assign you a bus and route."
                }
                else -> Unit
            }
        }
    }

    private fun resolveAssignedRoute(routeId: String) {
        getRoutes().onEach { resource ->
            if (resource is Resource.Success) {
                val route = resource.data.find { it.id == routeId }
                _assignedRoute.value = route
                android.util.Log.d("ROUTE_SYNC", "Driver assigned route: ${route?.id} stops count: ${route?.stops?.size}")
            }
        }.launchIn(viewModelScope)
    }

    private fun resolveBusNumber(busId: String) {
        viewModelScope.launch {
            when (val res = getBusById(busId)) {
                is Resource.Success -> {
                    _busNumber.value = res.data.number
                    android.util.Log.d("BUS_DEBUG", "Resolved bus ID $busId to number ${res.data.number}")
                }
                is Resource.Error -> {
                    _busNumber.value = "Bus $busId"
                    android.util.Log.e("BUS_DEBUG", "Failed to resolve bus $busId: ${res.message}")
                }
                else -> Unit
            }
        }
    }

    fun clearError() { _errorMessage.value = null }

    // ── Trip + Tracking lifecycle ─────────────────────────────────────────────

    /**
     * Starts a new trip using the admin-assigned bus and route.
     * No manual input needed — everything comes from the driver's Firestore record.
     */
    fun startTrip() {
        val driver = _driverRecord.value
        val route  = _assignedRoute.value
        val user   = _currentUser.value ?: return

        if (driver == null) {
            _errorMessage.value = "No driver record found. Contact admin."
            return
        }
        if (route == null) {
            _errorMessage.value = "No route assigned. Contact admin to assign a route."
            return
        }
        if (driver.assignedBusId.isBlank()) {
            _errorMessage.value = "No bus assigned. Contact admin to assign a bus."
            return
        }

        viewModelScope.launch {
            val trip = Trip(
                busNumber  = _busNumber.value.ifBlank { driver.assignedBusId },   // Use resolved number
                driverId   = user.uid,
                driverName = driver.name.ifBlank { user.displayName },
                routeId    = route.id,
                routeName  = route.routeName,
            )
            when (val result = startTripUseCase(trip)) {
                is Resource.Success -> {
                    val started = result.data
                    _isTracking.value = true

                    // Subscribe to real-time updates for this trip
                    observeTrip(started.id)

                    // Provide immediate location fix at the College Hub before GPS locks on.
                    // This satisfies the "Bus position = COLLEGE_LOCATION on trip start" requirement.
                    // (Note: The app never auto-starts trips. This logic only runs when triggered by the driver.)
                    viewModelScope.launch {
                        runCatching {
                            driverRepository.uploadLocation(
                                routeId   = route.id,
                                latitude  = CollegeHub.LATITUDE,
                                longitude = CollegeHub.LONGITUDE,
                                speed     = 0f,
                                heading   = 0f,
                                accuracy  = 10f,
                                busNumber = _busNumber.value.ifBlank { driver.assignedBusId },
                            )
                        }
                    }

                    // Start the foreground service
                    val intent = LocationTrackingService.startIntent(
                        context   = context,
                        routeId   = route.id,
                        busNumber = _busNumber.value.ifBlank { driver.assignedBusId },
                        tripId    = started.id,
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(intent)
                    } else {
                        context.startService(intent)
                    }
                }
                is Resource.Error -> {
                    _errorMessage.value = result.message
                }
                else -> Unit
            }
        }
    }

    /**
     * Ends the active trip in Firestore, then stops the tracking service.
     */
    fun endTrip() {
        val tripId = _activeTrip.value?.id ?: return

        viewModelScope.launch {
            when (val result = endTripUseCase(tripId)) {
                is Resource.Success -> {
                    stopTrackingService()
                    _isTracking.value = false
                    _activeTrip.value = null
                }
                is Resource.Error -> {
                    _errorMessage.value = result.message
                }
                else -> Unit
            }
        }
    }

    private fun observeTrip(tripId: String) {
        observeTripUseCase(tripId)
            .onEach { _activeTrip.value = it }
            .launchIn(viewModelScope)
    }

    private fun stopTrackingService() {
        context.startService(LocationTrackingService.stopIntent(context))
    }

    /**
     * Safely signs the driver out, ending any active trip first.
     */
    fun signOut(onDone: () -> Unit) {
        viewModelScope.launch {
            if (_isTracking.value) {
                val tripId = _activeTrip.value?.id
                if (tripId != null) {
                    when (val result = endTripUseCase(tripId)) {
                        is Resource.Error -> {
                            _errorMessage.value = "Trip could not be ended cleanly: ${result.message}"
                        }
                        else -> Unit
                    }
                }
                stopTrackingService()
                _isTracking.value = false
                _activeTrip.value = null
            }
            signOut.invoke()
            onDone()
        }
    }
}
