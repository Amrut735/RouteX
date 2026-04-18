package com.routex.app.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.utils.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    // One-shot navigation / snackbar events
    private val _events = MutableSharedFlow<ViewModelEvent>()
    val events = _events.asSharedFlow()

    protected fun emitEvent(event: ViewModelEvent) {
        viewModelScope.launch { _events.emit(event) }
    }

    /** Convenience to run a suspend block on [dispatcher] and push results into [stateFlow]. */
    protected fun <T> launchWithState(
        stateFlow: MutableStateFlow<UiState<T>>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend () -> T,
    ) {
        viewModelScope.launch(dispatcher) {
            stateFlow.value = UiState.Loading
            try {
                stateFlow.value = UiState.Success(block())
            } catch (e: Exception) {
                stateFlow.value = UiState.Error(
                    e.message ?: "An unexpected error occurred",
                    e,
                )
            }
        }
    }
}

sealed class ViewModelEvent {
    data class ShowSnackbar(val message: String) : ViewModelEvent()
    data class Navigate(val route: String) : ViewModelEvent()
    data object NavigateBack : ViewModelEvent()
}
