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

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u000e\u0010\u0000\"\u0004\u0018\u00010\u00012\u0004\u0018\u00010\u0001\u00a8\u0006\u0002"}, d2 = {"TripFilter", "Lcom/routex/app/trips/domain/model/TripStatus;", "app_debug"})
public final class AdminTripsViewModelKt {
}