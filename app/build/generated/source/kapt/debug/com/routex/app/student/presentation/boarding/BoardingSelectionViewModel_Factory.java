package com.routex.app.student.presentation.boarding;

import androidx.lifecycle.SavedStateHandle;
import com.routex.app.core.location.LocationProvider;
import com.routex.app.student.domain.usecase.GetRouteByIdUseCase;
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
public final class BoardingSelectionViewModel_Factory implements Factory<BoardingSelectionViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetRouteByIdUseCase> getRouteByIdProvider;

  private final Provider<LocationProvider> locationProvider;

  public BoardingSelectionViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getRouteByIdProvider = getRouteByIdProvider;
    this.locationProvider = locationProvider;
  }

  @Override
  public BoardingSelectionViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getRouteByIdProvider.get(), locationProvider.get());
  }

  public static BoardingSelectionViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider,
      Provider<LocationProvider> locationProvider) {
    return new BoardingSelectionViewModel_Factory(savedStateHandleProvider, getRouteByIdProvider, locationProvider);
  }

  public static BoardingSelectionViewModel newInstance(SavedStateHandle savedStateHandle,
      GetRouteByIdUseCase getRouteById, LocationProvider locationProvider) {
    return new BoardingSelectionViewModel(savedStateHandle, getRouteById, locationProvider);
  }
}
