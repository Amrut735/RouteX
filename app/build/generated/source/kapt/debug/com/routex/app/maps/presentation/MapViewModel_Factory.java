package com.routex.app.maps.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.maps.data.geofencing.GeofenceManager;
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
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
public final class MapViewModel_Factory implements Factory<MapViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetBusLocationUseCase> getBusLocationProvider;

  private final Provider<GetRouteByIdUseCase> getRouteByIdProvider;

  private final Provider<LocationProvider> locationProvider;

  private final Provider<GeofenceManager> geofenceManagerProvider;

  public MapViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetBusLocationUseCase> getBusLocationProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider,
      Provider<GeofenceManager> geofenceManagerProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getBusLocationProvider = getBusLocationProvider;
    this.getRouteByIdProvider = getRouteByIdProvider;
    this.locationProvider = locationProvider;
    this.geofenceManagerProvider = geofenceManagerProvider;
  }

  @Override
  public MapViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getBusLocationProvider.get(), getRouteByIdProvider.get(), locationProvider.get(), geofenceManagerProvider.get());
  }

  public static MapViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetBusLocationUseCase> getBusLocationProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider,
      Provider<GeofenceManager> geofenceManagerProvider) {
    return new MapViewModel_Factory(savedStateHandleProvider, getBusLocationProvider, getRouteByIdProvider, locationProvider, geofenceManagerProvider);
  }

  public static MapViewModel newInstance(SavedStateHandle savedStateHandle,
      GetBusLocationUseCase getBusLocation, GetRouteByIdUseCase getRouteById,
      LocationProvider locationProvider, GeofenceManager geofenceManager) {
    return new MapViewModel(savedStateHandle, getBusLocation, getRouteById, locationProvider, geofenceManager);
  }
}
