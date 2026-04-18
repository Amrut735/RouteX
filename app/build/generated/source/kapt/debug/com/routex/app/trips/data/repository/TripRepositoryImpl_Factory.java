package com.routex.app.trips.data.repository;

import com.google.firebase.auth.FirebaseAuth;
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
public final class TripRepositoryImpl_Factory implements Factory<TripRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> authProvider;

  public TripRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider) {
    this.firestoreProvider = firestoreProvider;
    this.authProvider = authProvider;
  }

  @Override
  public TripRepositoryImpl get() {
    return newInstance(firestoreProvider.get(), authProvider.get());
  }

  public static TripRepositoryImpl_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider) {
    return new TripRepositoryImpl_Factory(firestoreProvider, authProvider);
  }

  public static TripRepositoryImpl newInstance(FirebaseFirestore firestore, FirebaseAuth auth) {
    return new TripRepositoryImpl(firestore, auth);
  }
}
