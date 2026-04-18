package com.routex.app.admin.data.source;

import com.google.firebase.firestore.FirebaseFirestore;
import com.routex.app.core.notification.NotificationHelper;
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
public final class AlertDataSource_Factory implements Factory<AlertDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public AlertDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.firestoreProvider = firestoreProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  @Override
  public AlertDataSource get() {
    return newInstance(firestoreProvider.get(), notificationHelperProvider.get());
  }

  public static AlertDataSource_Factory create(Provider<FirebaseFirestore> firestoreProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new AlertDataSource_Factory(firestoreProvider, notificationHelperProvider);
  }

  public static AlertDataSource newInstance(FirebaseFirestore firestore,
      NotificationHelper notificationHelper) {
    return new AlertDataSource(firestore, notificationHelper);
  }
}
