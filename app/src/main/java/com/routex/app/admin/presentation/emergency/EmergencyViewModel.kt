package com.routex.app.admin.presentation.emergency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.admin.domain.model.EmergencyType
import com.routex.app.admin.domain.usecase.ObserveActiveAlertsUseCase
import com.routex.app.admin.domain.usecase.ResolveAlertUseCase
import com.routex.app.admin.domain.usecase.SendEmergencyAlertUseCase
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AlertForm(
    val selectedRouteId: String = "",
    val busNumber: String = "",
    val message: String = "",
    val type: EmergencyType = EmergencyType.GENERAL,
)

@HiltViewModel
class EmergencyViewModel @Inject constructor(
    private val sendAlert: SendEmergencyAlertUseCase,
    private val observeAlerts: ObserveActiveAlertsUseCase,
    private val resolveAlert: ResolveAlertUseCase,
    private val getAllRoutes: GetAllRoutesUseCase,
) : ViewModel() {

    private val _activeAlerts = MutableStateFlow<List<EmergencyAlert>>(emptyList())
    val activeAlerts: StateFlow<List<EmergencyAlert>> = _activeAlerts.asStateFlow()

    private val _routes = MutableStateFlow<List<BusRoute>>(emptyList())
    val routes: StateFlow<List<BusRoute>> = _routes.asStateFlow()

    private val _form = MutableStateFlow(AlertForm())
    val form: StateFlow<AlertForm> = _form.asStateFlow()

    private val _sendState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val sendState: StateFlow<UiState<Unit>> = _sendState.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    init {
        observeAlerts().onEach { _activeAlerts.value = it }.launchIn(viewModelScope)
        getAllRoutes().onEach { res ->
            if (res is Resource.Success) _routes.value = res.data
        }.launchIn(viewModelScope)
    }

    fun openDialog()  { _showDialog.value = true }
    fun closeDialog() { _showDialog.value = false; _form.value = AlertForm() }

    fun onFormChange(form: AlertForm) { _form.value = form }

    fun sendAlert(adminName: String) {
        val f = _form.value
        if (f.message.isBlank()) {
            _sendState.value = UiState.Error("Message cannot be empty")
            return
        }
        viewModelScope.launch {
            _sendState.value = UiState.Loading
            val alert = EmergencyAlert(
                routeId   = f.selectedRouteId,
                busNumber = f.busNumber.trim(),
                message   = f.message.trim(),
                type      = f.type,
                sentBy    = adminName,
            )
            _sendState.value = when (val result = sendAlert.invoke(alert)) {
                is Resource.Success -> { closeDialog(); UiState.Success(Unit) }
                is Resource.Error   -> UiState.Error(result.message)
                else -> UiState.Idle
            }
        }
    }

    fun resolveAlert(alertId: String) {
        viewModelScope.launch {
            resolveAlert.invoke(alertId)
        }
    }

    fun resetSendState() { _sendState.value = UiState.Idle }
}
