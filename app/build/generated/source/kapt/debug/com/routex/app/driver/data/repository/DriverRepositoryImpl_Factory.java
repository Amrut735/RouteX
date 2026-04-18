package com.routex.app.driver.data.repository;

import com.routex.app.maps.domain.repository.MapsRepository;
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
public final class DriverRepositoryImpl_Factory implements Factory<DriverRepositoryImpl> {
  private final Provider<MapsRepository> mapsRepositoryProvider;

  public DriverRepositoryImpl_Factory(Provider<MapsRepository> mapsRepositoryProvider) {
    this.mapsRepositoryProvider = mapsRepositoryProvider;
  }

  @Override
  public DriverRepositoryImpl get() {
    return newInstance(mapsRepositoryProvider.get());
  }

  public static DriverRepositoryImpl_Factory create(
      Provider<MapsRepository> mapsRepositoryProvider) {
    return new DriverRepositoryImpl_Factory(mapsRepositoryProvider);
  }

  public static DriverRepositoryImpl newInstance(MapsRepository mapsRepository) {
    return new DriverRepositoryImpl(mapsRepository);
  }
}
