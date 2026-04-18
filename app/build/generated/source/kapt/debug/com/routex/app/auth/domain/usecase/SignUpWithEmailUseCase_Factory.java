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
public final class SignUpWithEmailUseCase_Factory implements Factory<SignUpWithEmailUseCase> {
  private final Provider<AuthRepository> repositoryProvider;

  public SignUpWithEmailUseCase_Factory(Provider<AuthRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SignUpWithEmailUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SignUpWithEmailUseCase_Factory create(Provider<AuthRepository> repositoryProvider) {
    return new SignUpWithEmailUseCase_Factory(repositoryProvider);
  }

  public static SignUpWithEmailUseCase newInstance(AuthRepository repository) {
    return new SignUpWithEmailUseCase(repository);
  }
}
