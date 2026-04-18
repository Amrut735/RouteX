package com.routex.app.auth.presentation.login;

import com.routex.app.auth.domain.usecase.SignInWithEmailUseCase;
import com.routex.app.auth.domain.usecase.SignInWithGoogleUseCase;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<SignInWithEmailUseCase> signInWithEmailProvider;

  private final Provider<SignInWithGoogleUseCase> signInWithGoogleProvider;

  public LoginViewModel_Factory(Provider<SignInWithEmailUseCase> signInWithEmailProvider,
      Provider<SignInWithGoogleUseCase> signInWithGoogleProvider) {
    this.signInWithEmailProvider = signInWithEmailProvider;
    this.signInWithGoogleProvider = signInWithGoogleProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(signInWithEmailProvider.get(), signInWithGoogleProvider.get());
  }

  public static LoginViewModel_Factory create(
      Provider<SignInWithEmailUseCase> signInWithEmailProvider,
      Provider<SignInWithGoogleUseCase> signInWithGoogleProvider) {
    return new LoginViewModel_Factory(signInWithEmailProvider, signInWithGoogleProvider);
  }

  public static LoginViewModel newInstance(SignInWithEmailUseCase signInWithEmail,
      SignInWithGoogleUseCase signInWithGoogle) {
    return new LoginViewModel(signInWithEmail, signInWithGoogle);
  }
}
