package com.routex.app.admin.presentation.analytics;

import com.routex.app.admin.domain.usecase.GetAnalyticsUseCase;
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
public final class AnalyticsViewModel_Factory implements Factory<AnalyticsViewModel> {
  private final Provider<GetAnalyticsUseCase> getAnalyticsProvider;

  public AnalyticsViewModel_Factory(Provider<GetAnalyticsUseCase> getAnalyticsProvider) {
    this.getAnalyticsProvider = getAnalyticsProvider;
  }

  @Override
  public AnalyticsViewModel get() {
    return newInstance(getAnalyticsProvider.get());
  }

  public static AnalyticsViewModel_Factory create(
      Provider<GetAnalyticsUseCase> getAnalyticsProvider) {
    return new AnalyticsViewModel_Factory(getAnalyticsProvider);
  }

  public static AnalyticsViewModel newInstance(GetAnalyticsUseCase getAnalytics) {
    return new AnalyticsViewModel(getAnalytics);
  }
}
