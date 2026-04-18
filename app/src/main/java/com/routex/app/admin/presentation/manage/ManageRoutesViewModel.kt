package com.routex.app.admin.presentation.manage

import androidx.lifecycle.viewModelScope
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.usecase.AddRouteUseCase
import com.routex.app.admin.domain.usecase.DeleteRouteUseCase
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase
import com.routex.app.core.base.BaseViewModel
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddRouteForm(
    val routeNumber: String = "",
    val routeName: String = "",
    val startPoint: String = "",
    val endPoint: String = "",
    val busNumber: String = "",
    val driverName: String = "",
    val scheduleTime: String = "",
)

@HiltViewModel
class ManageRoutesViewModel @Inject constructor(
    private val getAllRoutes: GetAllRoutesUseCase,
    private val addRoute: AddRouteUseCase,
    private val deleteRoute: DeleteRouteUseCase,
) : BaseViewModel() {

    private val _routesState = MutableStateFlow<UiState<List<BusRoute>>>(UiState.Loading)
    val routesState = _routesState.asStateFlow()

    private val _actionState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val actionState = _actionState.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog = _showAddDialog.asStateFlow()

    private val _form = MutableStateFlow(AddRouteForm())
    val form = _form.asStateFlow()

    // ── Undo delete ───────────────────────────────────────────────────────────
    /** The last route that was deleted — held until undo window passes. */
    private val _lastDeletedRoute = MutableStateFlow<BusRoute?>(null)
    val lastDeletedRoute = _lastDeletedRoute.asStateFlow()

    /** Signals to the UI to show an undo snackbar. Reset after consumed. */
    private val _showUndoEvent = MutableStateFlow(false)
    val showUndoEvent = _showUndoEvent.asStateFlow()
    // ─────────────────────────────────────────────────────────────────────────

    init {
        observeRoutes()
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

    fun onFormChange(form: AddRouteForm) { _form.value = form }

    fun showDialog() { _showAddDialog.value = true }
    fun dismissDialog() {
        _showAddDialog.value = false
        _form.value = AddRouteForm()
    }

    fun submitAddRoute() {
        val f = _form.value
        if (f.routeNumber.isBlank() || f.routeName.isBlank()) {
            _actionState.value = UiState.Error("Route number and name are required")
            return
        }
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            val route = BusRoute(
                routeNumber  = f.routeNumber.trim(),
                routeName    = f.routeName.trim(),
                startPoint   = f.startPoint.trim(),
                endPoint     = f.endPoint.trim(),
                busNumber    = f.busNumber.trim(),
                driverName   = f.driverName.trim(),
                scheduleTime = f.scheduleTime.trim(),
                isActive     = true,
            )
            when (val result = addRoute(route)) {
                is Resource.Success -> {
                    _actionState.value = UiState.Success(Unit)
                    dismissDialog()
                }
                is Resource.Error -> _actionState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    /**
     * Deletes a route from Firestore but keeps a copy locally so the user
     * can undo within the snackbar window.
     */
    fun deleteRoute(route: BusRoute) {
        viewModelScope.launch {
            _actionState.value = UiState.Loading
            when (val result = deleteRoute.invoke(route.id)) {
                is Resource.Success -> {
                    _lastDeletedRoute.value = route
                    _showUndoEvent.value    = true
                    _actionState.value      = UiState.Success(Unit)
                }
                is Resource.Error -> _actionState.value = UiState.Error(result.message)
                else -> Unit
            }
        }
    }

    /**
     * Called when the user taps UNDO.
     * Re-inserts the previously deleted route using the same document ID
     * so all existing references (bus assignments, etc.) remain valid.
     */
    fun undoDelete() {
        val route = _lastDeletedRoute.value ?: return
        viewModelScope.launch {
            // Re-add with the original ID so foreign keys still match
            addRoute(route)
            _lastDeletedRoute.value = null
        }
        _showUndoEvent.value = false
    }

    /** Call after the snackbar has been dismissed without undo. */
    fun clearUndoState() {
        _lastDeletedRoute.value = null
        _showUndoEvent.value    = false
    }

    fun resetActionState() { _actionState.value = UiState.Idle }
}
