package com.routex.app.core.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState

// Required permissions for location features (coarse granted first, fine prompt separately)
val LOCATION_PERMISSIONS = buildList {
    add(Manifest.permission.ACCESS_FINE_LOCATION)
    add(Manifest.permission.ACCESS_COARSE_LOCATION)
}

/**
 * Wraps [content] with a permission gate.
 * Shows a rationale screen while permissions are missing; renders [content] once granted.
 *
 * Usage:
 * ```
 * WithLocationPermission {
 *     MapScreen(...)
 * }
 * ```
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WithLocationPermission(
    content: @Composable () -> Unit,
) {
    val permissionsState = rememberMultiplePermissionsState(LOCATION_PERMISSIONS)

    when {
        permissionsState.allPermissionsGranted -> content()
        else -> LocationPermissionRationale(permissionsState)
    }
}

// ── Rationale / denied screen ──────────────────────────────────────────────────

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LocationPermissionRationale(state: MultiplePermissionsState) {
    val context = LocalContext.current

    // Auto-request on first composition
    LaunchedEffect(Unit) {
        if (!state.shouldShowRationale) {
            state.launchMultiplePermissionRequest()
        }
    }

    val permanentlyDenied = state.permissions.any {
        it.status is PermissionStatus.Denied &&
            !(it.status as PermissionStatus.Denied).shouldShowRationale
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Rounded.LocationOff,
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Location Permission Required",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = if (permanentlyDenied)
                "Location permission was denied. Please enable it in App Settings to use live tracking."
            else
                "RouteX needs your location to show the live map and track the bus near you.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(28.dp))

        if (permanentlyDenied) {
            TextButton(
                onClick = {
                    context.startActivity(
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        },
                    )
                },
            ) {
                Text("Open App Settings")
            }
        } else {
            TextButton(onClick = { state.launchMultiplePermissionRequest() }) {
                Text("Grant Permission")
            }
        }
    }
}
