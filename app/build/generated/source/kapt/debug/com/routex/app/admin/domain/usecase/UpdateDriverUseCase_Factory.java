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
public final class UpdateDriverUseCase_Factory implements Factory<UpdateDriverUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public UpdateDriverUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public UpdateDriverUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static UpdateDriverUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new UpdateDriverUseCase_Factory(repoProvider);
  }

  public static UpdateDriverUseCase newInstance(AdminRepository repo) {
    return new UpdateDriverUseCase(repo);
  }
}
