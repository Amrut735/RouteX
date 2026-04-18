package com.routex.app.core.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextAlign;
import com.google.accompanist.permissions.ExperimentalPermissionsApi;
import com.google.accompanist.permissions.MultiplePermissionsState;
import com.google.accompanist.permissions.PermissionStatus;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0003\u001a\u001b\u0010\t\u001a\u00020\u00062\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00060\u000b\u00a2\u0006\u0002\b\fH\u0007\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u00a8\u0006\r"}, d2 = {"LOCATION_PERMISSIONS", "", "", "getLOCATION_PERMISSIONS", "()Ljava/util/List;", "LocationPermissionRationale", "", "state", "Lcom/google/accompanist/permissions/MultiplePermissionsState;", "WithLocationPermission", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "app_debug"})
public final class LocationPermissionHandlerKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> LOCATION_PERMISSIONS = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<java.lang.String> getLOCATION_PERMISSIONS() {
        return null;
    }
    
    /**
     * Wraps [content] with a permission gate.
     * Shows a rationale screen while permissions are missing; renders [content] once granted.
     *
     * Usage:
     * ```
     * WithLocationPermission {
     *    MapScreen(...)
     * }
     * ```
     */
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    public static final void WithLocationPermission(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class})
    @androidx.compose.runtime.Composable()
    private static final void LocationPermissionRationale(com.google.accompanist.permissions.MultiplePermissionsState state) {
    }
}