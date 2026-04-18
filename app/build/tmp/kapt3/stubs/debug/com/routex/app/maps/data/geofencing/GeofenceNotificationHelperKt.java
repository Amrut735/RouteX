package com.routex.app.maps.data.geofencing;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.routex.app.MainActivity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0003"}, d2 = {"CHANNEL_ID", "", "CHANNEL_NAME", "app_debug"})
public final class GeofenceNotificationHelperKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "routex_geofence_channel";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "RouteX Bus Alerts";
}