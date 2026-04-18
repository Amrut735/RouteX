package com.routex.app.driver.presentation;

import android.content.Context;
import com.routex.app.admin.domain.usecase.GetBusByIdUseCase;
import com.routex.app.admin.domain.usecase.GetDriverByAuthUidUseCase;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.network.NetworkMonitor;
import com.routex.app.driver.domain.repository.DriverRepository;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import com.routex.app.trips.domain.usecase.EndTripUseCase;
import com.routex.app.trips.domain.usecase.ObserveTripUseCase;
import com.routex.app.trips.domain.usecase.StartTripUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DriverViewModel_Factory implements Factory<DriverViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserProvider;

  private final Provider<GetRoutesUseCase> getRoutesProvider;

  private final Provider<SignOutUseCase> signOutProvider;

  private final Provider<StartTripUseCase> startTripUseCaseProvider;

  private final Provider<EndTripUseCase> endTripUseCaseProvider;

  private final Provider<ObserveTripUseCase> observeTripUseCaseProvider;

  private final Provider<GetDriverByAuthUidUseCase> getDriverByAuthUidProvider;

  private final Provider<GetBusByIdUseCase> getBusByIdProvider;

  private final Provider<DriverRepository> driverRepositoryProvider;

  private final Provider<NetworkMonitor> networkMonitorProvider;

  public DriverViewModel_Factory(Provider<Context> contextProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<GetRoutesUseCase> getRoutesProvider, Provider<SignOutUseCase> signOutProvider,
      Provider<StartTripUseCase> startTripUseCaseProvider,
      Provider<EndTripUseCase> endTripUseCaseProvider,
      Provider<ObserveTripUseCase> observeTripUseCaseProvider,
      Provider<GetDriverByAuthUidUseCase> getDriverByAuthUidProvider,
      Provider<GetBusByIdUseCase> getBusByIdProvider,
      Provider<DriverRepository> driverRepositoryProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    this.contextProvider = contextProvider;
    this.getCurrentUserProvider = getCurrentUserProvider;
    this.getRoutesProvider = getRoutesProvider;
    this.signOutProvider = signOutProvider;
    this.startTripUseCaseProvider = startTripUseCaseProvider;
    this.endTripUseCaseProvider = endTripUseCaseProvider;
    this.observeTripUseCaseProvider = observeTripUseCaseProvider;
    this.getDriverByAuthUidProvider = getDriverByAuthUidProvider;
    this.getBusByIdProvider = getBusByIdProvider;
    this.driverRepositoryProvider = driverRepositoryProvider;
    this.networkMonitorProvider = networkMonitorProvider;
  }

  @Override
  public DriverViewModel get() {
    return newInstance(contextProvider.get(), getCurrentUserProvider.get(), getRoutesProvider.get(), signOutProvider.get(), startTripUseCaseProvider.get(), endTripUseCaseProvider.get(), observeTripUseCaseProvider.get(), getDriverByAuthUidProvider.get(), getBusByIdProvider.get(), driverRepositoryProvider.get(), networkMonitorProvider.get());
  }

  public static DriverViewModel_Factory create(Provider<Context> contextProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<GetRoutesUseCase> getRoutesProvider, Provider<SignOutUseCase> signOutProvider,
      Provider<StartTripUseCase> startTripUseCaseProvider,
      Provider<EndTripUseCase> endTripUseCaseProvider,
      Provider<ObserveTripUseCase> observeTripUseCaseProvider,
      Provider<GetDriverByAuthUidUseCase> getDriverByAuthUidProvider,
      Provider<GetBusByIdUseCase> getBusByIdProvider,
      Provider<DriverRepository> driverRepositoryProvider,
      Provider<NetworkMonitor> networkMonitorProvider) {
    return new DriverViewModel_Factory(contextProvider, getCurrentUserProvider, getRoutesProvider, signOutProvider, startTripUseCaseProvider, endTripUseCaseProvider, observeTripUseCaseProvider, getDriverByAuthUidProvider, getBusByIdProvider, driverRepositoryProvider, networkMonitorProvider);
  }

  public static DriverViewModel newInstance(Context context, GetCurrentUserUseCase getCurrentUser,
      GetRoutesUseCase getRoutes, SignOutUseCase signOut, StartTripUseCase startTripUseCase,
      EndTripUseCase endTripUseCase, ObserveTripUseCase observeTripUseCase,
      GetDriverByAuthUidUseCase getDriverByAuthUid, GetBusByIdUseCase getBusById,
      DriverRepository driverRepository, NetworkMonitor networkMonitor) {
    return new DriverViewModel(context, getCurrentUser, getRoutes, signOut, startTripUseCase, endTripUseCase, observeTripUseCase, getDriverByAuthUid, getBusById, driverRepository, networkMonitor);
  }
}
