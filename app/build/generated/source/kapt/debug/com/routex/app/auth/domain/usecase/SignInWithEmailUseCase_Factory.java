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
public final class SignInWithEmailUseCase_Factory implements Factory<SignInWithEmailUseCase> {
  private final Provider<AuthRepository> repositoryProvider;

  public SignInWithEmailUseCase_Factory(Provider<AuthRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SignInWithEmailUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SignInWithEmailUseCase_Factory create(Provider<AuthRepository> repositoryProvider) {
    return new SignInWithEmailUseCase_Factory(repositoryProvider);
  }

  public static SignInWithEmailUseCase newInstance(AuthRepository repository) {
    return new SignInWithEmailUseCase(repository);
  }
}
