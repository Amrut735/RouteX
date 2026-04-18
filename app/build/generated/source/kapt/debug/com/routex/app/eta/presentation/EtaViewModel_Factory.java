package com.routex.app.eta.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.routex.app.core.data.UserPreferencesRepository;
import com.routex.app.core.notification.FcmTokenRepository;
import com.routex.app.core.notification.NotificationHelper;
import com.routex.app.eta.domain.usecase.ObserveEtaUseCase;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
import com.routex.app.student.domain.usecase.GetRoutesUseCase;
import com.routex.app.student.domain.usecase.MissedBusPredictionUseCase;
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
public final class EtaViewModel_Factory implements Factory<EtaViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetRouteByIdUseCase> getRouteProvider;

  private final Provider<GetRoutesUseCase> getRoutesProvider;

  private final Provider<ObserveEtaUseCase> observeEtaProvider;

  private final Provider<UserPreferencesRepository> prefsProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  private final Provider<FcmTokenRepository> fcmTokenRepositoryProvider;

  private final Provider<MissedBusPredictionUseCase> missedBusPredictionProvider;

  public EtaViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteProvider, Provider<GetRoutesUseCase> getRoutesProvider,
      Provider<ObserveEtaUseCase> observeEtaProvider,
      Provider<UserPreferencesRepository> prefsProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<FcmTokenRepository> fcmTokenRepositoryProvider,
      Provider<MissedBusPredictionUseCase> missedBusPredictionProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getRouteProvider = getRouteProvider;
    this.getRoutesProvider = getRoutesProvider;
    this.observeEtaProvider = observeEtaProvider;
    this.prefsProvider = prefsProvider;
    this.notificationHelperProvider = notificationHelperProvider;
    this.fcmTokenRepositoryProvider = fcmTokenRepositoryProvider;
    this.missedBusPredictionProvider = missedBusPredictionProvider;
  }

  @Override
  public EtaViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getRouteProvider.get(), getRoutesProvider.get(), observeEtaProvider.get(), prefsProvider.get(), notificationHelperProvider.get(), fcmTokenRepositoryProvider.get(), missedBusPredictionProvider.get());
  }

  public static EtaViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteProvider, Provider<GetRoutesUseCase> getRoutesProvider,
      Provider<ObserveEtaUseCase> observeEtaProvider,
      Provider<UserPreferencesRepository> prefsProvider,
      Provider<NotificationHelper> notificationHelperProvider,
      Provider<FcmTokenRepository> fcmTokenRepositoryProvider,
      Provider<MissedBusPredictionUseCase> missedBusPredictionProvider) {
    return new EtaViewModel_Factory(savedStateHandleProvider, getRouteProvider, getRoutesProvider, observeEtaProvider, prefsProvider, notificationHelperProvider, fcmTokenRepositoryProvider, missedBusPredictionProvider);
  }

  public static EtaViewModel newInstance(SavedStateHandle savedStateHandle,
      GetRouteByIdUseCase getRoute, GetRoutesUseCase getRoutes, ObserveEtaUseCase observeEta,
      UserPreferencesRepository prefs, NotificationHelper notificationHelper,
      FcmTokenRepository fcmTokenRepository, MissedBusPredictionUseCase missedBusPrediction) {
    return new EtaViewModel(savedStateHandle, getRoute, getRoutes, observeEta, prefs, notificationHelper, fcmTokenRepository, missedBusPrediction);
  }
}
