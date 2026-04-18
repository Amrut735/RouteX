package com.routex.app.maps.data.repository;

import com.google.firebase.database.FirebaseDatabase;
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
public final class MapsRepositoryImpl_Factory implements Factory<MapsRepositoryImpl> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public MapsRepositoryImpl_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MapsRepositoryImpl get() {
    return newInstance(databaseProvider.get());
  }

  public static MapsRepositoryImpl_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new MapsRepositoryImpl_Factory(databaseProvider);
  }

  public static MapsRepositoryImpl newInstance(FirebaseDatabase database) {
    return new MapsRepositoryImpl(database);
  }
}
