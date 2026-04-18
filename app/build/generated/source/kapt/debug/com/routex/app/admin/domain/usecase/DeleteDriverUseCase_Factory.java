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
public final class DeleteDriverUseCase_Factory implements Factory<DeleteDriverUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public DeleteDriverUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public DeleteDriverUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static DeleteDriverUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new DeleteDriverUseCase_Factory(repoProvider);
  }

  public static DeleteDriverUseCase newInstance(AdminRepository repo) {
    return new DeleteDriverUseCase(repo);
  }
}
