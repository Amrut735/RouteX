package com.routex.app.eta.domain.usecase;

import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
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
public final class ObserveEtaUseCase_Factory implements Factory<ObserveEtaUseCase> {
  private final Provider<GetBusLocationUseCase> getBusLocationProvider;

  private final Provider<CalculateEtaUseCase> calculateEtaProvider;

  public ObserveEtaUseCase_Factory(Provider<GetBusLocationUseCase> getBusLocationProvider,
      Provider<CalculateEtaUseCase> calculateEtaProvider) {
    this.getBusLocationProvider = getBusLocationProvider;
    this.calculateEtaProvider = calculateEtaProvider;
  }

  @Override
  public ObserveEtaUseCase get() {
    return newInstance(getBusLocationProvider.get(), calculateEtaProvider.get());
  }

  public static ObserveEtaUseCase_Factory create(
      Provider<GetBusLocationUseCase> getBusLocationProvider,
      Provider<CalculateEtaUseCase> calculateEtaProvider) {
    return new ObserveEtaUseCase_Factory(getBusLocationProvider, calculateEtaProvider);
  }

  public static ObserveEtaUseCase newInstance(GetBusLocationUseCase getBusLocation,
      CalculateEtaUseCase calculateEta) {
    return new ObserveEtaUseCase(getBusLocation, calculateEta);
  }
}
