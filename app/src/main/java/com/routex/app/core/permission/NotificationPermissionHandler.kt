package com.routex.app.core.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsOff
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

/**
 * Requests the POST_NOTIFICATIONS permission on Android 13+ (API 33+).
 *
 * Behaviour:
 *  - API < 33: no-op, [content] renders immediately (permission not required).
 *  - API ≥ 33, permission already granted: [content] renders immediately.
 *  - API ≥ 33, permission not yet asked: shows system permission prompt automatically.
 *  - API ≥ 33, permanently denied: shows a non-blocking bottom dialog explaining
 *    why notifications matter with a "Settings" button. The user can still use the
 *    app — notifications are degraded but the app does not crash.
 *
 * Usage in any screen:
 * ```kotlin
 * WithNotificationPermission {
 *     StudentDashboardScreen(...)
 * }
 * ```
 * Or at the root in MainActivity to cover all screens at once.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WithNotificationPermission(
    content: @Composable () -> Unit,
) {
    // Only required on Android 13+
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        content()
        return
    }

    val permissionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    // Auto-request once on first composition
    LaunchedEffect(Unit) {
        if (permissionState.status is PermissionStatus.Denied) {
            val shouldShow = (permissionState.status as PermissionStatus.Denied).shouldShowRationale
            if (shouldShow || permissionState.status is PermissionStatus.Denied) {
                permissionState.launchPermissionRequest()
            }
        }
    }

    // Always render content — notifications are not required for core functionality
    content()

    // Show a soft, dismissible rationale dialog only if permanently denied
    val permanentlyDenied = permissionState.status is PermissionStatus.Denied &&
        !(permissionState.status as PermissionStatus.Denied).shouldShowRationale

    if (permanentlyDenied) {
        NotificationPermissionDialog()
    }
}

/**
 * Non-blocking bottom dialog shown when POST_NOTIFICATIONS is permanently denied.
 * The user can dismiss it and continue using the app; ETA/proximity notifications
 * from FCM (background) are also affected, but the core tracking works.
 */
@Composable
private fun NotificationPermissionDialog() {
    val context = LocalContext.current
    var dismissed by remember { mutableStateOf(false) }

    if (dismissed) return

    Dialog(onDismissRequest = { dismissed = true }) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 6.dp,
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    imageVector        = Icons.Rounded.NotificationsOff,
                    contentDescription = null,
                    modifier           = Modifier.size(48.dp),
                    tint               = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text       = "Enable Notifications",
                    style      = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text      = "RouteX uses notifications to alert you when the bus is arriving. Without them, you'll only see updates inside the app.",
                    style     = MaterialTheme.typography.bodySmall,
                    color     = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(4.dp))
                FilledTonalButton(
                    onClick  = {
                        dismissed = true
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            },
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Open App Settings")
                }
                OutlinedButton(
                    onClick  = { dismissed = true },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Continue without notifications")
                }
            }
        }
    }
}
