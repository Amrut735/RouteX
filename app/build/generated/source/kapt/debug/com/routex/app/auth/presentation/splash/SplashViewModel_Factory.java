package com.routex.app.auth.presentation.splash;

import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.core.data.UserPreferencesRepository;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserProvider;

  private final Provider<UserPreferencesRepository> preferencesProvider;

  public SplashViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<UserPreferencesRepository> preferencesProvider) {
    this.getCurrentUserProvider = getCurrentUserProvider;
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(getCurrentUserProvider.get(), preferencesProvider.get());
  }

  public static SplashViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<UserPreferencesRepository> preferencesProvider) {
    return new SplashViewModel_Factory(getCurrentUserProvider, preferencesProvider);
  }

  public static SplashViewModel newInstance(GetCurrentUserUseCase getCurrentUser,
      UserPreferencesRepository preferences) {
    return new SplashViewModel(getCurrentUser, preferences);
  }
}
