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
public final class AddDriverUseCase_Factory implements Factory<AddDriverUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public AddDriverUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public AddDriverUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static AddDriverUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new AddDriverUseCase_Factory(repoProvider);
  }

  public static AddDriverUseCase newInstance(AdminRepository repo) {
    return new AddDriverUseCase(repo);
  }
}
