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
public final class UpdateRouteUseCase_Factory implements Factory<UpdateRouteUseCase> {
  private final Provider<AdminRepository> repositoryProvider;

  public UpdateRouteUseCase_Factory(Provider<AdminRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateRouteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateRouteUseCase_Factory create(Provider<AdminRepository> repositoryProvider) {
    return new UpdateRouteUseCase_Factory(repositoryProvider);
  }

  public static UpdateRouteUseCase newInstance(AdminRepository repository) {
    return new UpdateRouteUseCase(repository);
  }
}
