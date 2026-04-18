package com.routex.app.maps.domain.usecase;

import com.routex.app.maps.domain.repository.MapsRepository;
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
public final class GetBusLocationUseCase_Factory implements Factory<GetBusLocationUseCase> {
  private final Provider<MapsRepository> repositoryProvider;

  public GetBusLocationUseCase_Factory(Provider<MapsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetBusLocationUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetBusLocationUseCase_Factory create(Provider<MapsRepository> repositoryProvider) {
    return new GetBusLocationUseCase_Factory(repositoryProvider);
  }

  public static GetBusLocationUseCase newInstance(MapsRepository repository) {
    return new GetBusLocationUseCase(repository);
  }
}
