package com.routex.app.student.presentation.routes;

import com.routex.app.student.domain.usecase.GetRoutesUseCase;
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
public final class RoutesViewModel_Factory implements Factory<RoutesViewModel> {
  private final Provider<GetRoutesUseCase> getRoutesProvider;

  public RoutesViewModel_Factory(Provider<GetRoutesUseCase> getRoutesProvider) {
    this.getRoutesProvider = getRoutesProvider;
  }

  @Override
  public RoutesViewModel get() {
    return newInstance(getRoutesProvider.get());
  }

  public static RoutesViewModel_Factory create(Provider<GetRoutesUseCase> getRoutesProvider) {
    return new RoutesViewModel_Factory(getRoutesProvider);
  }

  public static RoutesViewModel newInstance(GetRoutesUseCase getRoutes) {
    return new RoutesViewModel(getRoutes);
  }
}
