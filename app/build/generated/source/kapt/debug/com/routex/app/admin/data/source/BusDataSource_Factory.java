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
public final class BusDataSource_Factory implements Factory<BusDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public BusDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public BusDataSource get() {
    return newInstance(firestoreProvider.get());
  }

  public static BusDataSource_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new BusDataSource_Factory(firestoreProvider);
  }

  public static BusDataSource newInstance(FirebaseFirestore firestore) {
    return new BusDataSource(firestore);
  }
}
