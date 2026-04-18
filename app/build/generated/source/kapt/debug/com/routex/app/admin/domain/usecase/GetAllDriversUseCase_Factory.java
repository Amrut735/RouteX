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
public final class GetAllDriversUseCase_Factory implements Factory<GetAllDriversUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public GetAllDriversUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetAllDriversUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetAllDriversUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new GetAllDriversUseCase_Factory(repoProvider);
  }

  public static GetAllDriversUseCase newInstance(AdminRepository repo) {
    return new GetAllDriversUseCase(repo);
  }
}
