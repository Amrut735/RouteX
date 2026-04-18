package com.routex.app.admin.presentation.trips;

import androidx.lifecycle.ViewModel;
import com.routex.app.core.utils.Resource;
import com.routex.app.core.utils.UiState;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import com.routex.app.trips.domain.repository.TripRepository;
import com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010 \u001a\u00020\u001f2\f\u0010\u0013\u001a\b\u0018\u00010\tj\u0002`\nR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0018\u00010\tj\u0002`\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0018\u00010\tj\u0002`\n0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/routex/app/admin/presentation/trips/AdminTripsViewModel;", "Landroidx/lifecycle/ViewModel;", "observeAllTrips", "Lcom/routex/app/trips/domain/usecase/ObserveAllTripsUseCase;", "tripRepository", "Lcom/routex/app/trips/domain/repository/TripRepository;", "(Lcom/routex/app/trips/domain/usecase/ObserveAllTripsUseCase;Lcom/routex/app/trips/domain/repository/TripRepository;)V", "_filter", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/routex/app/trips/domain/model/TripStatus;", "Lcom/routex/app/admin/presentation/trips/TripFilter;", "_hasReachedEnd", "", "_isLoadingMore", "_pagedTrips", "", "Lcom/routex/app/trips/domain/model/Trip;", "_tripsState", "Lcom/routex/app/core/utils/UiState;", "filter", "Lkotlinx/coroutines/flow/StateFlow;", "getFilter", "()Lkotlinx/coroutines/flow/StateFlow;", "filteredTrips", "getFilteredTrips", "hasReachedEnd", "getHasReachedEnd", "isLoadingMore", "lastDocumentId", "", "loadNextPage", "", "setFilter", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AdminTripsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase observeAllTrips = null;
    @org.jetbrains.annotations.NotNull()
    private final com.routex.app.trips.domain.repository.TripRepository tripRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.trips.domain.model.Trip>>> _tripsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.routex.app.trips.domain.model.TripStatus> _filter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.TripStatus> filter = null;
    
    /**
     * Accumulates extra pages loaded by [loadNextPage].
     * The real-time observer fills the first page; subsequent pages are appended here.
     * [filteredTrips] merges both sources.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.routex.app.trips.domain.model.Trip>> _pagedTrips = null;
    
    /**
     * True while a page is being fetched.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoadingMore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoadingMore = null;
    
    /**
     * True when we have reached the last page (server returned fewer items than [PAGE_SIZE]).
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _hasReachedEnd = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> hasReachedEnd = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastDocumentId;
    
    /**
     * Trips after applying the active filter.
     * Merges the real-time first page ([_tripsState]) with any extra pages from
     * [loadNextPage] ([_pagedTrips]), deduplicating by trip id.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.trips.domain.model.Trip>>> filteredTrips = null;
    
    @javax.inject.Inject()
    public AdminTripsViewModel(@org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase observeAllTrips, @org.jetbrains.annotations.NotNull()
    com.routex.app.trips.domain.repository.TripRepository tripRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.trips.domain.model.TripStatus> getFilter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoadingMore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getHasReachedEnd() {
        return null;
    }
    
    /**
     * Trips after applying the active filter.
     * Merges the real-time first page ([_tripsState]) with any extra pages from
     * [loadNextPage] ([_pagedTrips]), deduplicating by trip id.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.routex.app.core.utils.UiState<java.util.List<com.routex.app.trips.domain.model.Trip>>> getFilteredTrips() {
        return null;
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable()
    com.routex.app.trips.domain.model.TripStatus filter) {
    }
    
    /**
     * Load the next page. Ignores the call if already loading or at end.
     */
    public final void loadNextPage() {
    }
}