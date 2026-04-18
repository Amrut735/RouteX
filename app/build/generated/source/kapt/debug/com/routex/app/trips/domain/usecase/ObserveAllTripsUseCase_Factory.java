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
public final class ObserveAllTripsUseCase_Factory implements Factory<ObserveAllTripsUseCase> {
  private final Provider<TripRepository> repositoryProvider;

  public ObserveAllTripsUseCase_Factory(Provider<TripRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ObserveAllTripsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static ObserveAllTripsUseCase_Factory create(Provider<TripRepository> repositoryProvider) {
    return new ObserveAllTripsUseCase_Factory(repositoryProvider);
  }

  public static ObserveAllTripsUseCase newInstance(TripRepository repository) {
    return new ObserveAllTripsUseCase(repository);
  }
}
