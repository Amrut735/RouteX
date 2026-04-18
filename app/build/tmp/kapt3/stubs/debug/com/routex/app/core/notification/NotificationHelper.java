package com.routex.app.core.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.routex.app.MainActivity;
import com.routex.app.R;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Central helper for posting all non-foreground notifications in RouteX.
 *
 * Notification IDs are deterministic so that repeated updates replace the same
 * notification rather than stacking them (e.g., ETA updates share the same ID
 * within a single route).
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J0\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\u001e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0019"}, d2 = {"Lcom/routex/app/core/notification/NotificationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "manager", "Landroid/app/NotificationManager;", "getManager", "()Landroid/app/NotificationManager;", "manager$delegate", "Lkotlin/Lazy;", "createChannels", "", "postNotification", "id", "", "channelId", "", "title", "body", "priority", "showEtaAlert", "routeId", "showRemoteNotification", "Companion", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy manager$delegate = null;
    private static final int ETA_BASE_ID = 2000;
    private static final int FCM_GENERIC_ID = 9999;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.core.notification.NotificationHelper.Companion Companion = null;
    
    @javax.inject.Inject()
    public NotificationHelper(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.app.NotificationManager getManager() {
        return null;
    }
    
    public final void showEtaAlert(@org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body) {
    }
    
    public final void showRemoteNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body) {
    }
    
    private final void postNotification(int id, java.lang.String channelId, java.lang.String title, java.lang.String body, int priority) {
    }
    
    private final void createChannels() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/routex/app/core/notification/NotificationHelper$Companion;", "", "()V", "ETA_BASE_ID", "", "FCM_GENERIC_ID", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}