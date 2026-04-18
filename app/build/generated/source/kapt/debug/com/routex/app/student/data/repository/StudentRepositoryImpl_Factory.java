package com.routex.app.student.data.repository;

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
public final class StudentRepositoryImpl_Factory implements Factory<StudentRepositoryImpl> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public StudentRepositoryImpl_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public StudentRepositoryImpl get() {
    return newInstance(firestoreProvider.get());
  }

  public static StudentRepositoryImpl_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new StudentRepositoryImpl_Factory(firestoreProvider);
  }

  public static StudentRepositoryImpl newInstance(FirebaseFirestore firestore) {
    return new StudentRepositoryImpl(firestore);
  }
}
