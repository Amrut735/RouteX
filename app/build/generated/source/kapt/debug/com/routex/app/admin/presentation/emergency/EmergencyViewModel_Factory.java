package com.routex.app.admin.presentation.emergency;

import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.ObserveActiveAlertsUseCase;
import com.routex.app.admin.domain.usecase.ResolveAlertUseCase;
import com.routex.app.admin.domain.usecase.SendEmergencyAlertUseCase;
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
public final class EmergencyViewModel_Factory implements Factory<EmergencyViewModel> {
  private final Provider<SendEmergencyAlertUseCase> sendAlertProvider;

  private final Provider<ObserveActiveAlertsUseCase> observeAlertsProvider;

  private final Provider<ResolveAlertUseCase> resolveAlertProvider;

  private final Provider<GetAllRoutesUseCase> getAllRoutesProvider;

  public EmergencyViewModel_Factory(Provider<SendEmergencyAlertUseCase> sendAlertProvider,
      Provider<ObserveActiveAlertsUseCase> observeAlertsProvider,
      Provider<ResolveAlertUseCase> resolveAlertProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider) {
    this.sendAlertProvider = sendAlertProvider;
    this.observeAlertsProvider = observeAlertsProvider;
    this.resolveAlertProvider = resolveAlertProvider;
    this.getAllRoutesProvider = getAllRoutesProvider;
  }

  @Override
  public EmergencyViewModel get() {
    return newInstance(sendAlertProvider.get(), observeAlertsProvider.get(), resolveAlertProvider.get(), getAllRoutesProvider.get());
  }

  public static EmergencyViewModel_Factory create(
      Provider<SendEmergencyAlertUseCase> sendAlertProvider,
      Provider<ObserveActiveAlertsUseCase> observeAlertsProvider,
      Provider<ResolveAlertUseCase> resolveAlertProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider) {
    return new EmergencyViewModel_Factory(sendAlertProvider, observeAlertsProvider, resolveAlertProvider, getAllRoutesProvider);
  }

  public static EmergencyViewModel newInstance(SendEmergencyAlertUseCase sendAlert,
      ObserveActiveAlertsUseCase observeAlerts, ResolveAlertUseCase resolveAlert,
      GetAllRoutesUseCase getAllRoutes) {
    return new EmergencyViewModel(sendAlert, observeAlerts, resolveAlert, getAllRoutes);
  }
}
