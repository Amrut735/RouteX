package com.routex.app.core.ui;

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
public final class ThemeViewModel_Factory implements Factory<ThemeViewModel> {
  private final Provider<UserPreferencesRepository> preferencesRepositoryProvider;

  public ThemeViewModel_Factory(Provider<UserPreferencesRepository> preferencesRepositoryProvider) {
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
  }

  @Override
  public ThemeViewModel get() {
    return newInstance(preferencesRepositoryProvider.get());
  }

  public static ThemeViewModel_Factory create(
      Provider<UserPreferencesRepository> preferencesRepositoryProvider) {
    return new ThemeViewModel_Factory(preferencesRepositoryProvider);
  }

  public static ThemeViewModel newInstance(UserPreferencesRepository preferencesRepository) {
    return new ThemeViewModel(preferencesRepository);
  }
}
