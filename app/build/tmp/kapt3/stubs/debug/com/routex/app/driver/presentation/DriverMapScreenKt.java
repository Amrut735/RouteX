package com.routex.app.driver.presentation;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.IconButtonDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.MapProperties;
import com.google.maps.android.compose.MapUiSettings;
import com.google.maps.android.compose.MarkerState;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.utils.UiState;
import com.routex.app.trips.domain.model.TripStatus;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a \u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a,\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0003\u001a2\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0003\u001a\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0014H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\u0007\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\"\u0010\u0010\t\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0004\u00a8\u0006\u001d"}, d2 = {"CAMPUS_LATLNG", "Lcom/google/android/gms/maps/model/LatLng;", "CampusColor", "Landroidx/compose/ui/graphics/Color;", "J", "CompletedPolylineColor", "DriverMarkerColor", "NextStopColor", "RoutePolylineColor", "StopCircleFill", "DriverMapScreen", "", "onBack", "Lkotlin/Function0;", "viewModel", "Lcom/routex/app/driver/presentation/DriverMapViewModel;", "NextStopCard", "nextStopName", "", "distanceM", "", "tripStatus", "Lcom/routex/app/trips/domain/model/TripStatus;", "modifier", "Landroidx/compose/ui/Modifier;", "TopBar", "routeName", "formatDistance", "meters", "app_debug"})
public final class DriverMapScreenKt {
    private static final long RoutePolylineColor = 0L;
    private static final long CompletedPolylineColor = 0L;
    private static final long DriverMarkerColor = 0L;
    private static final long NextStopColor = 0L;
    private static final long StopCircleFill = 0L;
    private static final long CampusColor = 0L;
    @org.jetbrains.annotations.NotNull()
    private static final com.google.android.gms.maps.model.LatLng CAMPUS_LATLNG = null;
    
    @androidx.compose.runtime.Composable()
    public static final void DriverMapScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.routex.app.driver.presentation.DriverMapViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopBar(java.lang.String routeName, com.routex.app.trips.domain.model.TripStatus tripStatus, kotlin.jvm.functions.Function0<kotlin.Unit> onBack, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void NextStopCard(java.lang.String nextStopName, float distanceM, com.routex.app.trips.domain.model.TripStatus tripStatus, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String formatDistance(float meters) {
        return null;
    }
}