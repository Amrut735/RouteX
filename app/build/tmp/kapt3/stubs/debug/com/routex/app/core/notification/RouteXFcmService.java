package com.routex.app.core.notification;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;

/**
 * FCM entry-point.
 *
 * Handles two events:
 * 1. [onNewToken]         — platform rotated the registration token; save to Firestore.
 * 2. [onMessageReceived]  — Cloud Function (or backend) sent a data/notification message.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0019"}, d2 = {"Lcom/routex/app/core/notification/RouteXFcmService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "notificationHelper", "Lcom/routex/app/core/notification/NotificationHelper;", "getNotificationHelper", "()Lcom/routex/app/core/notification/NotificationHelper;", "setNotificationHelper", "(Lcom/routex/app/core/notification/NotificationHelper;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "tokenRepository", "Lcom/routex/app/core/notification/FcmTokenRepository;", "getTokenRepository", "()Lcom/routex/app/core/notification/FcmTokenRepository;", "setTokenRepository", "(Lcom/routex/app/core/notification/FcmTokenRepository;)V", "onDestroy", "", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "app_debug"})
public final class RouteXFcmService extends com.google.firebase.messaging.FirebaseMessagingService {
    @javax.inject.Inject()
    public com.routex.app.core.notification.FcmTokenRepository tokenRepository;
    @javax.inject.Inject()
    public com.routex.app.core.notification.NotificationHelper notificationHelper;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    
    public RouteXFcmService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.core.notification.FcmTokenRepository getTokenRepository() {
        return null;
    }
    
    public final void setTokenRepository(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.notification.FcmTokenRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.routex.app.core.notification.NotificationHelper getNotificationHelper() {
        return null;
    }
    
    public final void setNotificationHelper(@org.jetbrains.annotations.NotNull()
    com.routex.app.core.notification.NotificationHelper p0) {
    }
    
    @java.lang.Override()
    public void onNewToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @java.lang.Override()
    public void onMessageReceived(@org.jetbrains.annotations.NotNull()
    com.google.firebase.messaging.RemoteMessage message) {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
}