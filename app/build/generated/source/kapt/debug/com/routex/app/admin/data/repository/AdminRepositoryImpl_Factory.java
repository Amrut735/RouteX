package com.routex.app.admin.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.admin.data.source.AlertDataSource;
import com.routex.app.admin.data.source.BusDataSource;
import com.routex.app.admin.data.source.DriverDataSource;
import com.routex.app.admin.data.source.RouteAdminDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AdminRepositoryImpl_Factory implements Factory<AdminRepositoryImpl> {
  private final Provider<RouteAdminDataSource> routeSourceProvider;

  private final Provider<BusDataSource> busSourceProvider;

  private final Provider<DriverDataSource> driverSourceProvider;

  private final Provider<AlertDataSource> alertSourceProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public AdminRepositoryImpl_Factory(Provider<RouteAdminDataSource> routeSourceProvider,
      Provider<BusDataSource> busSourceProvider, Provider<DriverDataSource> driverSourceProvider,
      Provider<AlertDataSource> alertSourceProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.routeSourceProvider = routeSourceProvider;
    this.busSourceProvider = busSourceProvider;
    this.driverSourceProvider = driverSourceProvider;
    this.alertSourceProvider = alertSourceProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public AdminRepositoryImpl get() {
    return newInstance(routeSourceProvider.get(), busSourceProvider.get(), driverSourceProvider.get(), alertSourceProvider.get(), firestoreProvider.get());
  }

  public static AdminRepositoryImpl_Factory create(
      Provider<RouteAdminDataSource> routeSourceProvider, Provider<BusDataSource> busSourceProvider,
      Provider<DriverDataSource> driverSourceProvider,
      Provider<AlertDataSource> alertSourceProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new AdminRepositoryImpl_Factory(routeSourceProvider, busSourceProvider, driverSourceProvider, alertSourceProvider, firestoreProvider);
  }

  public static AdminRepositoryImpl newInstance(RouteAdminDataSource routeSource,
      BusDataSource busSource, DriverDataSource driverSource, AlertDataSource alertSource,
      FirebaseFirestore firestore) {
    return new AdminRepositoryImpl(routeSource, busSource, driverSource, alertSource, firestore);
  }
}
