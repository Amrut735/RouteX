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
public final class DeleteRouteUseCase_Factory implements Factory<DeleteRouteUseCase> {
  private final Provider<AdminRepository> repositoryProvider;

  public DeleteRouteUseCase_Factory(Provider<AdminRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteRouteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteRouteUseCase_Factory create(Provider<AdminRepository> repositoryProvider) {
    return new DeleteRouteUseCase_Factory(repositoryProvider);
  }

  public static DeleteRouteUseCase newInstance(AdminRepository repository) {
    return new DeleteRouteUseCase(repository);
  }
}
