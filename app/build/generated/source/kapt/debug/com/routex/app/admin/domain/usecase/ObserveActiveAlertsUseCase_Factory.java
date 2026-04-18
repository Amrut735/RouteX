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
public final class ObserveActiveAlertsUseCase_Factory implements Factory<ObserveActiveAlertsUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public ObserveActiveAlertsUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ObserveActiveAlertsUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static ObserveActiveAlertsUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new ObserveActiveAlertsUseCase_Factory(repoProvider);
  }

  public static ObserveActiveAlertsUseCase newInstance(AdminRepository repo) {
    return new ObserveActiveAlertsUseCase(repo);
  }
}
