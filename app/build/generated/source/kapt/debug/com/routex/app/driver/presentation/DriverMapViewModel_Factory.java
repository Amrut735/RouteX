package com.routex.app.driver.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import com.routex.app.trips.domain.usecase.ObserveActiveTripForRouteUseCase;
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
public final class DriverMapViewModel_Factory implements Factory<DriverMapViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetRouteByIdUseCase> getRouteByIdProvider;

  private final Provider<LocationProvider> locationProvider;

  private final Provider<ObserveActiveTripForRouteUseCase> observeActiveTripForRouteProvider;

  public DriverMapViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider,
      Provider<ObserveActiveTripForRouteUseCase> observeActiveTripForRouteProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getRouteByIdProvider = getRouteByIdProvider;
    this.locationProvider = locationProvider;
    this.observeActiveTripForRouteProvider = observeActiveTripForRouteProvider;
  }

  @Override
  public DriverMapViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getRouteByIdProvider.get(), locationProvider.get(), observeActiveTripForRouteProvider.get());
  }

  public static DriverMapViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider,
      Provider<ObserveActiveTripForRouteUseCase> observeActiveTripForRouteProvider) {
    return new DriverMapViewModel_Factory(savedStateHandleProvider, getRouteByIdProvider, locationProvider, observeActiveTripForRouteProvider);
  }

  public static DriverMapViewModel newInstance(SavedStateHandle savedStateHandle,
      GetRouteByIdUseCase getRouteById, LocationProvider locationProvider,
      ObserveActiveTripForRouteUseCase observeActiveTripForRoute) {
    return new DriverMapViewModel(savedStateHandle, getRouteById, locationProvider, observeActiveTripForRoute);
  }
}
