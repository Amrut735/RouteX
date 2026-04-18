package com.routex.app.student.presentation.boarding;

import androidx.lifecycle.SavedStateHandle;
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
public final class AvailableBusesViewModel_Factory implements Factory<AvailableBusesViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetRouteByIdUseCase> getRouteByIdProvider;

  public AvailableBusesViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getRouteByIdProvider = getRouteByIdProvider;
  }

  @Override
  public AvailableBusesViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getRouteByIdProvider.get());
  }

  public static AvailableBusesViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetRouteByIdUseCase> getRouteByIdProvider) {
    return new AvailableBusesViewModel_Factory(savedStateHandleProvider, getRouteByIdProvider);
  }

  public static AvailableBusesViewModel newInstance(SavedStateHandle savedStateHandle,
      GetRouteByIdUseCase getRouteById) {
    return new AvailableBusesViewModel(savedStateHandle, getRouteById);
  }
}
