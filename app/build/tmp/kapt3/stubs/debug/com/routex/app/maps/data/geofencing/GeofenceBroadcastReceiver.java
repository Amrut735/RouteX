package com.routex.app.maps.data.geofencing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

/**
 * Receives OS-level geofence transition events.
 *
 * Responsibilities:
 * 1. Forward events to [GeofenceManager]'s in-process event bus (for the live MapScreen snackbar).
 * 2. Post a system notification via [GeofenceNotificationHelper] so students are alerted
 *    even when the app is backgrounded or killed.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2 = {"Lcom/routex/app/maps/data/geofencing/GeofenceBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_debug"})
public final class GeofenceBroadcastReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "GeofenceReceiver";
    private static final int GEOFENCE_NOTIFICATION_BASE_ID = 2000;
    private static final int CAMPUS_NOTIFICATION_ID = 2999;
    @org.jetbrains.annotations.NotNull()
    public static final com.routex.app.maps.data.geofencing.GeofenceBroadcastReceiver.Companion Companion = null;
    
    public GeofenceBroadcastReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/routex/app/maps/data/geofencing/GeofenceBroadcastReceiver$Companion;", "", "()V", "CAMPUS_NOTIFICATION_ID", "", "GEOFENCE_NOTIFICATION_BASE_ID", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}