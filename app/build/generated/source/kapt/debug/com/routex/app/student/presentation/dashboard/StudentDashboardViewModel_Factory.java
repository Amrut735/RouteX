package com.routex.app.student.presentation.dashboard;

import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.network.NetworkMonitor;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
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
public final class StudentDashboardViewModel_Factory implements Factory<StudentDashboardViewModel> {
  private final Provider<GetRoutesUseCase> getRoutesProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserProvider;

  private final Provider<SignOutUseCase> signOutProvider;

  private final Provider<ObserveAllTripsUseCase> observeAllTripsProvider;

  private final Provider<NetworkMonitor> networkMonitorProvider;

  public StudentDashboardViewModel_Factory(Provider<GetRoutesUseCase> getRoutesProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<SignOutUseCase> signOutProvider,
      Provider<ObserveAllTripsUseCase> observeAllTripsProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    this.getRoutesProvider = getRoutesProvider;
    this.getCurrentUserProvider = getCurrentUserProvider;
    this.signOutProvider = signOutProvider;
    this.observeAllTripsProvider = observeAllTripsProvider;
    this.networkMonitorProvider = networkMonitorProvider;
  }

  @Override
  public StudentDashboardViewModel get() {
    return newInstance(getRoutesProvider.get(), getCurrentUserProvider.get(), signOutProvider.get(), observeAllTripsProvider.get(), networkMonitorProvider.get());
  }

  public static StudentDashboardViewModel_Factory create(
      Provider<GetRoutesUseCase> getRoutesProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<SignOutUseCase> signOutProvider,
      Provider<ObserveAllTripsUseCase> observeAllTripsProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    return new StudentDashboardViewModel_Factory(getRoutesProvider, getCurrentUserProvider, signOutProvider, observeAllTripsProvider, networkMonitorProvider);
  }

  public static StudentDashboardViewModel newInstance(GetRoutesUseCase getRoutes,
      GetCurrentUserUseCase getCurrentUser, SignOutUseCase signOut,
      ObserveAllTripsUseCase observeAllTrips, NetworkMonitor networkMonitor) {
    return new StudentDashboardViewModel(getRoutes, getCurrentUser, signOut, observeAllTrips, networkMonitor);
  }
}
