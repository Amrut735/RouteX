package com.routex.app.driver.data.service;

import com.routex.app.core.location.LocationProvider;
import com.routex.app.driver.domain.repository.DriverRepository;
import com.routex.app.trips.domain.repository.TripRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class LocationTrackingService_MembersInjector implements MembersInjector<LocationTrackingService> {
  private final Provider<LocationProvider> locationProvider;

  private final Provider<DriverRepository> driverRepositoryProvider;

  private final Provider<TripRepository> tripRepositoryProvider;

  public LocationTrackingService_MembersInjector(Provider<LocationProvider> locationProvider,
      Provider<DriverRepository> driverRepositoryProvider,
      Provider<TripRepository> tripRepositoryProvider) {
    this.locationProvider = locationProvider;
    this.driverRepositoryProvider = driverRepositoryProvider;
    this.tripRepositoryProvider = tripRepositoryProvider;
  }

  public static MembersInjector<LocationTrackingService> create(
      Provider<LocationProvider> locationProvider,
      Provider<DriverRepository> driverRepositoryProvider,
      Provider<TripRepository> tripRepositoryProvider) {
    return new LocationTrackingService_MembersInjector(locationProvider, driverRepositoryProvider, tripRepositoryProvider);
  }

  @Override
  public void injectMembers(LocationTrackingService instance) {
    injectLocationProvider(instance, locationProvider.get());
    injectDriverRepository(instance, driverRepositoryProvider.get());
    injectTripRepository(instance, tripRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.routex.app.driver.data.service.LocationTrackingService.locationProvider")
  public static void injectLocationProvider(LocationTrackingService instance,
      LocationProvider locationProvider) {
    instance.locationProvider = locationProvider;
  }

  @InjectedFieldSignature("com.routex.app.driver.data.service.LocationTrackingService.driverRepository")
  public static void injectDriverRepository(LocationTrackingService instance,
      DriverRepository driverRepository) {
    instance.driverRepository = driverRepository;
  }

  @InjectedFieldSignature("com.routex.app.driver.data.service.LocationTrackingService.tripRepository")
  public static void injectTripRepository(LocationTrackingService instance,
      TripRepository tripRepository) {
    instance.tripRepository = tripRepository;
  }
}
