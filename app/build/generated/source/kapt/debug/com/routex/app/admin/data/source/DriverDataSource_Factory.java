package com.routex.app.admin.data.source;

import com.google.firebase.firestore.FirebaseFirestore;
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
public final class DriverDataSource_Factory implements Factory<DriverDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public DriverDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public DriverDataSource get() {
    return newInstance(firestoreProvider.get());
  }

  public static DriverDataSource_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new DriverDataSource_Factory(firestoreProvider);
  }

  public static DriverDataSource newInstance(FirebaseFirestore firestore) {
    return new DriverDataSource(firestore);
  }
}
