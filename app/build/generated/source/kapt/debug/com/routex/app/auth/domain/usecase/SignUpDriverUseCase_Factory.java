package com.routex.app.auth.domain.usecase;

import com.routex.app.auth.domain.repository.AuthRepository;
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
public final class SignUpDriverUseCase_Factory implements Factory<SignUpDriverUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public SignUpDriverUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignUpDriverUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SignUpDriverUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new SignUpDriverUseCase_Factory(authRepositoryProvider);
  }

  public static SignUpDriverUseCase newInstance(AuthRepository authRepository) {
    return new SignUpDriverUseCase(authRepository);
  }
}
