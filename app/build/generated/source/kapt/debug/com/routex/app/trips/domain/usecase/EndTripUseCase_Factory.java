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
public final class EndTripUseCase_Factory implements Factory<EndTripUseCase> {
  private final Provider<TripRepository> repositoryProvider;

  public EndTripUseCase_Factory(Provider<TripRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EndTripUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static EndTripUseCase_Factory create(Provider<TripRepository> repositoryProvider) {
    return new EndTripUseCase_Factory(repositoryProvider);
  }

  public static EndTripUseCase newInstance(TripRepository repository) {
    return new EndTripUseCase(repository);
  }
}
