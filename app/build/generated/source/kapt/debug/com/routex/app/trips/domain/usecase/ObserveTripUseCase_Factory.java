package com.routex.app.trips.domain.usecase;

import com.routex.app.trips.domain.repository.TripRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ObserveTripUseCase_Factory implements Factory<ObserveTripUseCase> {
  private final Provider<TripRepository> repositoryProvider;

  public ObserveTripUseCase_Factory(Provider<TripRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveTripUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveTripUseCase_Factory create(Provider<TripRepository> repositoryProvider) {
    return new ObserveTripUseCase_Factory(repositoryProvider);
  }

  public static ObserveTripUseCase newInstance(TripRepository repository) {
    return new ObserveTripUseCase(repository);
  }
}
