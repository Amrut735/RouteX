package com.routex.app.auth.presentation.onboarding;

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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<UserPreferencesRepository> preferencesProvider;

  public OnboardingViewModel_Factory(Provider<UserPreferencesRepository> preferencesProvider) {
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(preferencesProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<UserPreferencesRepository> preferencesProvider) {
    return new OnboardingViewModel_Factory(preferencesProvider);
  }

  public static OnboardingViewModel newInstance(UserPreferencesRepository preferences) {
    return new OnboardingViewModel(preferences);
  }
}
