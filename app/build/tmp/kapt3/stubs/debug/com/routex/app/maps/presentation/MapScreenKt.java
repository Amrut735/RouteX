package com.routex.app.maps.presentation;

import androidx.compose.animation.core.Spring;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarDuration;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.MapProperties;
import com.google.maps.android.compose.MapType;
import com.google.maps.android.compose.MapUiSettings;
import com.google.maps.android.compose.MarkerState;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.utils.UiState;
import com.routex.app.maps.domain.model.BusLocationUpdate;
import com.routex.app.maps.domain.model.GeofenceEvent;
import com.routex.app.maps.domain.model.GeofenceTransition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003\u001a(\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003\u001a,\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00192\u0006\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003\u001a\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0017H\u0003\u001a\"\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00192\b\b\u0002\u0010\'\u001a\u00020(H\u0007\u001a,\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00192\b\b\u0002\u0010-\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\r\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u000e\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u000f\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00060"}, d2 = {"APPROACHING_BG", "Landroidx/compose/ui/graphics/Color;", "J", "CAMPUS_FILL_COLOR", "CAMPUS_LATLNG", "Lcom/google/android/gms/maps/model/LatLng;", "CAMPUS_STROKE_COLOR", "DEFAULT_CAMPUS", "DoubleVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "GEOFENCE_COLOR", "POLYLINE_COMPLETED", "POLYLINE_REMAINING", "STOP_CIRCLE_COLOR", "ArrivingAtCollegeBanner", "", "modifier", "Landroidx/compose/ui/Modifier;", "BusMarker", "position", "isOnline", "", "busNumber", "", "heading", "", "BusStatusCard", "update", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "nextStopName", "distanceM", "CampusMarker", "isApproaching", "MapScreen", "navController", "Landroidx/navigation/NavController;", "routeId", "viewModel", "Lcom/routex/app/maps/presentation/MapViewModel;", "StatusChip", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "tint", "StatusChip-mxwnekA", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;J)V", "app_debug"})
public final class MapScreenKt {
    private static final long POLYLINE_COMPLETED = 0L;
    private static final long POLYLINE_REMAINING = 0L;
    private static final long STOP_CIRCLE_COLOR = 0L;
    private static final long GEOFENCE_COLOR = 0L;
    private static final long CAMPUS_FILL_COLOR = 0L;
    private static final long CAMPUS_STROKE_COLOR = 0L;
    private static final long APPROACHING_BG = 0L;
    
    /**
     * Default map centre — KLS Gogte Institute of Technology, Belagavi.
     */
    @org.jetbrains.annotations.NotNull()
    private static final com.google.android.gms.maps.model.LatLng DEFAULT_CAMPUS = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.google.android.gms.maps.model.LatLng CAMPUS_LATLNG = null;
    
    /**
     * TwoWayConverter so we can use Animatable<Double> for lat/lng.
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.compose.animation.core.TwoWayConverter<java.lang.Double, androidx.compose.animation.core.AnimationVector1D> DoubleVectorConverter = null;
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void MapScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.lang.String routeId, @org.jetbrains.annotations.NotNull()
    com.routex.app.maps.presentation.MapViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CampusMarker(boolean isApproaching) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ArrivingAtCollegeBanner(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BusMarker(com.google.android.gms.maps.model.LatLng position, boolean isOnline, java.lang.String busNumber, float heading) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BusStatusCard(com.routex.app.maps.domain.model.BusLocationUpdate update, java.lang.String nextStopName, float distanceM, androidx.compose.ui.Modifier modifier) {
    }
}