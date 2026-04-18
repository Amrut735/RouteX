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
public final class GetDriverByAuthUidUseCase_Factory implements Factory<GetDriverByAuthUidUseCase> {
  private final Provider<AdminRepository> repoProvider;

  public GetDriverByAuthUidUseCase_Factory(Provider<AdminRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetDriverByAuthUidUseCase get() {
    return newInstance(repoProvider.get());
  }

  public static GetDriverByAuthUidUseCase_Factory create(Provider<AdminRepository> repoProvider) {
    return new GetDriverByAuthUidUseCase_Factory(repoProvider);
  }

  public static GetDriverByAuthUidUseCase newInstance(AdminRepository repo) {
    return new GetDriverByAuthUidUseCase(repo);
  }
}
