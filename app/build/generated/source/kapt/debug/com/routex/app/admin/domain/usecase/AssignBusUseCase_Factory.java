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
public final class AssignBusUseCase_Factory implements Factory<AssignBusUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public AssignBusUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public AssignBusUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static AssignBusUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new AssignBusUseCase_Factory(repoProvider);
  }

  public static AssignBusUseCase newInstance(AdminRepository repo) {
    return new AssignBusUseCase(repo);
  }
}
