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
public final class ValidateDriverCodeUseCase_Factory implements Factory<ValidateDriverCodeUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public ValidateDriverCodeUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public ValidateDriverCodeUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static ValidateDriverCodeUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new ValidateDriverCodeUseCase_Factory(authRepositoryProvider);
  }

  public static ValidateDriverCodeUseCase newInstance(AuthRepository authRepository) {
    return new ValidateDriverCodeUseCase(authRepository);
  }
}
