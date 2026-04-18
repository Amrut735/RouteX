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
public final class AddBusUseCase_Factory implements Factory<AddBusUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public AddBusUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public AddBusUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static AddBusUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new AddBusUseCase_Factory(repoProvider);
  }

  public static AddBusUseCase newInstance(AdminRepository repo) {
    return new AddBusUseCase(repo);
  }
}
