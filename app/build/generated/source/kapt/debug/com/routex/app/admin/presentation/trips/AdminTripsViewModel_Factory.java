package com.routex.app.admin.presentation.trips;

import com.routex.app.trips.domain.repository.TripRepository;
import com.routex.app.trips.domain.usecase.ObserveAllTripsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AdminTripsViewModel_Factory implements Factory<AdminTripsViewModel> {
  private final Provider<ObserveAllTripsUseCase> observeAllTripsProvider;

  private final Provider<TripRepository> tripRepositoryProvider;

  public AdminTripsViewModel_Factory(Provider<ObserveAllTripsUseCase> observeAllTripsProvider,
      Provider<TripRepository> tripRepositoryProvider) {
    this.observeAllTripsProvider = observeAllTripsProvider;
    this.tripRepositoryProvider = tripRepositoryProvider;
  }

  @Override
  public AdminTripsViewModel get() {
    return newInstance(observeAllTripsProvider.get(), tripRepositoryProvider.get());
  }

  public static AdminTripsViewModel_Factory create(
      Provider<ObserveAllTripsUseCase> observeAllTripsProvider,
      Provider<TripRepository> tripRepositoryProvider) {
    return new AdminTripsViewModel_Factory(observeAllTripsProvider, tripRepositoryProvider);
  }

  public static AdminTripsViewModel newInstance(ObserveAllTripsUseCase observeAllTrips,
      TripRepository tripRepository) {
    return new AdminTripsViewModel(observeAllTrips, tripRepository);
  }
}
