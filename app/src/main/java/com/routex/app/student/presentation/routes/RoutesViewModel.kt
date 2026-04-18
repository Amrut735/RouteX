package com.routex.app.student.presentation.routes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.GetRoutesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val getRoutes: GetRoutesUseCase,
) : ViewModel() {

    private val _routesState = MutableStateFlow<UiState<List<Route>>>(UiState.Loading)
    val routesState = _routesState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _allRoutes = MutableStateFlow<List<Route>>(emptyList())

    init {
        observeRoutes()
        observeSearch()
    }

    private fun observeRoutes() {
        getRoutes().onEach { resource ->
            when (resource) {
                is Resource.Loading -> _routesState.value = UiState.Loading
                is Resource.Success -> {
                    _allRoutes.value = resource.data
                    applySearch(_searchQuery.value, resource.data)
                }
                is Resource.Error   -> _routesState.value = UiState.Error(resource.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun observeSearch() {
        _searchQuery.onEach { query ->
            applySearch(query, _allRoutes.value)
        }.launchIn(viewModelScope)
    }

    private fun applySearch(query: String, routes: List<Route>) {
        val filtered = if (query.isBlank()) routes
        else routes.filter { route ->
            route.routeName.contains(query, ignoreCase = true) ||
                route.routeNumber.contains(query, ignoreCase = true) ||
                route.startPoint.contains(query, ignoreCase = true) ||
                route.endPoint.contains(query, ignoreCase = true)
        }
        _routesState.value = UiState.Success(filtered)
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}
