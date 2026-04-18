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
public final class GetAllRoutesUseCase_Factory implements Factory<GetAllRoutesUseCase> {
  private final Provider<AdminRepository> repositoryProvider;

  public GetAllRoutesUseCase_Factory(Provider<AdminRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllRoutesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllRoutesUseCase_Factory create(Provider<AdminRepository> repositoryProvider) {
    return new GetAllRoutesUseCase_Factory(repositoryProvider);
  }

  public static GetAllRoutesUseCase newInstance(AdminRepository repository) {
    return new GetAllRoutesUseCase(repository);
  }
}
