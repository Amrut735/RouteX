package com.routex.app.core.firebase;

import com.google.firebase.database.FirebaseDatabase;
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
public final class FirebaseInitializer_Factory implements Factory<FirebaseInitializer> {
  private final Provider<FirebaseDatabase> dbProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public FirebaseInitializer_Factory(Provider<FirebaseDatabase> dbProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.dbProvider = dbProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FirebaseInitializer get() {
    return newInstance(dbProvider.get(), firestoreProvider.get());
  }

  public static FirebaseInitializer_Factory create(Provider<FirebaseDatabase> dbProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FirebaseInitializer_Factory(dbProvider, firestoreProvider);
  }

  public static FirebaseInitializer newInstance(FirebaseDatabase db, FirebaseFirestore firestore) {
    return new FirebaseInitializer(db, firestore);
  }
}
