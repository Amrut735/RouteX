package com.routex.app.core.notification;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RouteXFcmService_MembersInjector implements MembersInjector<RouteXFcmService> {
  private final Provider<FcmTokenRepository> tokenRepositoryProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public RouteXFcmService_MembersInjector(Provider<FcmTokenRepository> tokenRepositoryProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.tokenRepositoryProvider = tokenRepositoryProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public static MembersInjector<RouteXFcmService> create(
      Provider<FcmTokenRepository> tokenRepositoryProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new RouteXFcmService_MembersInjector(tokenRepositoryProvider, notificationHelperProvider);
  }

  @Override
  public void injectMembers(RouteXFcmService instance) {
    injectTokenRepository(instance, tokenRepositoryProvider.get());
    injectNotificationHelper(instance, notificationHelperProvider.get());
  }

  @InjectedFieldSignature("com.routex.app.core.notification.RouteXFcmService.tokenRepository")
  public static void injectTokenRepository(RouteXFcmService instance,
      FcmTokenRepository tokenRepository) {
    instance.tokenRepository = tokenRepository;
  }

  @InjectedFieldSignature("com.routex.app.core.notification.RouteXFcmService.notificationHelper")
  public static void injectNotificationHelper(RouteXFcmService instance,
      NotificationHelper notificationHelper) {
    instance.notificationHelper = notificationHelper;
  }
}
