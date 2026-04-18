package com.routex.app.admin.domain.usecase;

import com.routex.app.admin.domain.repository.AdminRepository;
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
public final class SeedBusesUseCase_Factory implements Factory<SeedBusesUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public SeedBusesUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SeedBusesUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static SeedBusesUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new SeedBusesUseCase_Factory(repoProvider);
  }

  public static SeedBusesUseCase newInstance(AdminRepository repo) {
    return new SeedBusesUseCase(repo);
  }
}
