package com.routex.app.admin.presentation.buses

import androidx.lifecycle.viewModelScope
import com.routex.app.admin.domain.model.Bus
import com.routex.app.admin.domain.model.Driver
import com.routex.app.admin.domain.usecase.AddBusUseCase
import com.routex.app.admin.domain.usecase.AddDriverUseCase
import com.routex.app.admin.domain.usecase.AssignBusUseCase
import com.routex.app.admin.domain.usecase.AssignDriverUseCase
import com.routex.app.admin.domain.usecase.DeleteBusUseCase
import com.routex.app.admin.domain.usecase.DeleteDriverUseCase
import com.routex.app.admin.domain.usecase.GetAllBusesUseCase
import com.routex.app.admin.domain.usecase.GetAllDriversUseCase
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase
import com.routex.app.admin.domain.usecase.SeedBusesUseCase
import com.routex.app.admin.domain.usecase.SeedDriversUseCase
import com.routex.app.admin.domain.usecase.SeedRoutesUseCase
import com.routex.app.admin.domain.usecase.UpdateBusUseCase
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BusForm(
    val id: String = "",
    val number: String = "",
    val model: String = "",
    val capacity: String = "40",
    val licensePlate: String = "",
    val assignedRouteId: String = "",
    val assignedDriverId: String = "",
)

fun BusForm.toBus() = Bus(
    id               = id,
    number           = number.trim(),
    model            = model.trim(),
    capacity         = capacity.toIntOrNull() ?: 40,
    licensePlate     = licensePlate.trim(),
    assignedRouteId  = assignedRouteId,
    assignedDriverId = assignedDriverId,
)

