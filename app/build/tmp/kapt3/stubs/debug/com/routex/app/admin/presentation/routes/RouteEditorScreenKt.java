package com.routex.app.admin.presentation.routes;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.MapProperties;
import com.google.maps.android.compose.MapUiSettings;
import com.google.maps.android.compose.MarkerState;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aV\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u000726\u0010\b\u001a2\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\n\u00a2\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tH\u0003\u001a\u00aa\u0001\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u00182\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u00182\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\u0018H\u0003\u001a\u001a\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"DEFAULT_CENTER", "Lcom/google/android/gms/maps/model/LatLng;", "RenameStopDialog", "", "stop", "Lcom/routex/app/admin/presentation/routes/MapStop;", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "arrivalTime", "RouteDetailsPanel", "routeNumber", "routeName", "busNumber", "driverName", "scheduleTime", "stopsCount", "", "onClose", "onRouteNumberChange", "Lkotlin/Function1;", "onRouteNameChange", "onBusNumberChange", "onDriverNameChange", "onScheduleTimeChange", "RouteEditorScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/admin/presentation/routes/RouteEditorViewModel;", "app_debug"})
public final class RouteEditorScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final com.google.android.gms.maps.model.LatLng DEFAULT_CENTER = null;
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void RouteEditorScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.routes.RouteEditorViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RouteDetailsPanel(java.lang.String routeNumber, java.lang.String routeName, java.lang.String busNumber, java.lang.String driverName, java.lang.String scheduleTime, int stopsCount, kotlin.jvm.functions.Function0<kotlin.Unit> onClose, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRouteNumberChange, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onRouteNameChange, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBusNumberChange, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onDriverNameChange, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onScheduleTimeChange) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RenameStopDialog(com.routex.app.admin.presentation.routes.MapStop stop, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
}