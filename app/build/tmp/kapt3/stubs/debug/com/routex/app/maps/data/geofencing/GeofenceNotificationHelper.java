package com.routex.app.maps.data.geofencing;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.routex.app.MainActivity;

/**
 * Posts a system notification when the bus enters a stop's geofence.
 * Works even when the app is in the background or killed — called from
 * [GeofenceBroadcastReceiver] which is an OS-level receiver.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ&\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\u000f"}, d2 = {"Lcom/routex/app/maps/data/geofencing/GeofenceNotificationHelper;", "", "()V", "createChannel", "", "context", "Landroid/content/Context;", "notifyArrivedAtCollege", "notificationId", "", "notifyBusNearStop", "stopName", "", "distanceMeters", "", "app_debug"})
public final class GeofenceNotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.maps.data.geofencing.GeofenceNotificationHelper INSTANCE = null;
    
    private GeofenceNotificationHelper() {
        super();
    }
    
    public final void notifyBusNearStop(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String stopName, float distanceMeters, int notificationId) {
    }
    
    /**
     * High-priority notification shown when the bus enters the KLS GIT
     * campus geofence. Notifies all students that they have arrived.
     */
    public final void notifyArrivedAtCollege(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId) {
    }
    
    private final void createChannel(android.content.Context context) {
    }
}