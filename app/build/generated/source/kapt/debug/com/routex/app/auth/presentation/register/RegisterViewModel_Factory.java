package com.routex.app.auth.presentation.register;

import com.routex.app.auth.domain.usecase.SignUpDriverUseCase;
import com.routex.app.auth.domain.usecase.SignUpWithEmailUseCase;
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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<SignUpWithEmailUseCase> signUpWithEmailProvider;

  private final Provider<SignUpDriverUseCase> signUpDriverProvider;

  public RegisterViewModel_Factory(Provider<SignUpWithEmailUseCase> signUpWithEmailProvider,
      Provider<SignUpDriverUseCase> signUpDriverProvider) {
    this.signUpWithEmailProvider = signUpWithEmailProvider;
    this.signUpDriverProvider = signUpDriverProvider;
  }

  @Override
  public RegisterViewModel get() {
    return newInstance(signUpWithEmailProvider.get(), signUpDriverProvider.get());
  }

  public static RegisterViewModel_Factory create(
      Provider<SignUpWithEmailUseCase> signUpWithEmailProvider,
      Provider<SignUpDriverUseCase> signUpDriverProvider) {
    return new RegisterViewModel_Factory(signUpWithEmailProvider, signUpDriverProvider);
  }

  public static RegisterViewModel newInstance(SignUpWithEmailUseCase signUpWithEmail,
      SignUpDriverUseCase signUpDriver) {
    return new RegisterViewModel(signUpWithEmail, signUpDriver);
  }
}
