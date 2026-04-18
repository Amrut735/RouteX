package com.routex.app.admin.presentation.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.admin.domain.model.AnalyticsSnapshot
import com.routex.app.admin.domain.usecase.GetAnalyticsUseCase
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val getAnalytics: GetAnalyticsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<AnalyticsSnapshot>>(UiState.Loading)
    val state: StateFlow<UiState<AnalyticsSnapshot>> = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = when (val result = getAnalytics()) {
                is Resource.Success -> UiState.Success(result.data)
                is Resource.Error   -> UiState.Error(result.message)
                else -> UiState.Loading
            }
        }
    }
}