@HiltViewModel
class BusManagementViewModel @Inject constructor(
    private val getAllBuses: GetAllBusesUseCase,
    private val getAllRoutes: GetAllRoutesUseCase,
    private val addBus: AddBusUseCase,
    private val updateBus: UpdateBusUseCase,
    private val deleteBus: DeleteBusUseCase,
    private val assignBus: AssignBusUseCase,
    private val addDriverUseCase: AddDriverUseCase,
    private val getAllDriversUseCase: GetAllDriversUseCase,
    private val assignDriverUseCase: AssignDriverUseCase,
    private val seedDriversUseCase: SeedDriversUseCase,
    private val seedBusesUseCase: SeedBusesUseCase,
    private val seedRoutesUseCase: SeedRoutesUseCase,
) : BaseViewModel() {

    // ── Buses ─────────────────────────────────────────────────────────────────
    private val _busesState = MutableStateFlow<UiState<List<Bus>>>(UiState.Loading)
    val busesState: StateFlow<UiState<List<Bus>>> = _busesState.asStateFlow()

    // ── Routes (for assignment dropdown) ─────────────────────────────────────
    private val _routesState = MutableStateFlow<UiState<List<BusRoute>>>(UiState.Idle)
    val routesState: StateFlow<UiState<List<BusRoute>>> = _routesState.asStateFlow()

    // ── Action feedback ───────────────────────────────────────────────────────
    private val _actionState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val actionState: StateFlow<UiState<Unit>> = _actionState.asStateFlow()

    // ── Dialogs ───────────────────────────────────────────────────────────────
    private val _showBusDialog = MutableStateFlow(false)
    val showBusDialog: StateFlow<Boolean> = _showBusDialog.asStateFlow()

    private val _editingBus = MutableStateFlow<Bus?>(null)
    val editingBus: StateFlow<Bus?> = _editingBus.asStateFlow()

    private val _busForm = MutableStateFlow(BusForm())
    val busForm: StateFlow<BusForm> = _busForm.asStateFlow()

    // ── Driver dialog ─────────────────────────────────────────────────────────
    data class DriverForm(
        val name: String = "",
        val email: String = "",
        val driverCode: String = "",
        val phoneNumber: String = "",
    )
    private val _showDriverDialog = MutableStateFlow(false)
    val showDriverDialog: StateFlow<Boolean> = _showDriverDialog.asStateFlow()
    private val _driverForm = MutableStateFlow(DriverForm())
    val driverForm: StateFlow<DriverForm> = _driverForm.asStateFlow()

    // ── Drivers list ──────────────────────────────────────────────────────────
    private val _driversState = MutableStateFlow<UiState<List<Driver>>>(UiState.Loading)
    val driversState: StateFlow<UiState<List<Driver>>> = _driversState.asStateFlow()

    init {
        observeBuses()
        observeRoutes()
        observeDrivers()
        viewModelScope.launch {
            seedDriversUseCase()
            seedBusesUseCase()
            seedRoutesUseCase()
        }
    }

    private fun observeBuses() {
        getAllBuses().onEach { resource ->
            _busesState.value = when (resource) {
                is Resource.Loading -> UiState.Loading
                is Resource.Success -> UiState.Success(resource.data)
                is Resource.Error   -> UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeDrivers() {
        getAllDriversUseCase().onEach { resource ->
            _driversState.value = when (resource) {
                is Resource.Loading -> UiState.Loading
                is Resource.Success -> UiState.Success(resource.data)
                is Resource.Error   -> UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeRoutes() {
        getAllRoutes().onEach { resource ->
            _routesState.value = when (resource) {
                is Resource.Loading -> UiState.Loading
                is Resource.Success -> UiState.Success(resource.data)
                is Resource.Error   -> UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    // ── Dialog control ────────────────────────────────────────────────────────

    fun openAddBusDialog() {
        _editingBus.value = null
        _busForm.value = BusForm()
        _showBusDialog.value = true
    }

    fun openEditBusDialog(bus: Bus) {
        _editingBus.value = bus
        _busForm.value = BusForm(
            id               = bus.id,
            number           = bus.number,
            model            = bus.model,
            capacity         = bus.capacity.toString(),
            licensePlate     = bus.licensePlate,
            assignedRouteId  = bus.assignedRouteId,
            assignedDriverId = bus.assignedDriverId,
        )
        _showBusDialog.value = true
    }

    fun dismissBusDialog() {
        _showBusDialog.value = false
        _editingBus.value = null
        _busForm.value = BusForm()
    }

    fun onBusFormChange(form: BusForm) { _busForm.value = form }

    // ── CRUD ──────────────────────────────────────────────────────────────────

    fun submitBus() {
        val form = _busForm.value
        if (form.number.isBlank()) {
            _actionState.value = UiState.Error("Bus number is required")
            return
        }
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            val bus = form.toBus()
            val result = if (_editingBus.value != null) updateBus(bus)
                         else addBus(bus)
            _actionState.value = when (result) {
                is Resource.Success -> { dismissBusDialog(); UiState.Success(Unit) }
                is Resource.Error   -> UiState.Error(result.message)
                else                -> UiState.Idle
            }
        }
    }

    fun deleteBus(busId: String) {
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            _actionState.value = when (val r = deleteBus.invoke(busId)) {
                is Resource.Success -> UiState.Success(Unit)
                is Resource.Error   -> UiState.Error(r.message)
                else -> UiState.Idle
            }
        }
    }

    fun assignDriverToBus(driverId: String, busId: String, routeId: String) {
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            _actionState.value = when (val r = assignDriverUseCase(driverId, busId, routeId)) {
                is Resource.Success -> UiState.Success(Unit)
                is Resource.Error   -> UiState.Error(r.message)
                else -> UiState.Idle
            }
        }
    }

    fun assignBusToRoute(busId: String, routeId: String, driverId: String) {
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            _actionState.value = when (val r = assignBus(busId, routeId, driverId)) {
                is Resource.Success -> UiState.Success(Unit)
                is Resource.Error   -> UiState.Error(r.message)
                else -> UiState.Idle
            }
        }
    }

    fun resetActionState() { _actionState.value = UiState.Idle }

    // ── Driver management ─────────────────────────────────────────────────────

    fun openAddDriverDialog() { _showDriverDialog.value = true }
    fun dismissDriverDialog() {
        _showDriverDialog.value = false
        _driverForm.value = DriverForm()
    }
    fun onDriverFormChange(form: DriverForm) { _driverForm.value = form }

    fun submitDriver() {
        val f = _driverForm.value
        if (f.name.isBlank() || f.email.isBlank() || f.driverCode.isBlank()) {
            _actionState.value = UiState.Error("Name, email, and driver code are all required")
            return
        }
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            val driver = Driver(
                name = f.name.trim(),
                email = f.email.trim().lowercase(),
                driverCode = f.driverCode.trim(),
                phoneNumber = f.phoneNumber.trim(),
            )
            _actionState.value = when (val r = addDriverUseCase(driver)) {
                is Resource.Success -> { dismissDriverDialog(); UiState.Success(Unit) }
                is Resource.Error   -> UiState.Error(r.message)
                else                -> UiState.Idle
            }
        }
    }

    // Helper to map Resource<Bus> → Resource<T>
    private fun <T> Resource<Bus>.map(transform: (Bus) -> T): Resource<T> = when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error   -> Resource.Error(message)
        else                -> Resource.Loading
    }
}
