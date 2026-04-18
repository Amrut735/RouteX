package com.routex.app.admin.presentation.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routex.app.core.utils.Resource
import com.routex.app.core.utils.UiState
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import com.routex.app.trips.domain.repository.TripRepository
import com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/** null = "All" filter */
typealias TripFilter = TripStatus?

@HiltViewModel
class AdminTripsViewModel @Inject constructor(
    private val observeAllTrips: ObserveAllTripsUseCase,
    private val tripRepository:  TripRepository,
) : ViewModel() {

    private val _tripsState = MutableStateFlow<UiState<List<Trip>>>(UiState.Loading)

    private val _filter = MutableStateFlow<TripFilter>(null)
    val filter = _filter.asStateFlow()

    // ── Pagination state ──────────────────────────────────────────────────────

    /**
     * Accumulates extra pages loaded by [loadNextPage].
     * The real-time observer fills the first page; subsequent pages are appended here.
     * [filteredTrips] merges both sources.
     */
    private val _pagedTrips = MutableStateFlow<List<Trip>>(emptyList())

    /** True while a page is being fetched. */
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()

    /** True when we have reached the last page (server returned fewer items than [PAGE_SIZE]). */
    private val _hasReachedEnd = MutableStateFlow(false)
    val hasReachedEnd = _hasReachedEnd.asStateFlow()

    private var lastDocumentId: String? = null

    /**
     * Trips after applying the active filter.
     * Merges the real-time first page ([_tripsState]) with any extra pages from
     * [loadNextPage] ([_pagedTrips]), deduplicating by trip id.
     */
    val filteredTrips = combine(_tripsState, _pagedTrips, _filter) { state, extras, filter ->
        when (state) {
            is UiState.Success -> {
                val merged = (state.data + extras).distinctBy { it.id }
                val list   = if (filter == null) merged
                             else merged.filter { it.status == filter }
                UiState.Success(list)
            }
            else -> state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState.Loading)

    init {
        // Real-time observer for the most-recent page (first 20 docs)
        observeAllTrips()
            .onEach { resource ->
                _tripsState.value = when (resource) {
                    is Resource.Loading -> UiState.Loading
                    is Resource.Success -> UiState.Success(resource.data)
                    is Resource.Error   -> UiState.Error(resource.message)
                }
            }
            .launchIn(viewModelScope)
    }

    fun setFilter(filter: TripFilter) { _filter.value = filter }

    // ── Pagination ────────────────────────────────────────────────────────────

    /** Load the next page. Ignores the call if already loading or at end. */
    fun loadNextPage() {
        if (_isLoadingMore.value || _hasReachedEnd.value) return
        viewModelScope.launch {
            _isLoadingMore.value = true
            when (val result = tripRepository.getPagedTrips(
                afterDocumentId = lastDocumentId,
                pageSize        = TripRepository.PAGE_SIZE,
            )) {
                is Resource.Success -> {
                    val newItems      = result.data
                    _pagedTrips.value = (_pagedTrips.value + newItems).distinctBy { it.id }
                    lastDocumentId    = newItems.lastOrNull()?.id
                    _hasReachedEnd.value = newItems.size < TripRepository.PAGE_SIZE
                }
                is Resource.Error -> {
                    // Surface error via existing state; don't crash the list
                    _tripsState.value = UiState.Error(result.message)
                }
                else -> Unit
            }
            _isLoadingMore.value = false
        }
    }
}
