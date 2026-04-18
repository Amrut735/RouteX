package com.routex.app.core.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;
import com.google.accompanist.permissions.PermissionStatus;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a\u001b\u0010\u0002\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0004\u00a2\u0006\u0002\b\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"NotificationPermissionDialog", "", "WithNotificationPermission", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "app_debug"})
public final class NotificationPermissionHandlerKt {
    
    /**
     * Requests the POST_NOTIFICATIONS permission on Android 13+ (API 33+).
     *
     * Behaviour:
     * - API < 33: no-op, [content] renders immediately (permission not required).
     * - API ≥ 33, permission already granted: [content] renders immediately.
     * - API ≥ 33, permission not yet asked: shows system permission prompt automatically.
     * - API ≥ 33, permanently denied: shows a non-blocking bottom dialog explaining
     *   why notifications matter with a "Settings" button. The user can still use the
     *   app — notifications are degraded but the app does not crash.
     *
     * Usage in any screen:
     * ```kotlin
     * WithNotificationPermission {
     *    StudentDashboardScreen(...)
     * }
     * ```
     * Or at the root in MainActivity to cover all screens at once.
     */
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void WithNotificationPermission(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    /**
     * Non-blocking bottom dialog shown when POST_NOTIFICATIONS is permanently denied.
     * The user can dismiss it and continue using the app; ETA/proximity notifications
     * from FCM (background) are also affected, but the core tracking works.
     */
    @androidx.compose.runtime.Composable()
    private static final void NotificationPermissionDialog() {
    }
}