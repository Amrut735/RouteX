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
public final class GetBusByIdUseCase_Factory implements Factory<GetBusByIdUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public GetBusByIdUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetBusByIdUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetBusByIdUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new GetBusByIdUseCase_Factory(repoProvider);
  }

  public static GetBusByIdUseCase newInstance(AdminRepository repo) {
    return new GetBusByIdUseCase(repo);
  }
}
