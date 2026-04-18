package com.routex.app.admin.presentation.buses;

import com.routex.app.admin.domain.usecase.AddBusUseCase;
import com.routex.app.admin.domain.usecase.AddDriverUseCase;
import com.routex.app.admin.domain.usecase.AssignBusUseCase;
import com.routex.app.admin.domain.usecase.AssignDriverUseCase;
import com.routex.app.admin.domain.usecase.DeleteBusUseCase;
import com.routex.app.admin.domain.usecase.GetAllBusesUseCase;
import com.routex.app.admin.domain.usecase.GetAllDriversUseCase;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.admin.domain.usecase.SeedBusesUseCase;
import com.routex.app.admin.domain.usecase.SeedDriversUseCase;
import com.routex.app.admin.domain.usecase.SeedRoutesUseCase;
import com.routex.app.admin.domain.usecase.UpdateBusUseCase;
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
public final class BusManagementViewModel_Factory implements Factory<BusManagementViewModel> {
  private final Provider<GetAllBusesUseCase> getAllBusesProvider;

  private final Provider<GetAllRoutesUseCase> getAllRoutesProvider;

  private final Provider<AddBusUseCase> addBusProvider;

  private final Provider<UpdateBusUseCase> updateBusProvider;

  private final Provider<DeleteBusUseCase> deleteBusProvider;

  private final Provider<AssignBusUseCase> assignBusProvider;

  private final Provider<AddDriverUseCase> addDriverUseCaseProvider;

  private final Provider<GetAllDriversUseCase> getAllDriversUseCaseProvider;

  private final Provider<AssignDriverUseCase> assignDriverUseCaseProvider;

  private final Provider<SeedDriversUseCase> seedDriversUseCaseProvider;

  private final Provider<SeedBusesUseCase> seedBusesUseCaseProvider;

  private final Provider<SeedRoutesUseCase> seedRoutesUseCaseProvider;

  public BusManagementViewModel_Factory(Provider<GetAllBusesUseCase> getAllBusesProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider, Provider<AddBusUseCase> addBusProvider,
      Provider<UpdateBusUseCase> updateBusProvider, Provider<DeleteBusUseCase> deleteBusProvider,
      Provider<AssignBusUseCase> assignBusProvider,
      Provider<AddDriverUseCase> addDriverUseCaseProvider,
      Provider<GetAllDriversUseCase> getAllDriversUseCaseProvider,
      Provider<AssignDriverUseCase> assignDriverUseCaseProvider,
      Provider<SeedDriversUseCase> seedDriversUseCaseProvider,
      Provider<SeedBusesUseCase> seedBusesUseCaseProvider,
      Provider<SeedRoutesUseCase> seedRoutesUseCaseProvider) {
    this.getAllBusesProvider = getAllBusesProvider;
    this.getAllRoutesProvider = getAllRoutesProvider;
    this.addBusProvider = addBusProvider;
    this.updateBusProvider = updateBusProvider;
    this.deleteBusProvider = deleteBusProvider;
    this.assignBusProvider = assignBusProvider;
    this.addDriverUseCaseProvider = addDriverUseCaseProvider;
    this.getAllDriversUseCaseProvider = getAllDriversUseCaseProvider;
    this.assignDriverUseCaseProvider = assignDriverUseCaseProvider;
    this.seedDriversUseCaseProvider = seedDriversUseCaseProvider;
    this.seedBusesUseCaseProvider = seedBusesUseCaseProvider;
    this.seedRoutesUseCaseProvider = seedRoutesUseCaseProvider;
  }

  @Override
  public BusManagementViewModel get() {
    return newInstance(getAllBusesProvider.get(), getAllRoutesProvider.get(), addBusProvider.get(), updateBusProvider.get(), deleteBusProvider.get(), assignBusProvider.get(), addDriverUseCaseProvider.get(), getAllDriversUseCaseProvider.get(), assignDriverUseCaseProvider.get(), seedDriversUseCaseProvider.get(), seedBusesUseCaseProvider.get(), seedRoutesUseCaseProvider.get());
  }

  public static BusManagementViewModel_Factory create(
      Provider<GetAllBusesUseCase> getAllBusesProvider,
      Provider<GetAllRoutesUseCase> getAllRoutesProvider, Provider<AddBusUseCase> addBusProvider,
      Provider<UpdateBusUseCase> updateBusProvider, Provider<DeleteBusUseCase> deleteBusProvider,
      Provider<AssignBusUseCase> assignBusProvider,
      Provider<AddDriverUseCase> addDriverUseCaseProvider,
      Provider<GetAllDriversUseCase> getAllDriversUseCaseProvider,
      Provider<AssignDriverUseCase> assignDriverUseCaseProvider,
      Provider<SeedDriversUseCase> seedDriversUseCaseProvider,
      Provider<SeedBusesUseCase> seedBusesUseCaseProvider,
      Provider<SeedRoutesUseCase> seedRoutesUseCaseProvider) {
    return new BusManagementViewModel_Factory(getAllBusesProvider, getAllRoutesProvider, addBusProvider, updateBusProvider, deleteBusProvider, assignBusProvider, addDriverUseCaseProvider, getAllDriversUseCaseProvider, assignDriverUseCaseProvider, seedDriversUseCaseProvider, seedBusesUseCaseProvider, seedRoutesUseCaseProvider);
  }

  public static BusManagementViewModel newInstance(GetAllBusesUseCase getAllBuses,
      GetAllRoutesUseCase getAllRoutes, AddBusUseCase addBus, UpdateBusUseCase updateBus,
      DeleteBusUseCase deleteBus, AssignBusUseCase assignBus, AddDriverUseCase addDriverUseCase,
      GetAllDriversUseCase getAllDriversUseCase, AssignDriverUseCase assignDriverUseCase,
      SeedDriversUseCase seedDriversUseCase, SeedBusesUseCase seedBusesUseCase,
      SeedRoutesUseCase seedRoutesUseCase) {
    return new BusManagementViewModel(getAllBuses, getAllRoutes, addBus, updateBus, deleteBus, assignBus, addDriverUseCase, getAllDriversUseCase, assignDriverUseCase, seedDriversUseCase, seedBusesUseCase, seedRoutesUseCase);
  }
}
