package com.routex.app.admin.presentation.manage;

import com.routex.app.admin.domain.usecase.AddRouteUseCase;
import com.routex.app.admin.domain.usecase.DeleteRouteUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
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
public final class ManageRoutesViewModel_Factory implements Factory<ManageRoutesViewModel> {
  private final Provider<GetAllRoutesUseCase> getAllRoutesProvider;

  private final Provider<AddRouteUseCase> addRouteProvider;

  private final Provider<DeleteRouteUseCase> deleteRouteProvider;

  public ManageRoutesViewModel_Factory(Provider<GetAllRoutesUseCase> getAllRoutesProvider,
      Provider<AddRouteUseCase> addRouteProvider,
      Provider<DeleteRouteUseCase> deleteRouteProvider) {
    this.getAllRoutesProvider = getAllRoutesProvider;
    this.addRouteProvider = addRouteProvider;
    this.deleteRouteProvider = deleteRouteProvider;
  }

  @Override
  public ManageRoutesViewModel get() {
    return newInstance(getAllRoutesProvider.get(), addRouteProvider.get(), deleteRouteProvider.get());
  }

  public static ManageRoutesViewModel_Factory create(
      Provider<GetAllRoutesUseCase> getAllRoutesProvider,
      Provider<AddRouteUseCase> addRouteProvider,
      Provider<DeleteRouteUseCase> deleteRouteProvider) {
    return new ManageRoutesViewModel_Factory(getAllRoutesProvider, addRouteProvider, deleteRouteProvider);
  }

  public static ManageRoutesViewModel newInstance(GetAllRoutesUseCase getAllRoutes,
      AddRouteUseCase addRoute, DeleteRouteUseCase deleteRoute) {
    return new ManageRoutesViewModel(getAllRoutes, addRoute, deleteRoute);
  }
}
