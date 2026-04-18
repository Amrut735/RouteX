package com.routex.app.admin.presentation.dashboard;

import com.routex.app.admin.domain.repository.AdminRepository;
import com.routex.app.admin.domain.usecase.GetAllRoutesUseCase;
import com.routex.app.auth.domain.usecase.GetCurrentUserUseCase;
import com.routex.app.auth.domain.usecase.SignOutUseCase;
import com.routex.app.core.firebase.FirebaseInitializer;
import com.routex.app.maps.domain.usecase.GetBusLocationUseCase;
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
public final class AdminDashboardViewModel_Factory implements Factory<AdminDashboardViewModel> {
  private final Provider<GetAllRoutesUseCase> getAllRoutesProvider;

  private final Provider<AdminRepository> adminRepositoryProvider;

  private final Provider<GetCurrentUserUseCase> getCurrentUserProvider;

  private final Provider<SignOutUseCase> signOutProvider;

  private final Provider<FirebaseInitializer> firebaseInitializerProvider;

  private final Provider<GetBusLocationUseCase> getBusLocationProvider;

  public AdminDashboardViewModel_Factory(Provider<GetAllRoutesUseCase> getAllRoutesProvider,
      Provider<AdminRepository> adminRepositoryProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<SignOutUseCase> signOutProvider,
      Provider<FirebaseInitializer> firebaseInitializerProvider,
      Provider<GetBusLocationUseCase> getBusLocationProvider) {
    this.getAllRoutesProvider = getAllRoutesProvider;
    this.adminRepositoryProvider = adminRepositoryProvider;
    this.getCurrentUserProvider = getCurrentUserProvider;
    this.signOutProvider = signOutProvider;
    this.firebaseInitializerProvider = firebaseInitializerProvider;
    this.getBusLocationProvider = getBusLocationProvider;
  }

  @Override
  public AdminDashboardViewModel get() {
    return newInstance(getAllRoutesProvider.get(), adminRepositoryProvider.get(), getCurrentUserProvider.get(), signOutProvider.get(), firebaseInitializerProvider.get(), getBusLocationProvider.get());
  }

  public static AdminDashboardViewModel_Factory create(
      Provider<GetAllRoutesUseCase> getAllRoutesProvider,
      Provider<AdminRepository> adminRepositoryProvider,
      Provider<GetCurrentUserUseCase> getCurrentUserProvider,
      Provider<SignOutUseCase> signOutProvider,
      Provider<FirebaseInitializer> firebaseInitializerProvider,
      Provider<GetBusLocationUseCase> getBusLocationProvider) {
    return new AdminDashboardViewModel_Factory(getAllRoutesProvider, adminRepositoryProvider, getCurrentUserProvider, signOutProvider, firebaseInitializerProvider, getBusLocationProvider);
  }

  public static AdminDashboardViewModel newInstance(GetAllRoutesUseCase getAllRoutes,
      AdminRepository adminRepository, GetCurrentUserUseCase getCurrentUser, SignOutUseCase signOut,
      FirebaseInitializer firebaseInitializer, GetBusLocationUseCase getBusLocation) {
    return new AdminDashboardViewModel(getAllRoutes, adminRepository, getCurrentUser, signOut, firebaseInitializer, getBusLocation);
  }
}
