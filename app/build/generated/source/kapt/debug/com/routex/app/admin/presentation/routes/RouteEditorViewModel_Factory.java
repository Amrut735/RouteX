package com.routex.app.admin.presentation.routes;

import androidx.lifecycle.SavedStateHandle;
import com.routex.app.admin.domain.usecase.AddRouteUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.UpdateRouteUseCase;
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
public final class RouteEditorViewModel_Factory implements Factory<RouteEditorViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<AddRouteUseCase> addRouteProvider;

  private final Provider<UpdateRouteUseCase> updateRouteProvider;

  private final Provider<GetAllRoutesUseCase> getAllRoutesProvider;

  public RouteEditorViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<AddRouteUseCase> addRouteProvider, Provider<UpdateRouteUseCase> updateRouteProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.addRouteProvider = addRouteProvider;
    this.updateRouteProvider = updateRouteProvider;
    this.getAllRoutesProvider = getAllRoutesProvider;
  }

  @Override
  public RouteEditorViewModel get() {
    return newInstance(savedStateHandleProvider.get(), addRouteProvider.get(), updateRouteProvider.get(), getAllRoutesProvider.get());
  }

  public static RouteEditorViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<AddRouteUseCase> addRouteProvider, Provider<UpdateRouteUseCase> updateRouteProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider) {
    return new RouteEditorViewModel_Factory(savedStateHandleProvider, addRouteProvider, updateRouteProvider, getAllRoutesProvider);
  }

  public static RouteEditorViewModel newInstance(SavedStateHandle savedStateHandle,
      AddRouteUseCase addRoute, UpdateRouteUseCase updateRoute, GetAllRoutesUseCase getAllRoutes) {
    return new RouteEditorViewModel(savedStateHandle, addRoute, updateRoute, getAllRoutes);
  }
}
