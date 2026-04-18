package com.routex.app.maps.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.GpsFixed
import androidx.compose.material.icons.rounded.GpsOff
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.NotificationImportant
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.utils.UiState
import com.routex.app.maps.data.geofencing.GEOFENCE_RADIUS_M
import com.routex.app.maps.domain.model.BusLocationUpdate
import com.routex.app.maps.domain.model.GeofenceEvent
import com.routex.app.maps.domain.model.GeofenceTransition
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

// ── Brand colours for polylines (can't read MaterialTheme inside GoogleMap lambda) ──
private val POLYLINE_COMPLETED  = Color(0xFF9E9E9E)   // grey  – travelled
private val POLYLINE_REMAINING  = Color(0xFF0F4C75)   // brand blue – ahead
private val STOP_CIRCLE_COLOR   = Color(0x440F4C75)   // translucent brand blue
private val GEOFENCE_COLOR      = Color(0x22FF6B35)   // translucent orange
private val CAMPUS_FILL_COLOR   = Color(0x33FFD700)   // translucent gold
private val CAMPUS_STROKE_COLOR = Color(0xCCFFD700)   // gold border
private val APPROACHING_BG      = Color(0xEEFFD700)   // banner background

/** Default map centre — KLS Gogte Institute of Technology, Belagavi. */
private val DEFAULT_CAMPUS = LatLng(CollegeHub.LATITUDE, CollegeHub.LONGITUDE)
private val CAMPUS_LATLNG  = LatLng(CollegeHub.LATITUDE, CollegeHub.LONGITUDE)

/** TwoWayConverter so we can use Animatable<Double> for lat/lng. */
private val DoubleVectorConverter = TwoWayConverter<Double, androidx.compose.animation.core.AnimationVector1D>(
    convertToVector = { androidx.compose.animation.core.AnimationVector1D(it.toFloat()) },
    convertFromVector = { it.value.toDouble() },
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    navController: NavController,
    routeId: String,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val routeState          by viewModel.routeState.collectAsState()
    val busLocation         by viewModel.busLocation.collectAsState()
    val allBusLocations     by viewModel.allBusLocations.collectAsState()
    val userLocation        by viewModel.userLocation.collectAsState()
    val routeProgress       by viewModel.routeProgress.collectAsState()
    val followBus           by viewModel.followBus.collectAsState()
    val geofenceEvent       by viewModel.geofenceEvents.collectAsState()
    val isArrivingAtCampus  by viewModel.isArrivingAtCampus.collectAsState()
    val approachingCampus   by viewModel.approachingCampusBuses.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(DEFAULT_CAMPUS, 15f)
    }
    val scope = rememberCoroutineScope()

    // ── Smooth animated bus position (separate Animatables for lat + lng) ──────
    val animLat = remember { Animatable(DEFAULT_CAMPUS.latitude, DoubleVectorConverter) }
    val animLng = remember { Animatable(DEFAULT_CAMPUS.longitude, DoubleVectorConverter) }

    val animatedBusLatLng = LatLng(animLat.value, animLng.value)

    LaunchedEffect(busLocation?.location) {
        val loc = busLocation?.location ?: return@LaunchedEffect
        if (loc.latitude == 0.0 && loc.longitude == 0.0) return@LaunchedEffect
        // Animate smoothly — spring gives a natural deceleration
        val spec = spring<Double>(stiffness = Spring.StiffnessLow)
        launch { animLat.animateTo(loc.latitude, spec) }
        launch { animLng.animateTo(loc.longitude, spec) }
    }

    // ── Camera: auto-follow bus ───────────────────────────────────────────────
    LaunchedEffect(animatedBusLatLng, followBus) {
        if (followBus && busLocation?.isOnline == true) {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder()
                        .target(animatedBusLatLng)
                        .zoom(16f)
                        .bearing(busLocation?.heading ?: 0f)
                        .tilt(30f)
                        .build(),
                ),
                durationMs = 1_200,
            )
        }
    }

    // ── Geofence alert snackbar ───────────────────────────────────────────────
    LaunchedEffect(geofenceEvent) {
        val evt = geofenceEvent ?: return@LaunchedEffect
        if (evt.transition == GeofenceTransition.ENTER) {
            val message = if (evt.isCampusEntry) {
                "🎓  Arriving at KLS Gogte Institute of Technology!"
            } else {
                val stopName = evt.stop.name.ifBlank { "a bus stop" }
                "🚌  Bus is approaching $stopName (${evt.distanceMeters.roundToInt()} m)"
            }
            snackbarHostState.showSnackbar(
                message  = message,
                duration = if (evt.isCampusEntry) SnackbarDuration.Long else SnackbarDuration.Short,
            )
        }
    }

    val title = when {
        viewModel.isAllRoutes -> "Live Map – All Buses"
        routeState is UiState.Success && (routeState as UiState.Success).data != null ->
            (routeState as UiState.Success).data!!.routeName.ifEmpty { "Route $routeId" }
        else -> "Live Tracking"
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when (routeState) {
                is UiState.Loading -> LoadingScreen("Loading route…")
                is UiState.Error   -> ErrorScreen(
                    message = (routeState as UiState.Error).message.ifBlank {
                        "Failed to load the route. Please check your connection."
                    },
                    onRetry = { viewModel.retryLoad() },
                )
                else -> {
                    val route = (routeState as? UiState.Success)?.data

                    // ── Google Map ────────────────────────────────────────────
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(
                            mapType = MapType.NORMAL,
                            isMyLocationEnabled = false,    // we render our own dot
                        ),
                        uiSettings = MapUiSettings(
                            zoomControlsEnabled     = false,
                            myLocationButtonEnabled = false,
                            compassEnabled          = true,
                        ),
                        onMapClick = { viewModel.disableFollowBus() },
                    ) {
                        // ── Completed polyline (grey) ────────────────────────
                        if (routeProgress.completedPoints.size >= 2) {
                            Polyline(
                                points = routeProgress.completedPoints,
                                color  = POLYLINE_COMPLETED,
                                width  = 10f,
                                zIndex = 1f,
                            )
                        }

                        // ── Remaining polyline (brand blue) ──────────────────
                        if (routeProgress.remainingPoints.size >= 2) {
                            Polyline(
                                points = routeProgress.remainingPoints,
                                color  = POLYLINE_REMAINING,
                                width  = 12f,
                                zIndex = 2f,
                            )
                        }

                        // ── Bus stop markers + geofence circles ───────────────
                        route?.stops?.sortedBy { it.sequence }?.forEach { stop ->
                            if (stop.latitude == 0.0 && stop.longitude == 0.0) return@forEach
                            val pos = LatLng(stop.latitude, stop.longitude)

                            Circle(
                                center      = pos,
                                radius      = GEOFENCE_RADIUS_M.toDouble(),
                                fillColor   = GEOFENCE_COLOR,
                                strokeColor = Color(0x66FF6B35),
                                strokeWidth = 2f,
                            )
                            Marker(
                                state   = MarkerState(position = pos),
                                title   = stop.name.ifBlank { "Stop ${stop.sequence}" },
                                snippet = stop.arrivalTime.ifBlank { null },
                                icon    = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE,
                                ),
                            )
                        }

                        // ── Animated bus marker (single route) ───────────────
                        if (!viewModel.isAllRoutes && busLocation != null) {
                            BusMarker(
                                position  = animatedBusLatLng,
                                isOnline  = busLocation!!.isOnline,
                                busNumber = busLocation!!.busNumber,
                                heading   = busLocation!!.heading,
                            )
                        }

                        // ── All buses (admin / all-routes view) ──────────────
                        allBusLocations.forEach { bus ->
                            if (bus.location.latitude == 0.0) return@forEach
                            BusMarker(
                                position  = LatLng(bus.location.latitude, bus.location.longitude),
                                isOnline  = bus.isOnline,
                                busNumber = bus.busNumber,
                                heading   = bus.heading,
                            )
                        }

                        // ── KLS GIT campus marker (gold, always visible) ────
                        CampusMarker(isApproaching = isArrivingAtCampus)

                        // ── User location dot (blue) ─────────────────────────
                        userLocation?.let { userPos ->
                            Circle(
                                center      = userPos,
                                radius      = 8.0,
                                fillColor   = Color(0xFF2196F3),
                                strokeColor = Color.White,
                                strokeWidth = 3f,
                                zIndex      = 5f,
                            )
                        }
                    }

                    // ── FAB column ─────────────────────────────────────────────
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.End,
                    ) {
                        // Re-centre on user location
                        SmallFloatingActionButton(
                            onClick = {
                                userLocation?.let { pos ->
                                    scope.launch {
                                        cameraPositionState.animate(
                                            CameraUpdateFactory.newLatLngZoom(pos, 16f),
                                        )
                                    }
                                }
                            },
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ) {
                            Icon(Icons.Rounded.MyLocation, contentDescription = "My location")
                        }

                        // Follow / unfollow bus
                        FloatingActionButton(
                            onClick = { viewModel.toggleFollowBus() },
                            containerColor = if (followBus)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant,
                        ) {
                            Icon(
                                imageVector = if (followBus)
                                    Icons.Rounded.GpsFixed
                                else
                                    Icons.Rounded.GpsOff,
                                contentDescription = "Follow bus",
                                tint = if (followBus) Color.White
                                       else MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }

                    // ── "Arriving at College" banner ──────────────────────────
                    AnimatedVisibility(
                        visible  = isArrivingAtCampus,
                        modifier = Modifier.align(Alignment.TopCenter),
                        enter    = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                        exit     = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
                    ) {
                        ArrivingAtCollegeBanner(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    }

                    // ── Bottom bus status card ─────────────────────────────────
                    AnimatedVisibility(
                        visible = !viewModel.isAllRoutes && busLocation != null,
                        modifier = Modifier.align(Alignment.BottomStart),
                        enter  = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                        exit   = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                    ) {
                        busLocation?.let { loc ->
                            BusStatusCard(
                                update         = loc,
                                nextStopName   = route?.stops
                                    ?.sortedBy { it.sequence }
                                    ?.getOrNull(routeProgress.nextStopIndex)
                                    ?.name,
                                distanceM      = routeProgress.distanceToNextStopM,
                                modifier       = Modifier.padding(
                                    start = 16.dp, end = 80.dp, bottom = 16.dp,
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}

// ── Campus marker + circle ────────────────────────────────────────────────────

@Composable
private fun CampusMarker(isApproaching: Boolean) {
    // Geofence circle — gold fill, wider than stop circles
    Circle(
        center      = CAMPUS_LATLNG,
        radius      = CollegeHub.GEOFENCE_RADIUS_M.toDouble(),
        fillColor   = CAMPUS_FILL_COLOR,
        strokeColor = CAMPUS_STROKE_COLOR,
        strokeWidth = if (isApproaching) 4f else 2f,
        zIndex      = 3f,
    )
    // Outer "approaching" ring — shown only when a bus is near
    if (isApproaching) {
        Circle(
            center      = CAMPUS_LATLNG,
            radius      = CollegeHub.APPROACHING_RADIUS_M.toDouble(),
            fillColor   = Color(0x11FFD700),
            strokeColor = Color(0x55FFD700),
            strokeWidth = 2f,
            zIndex      = 2f,
        )
    }
    // Landmark marker — gold hue, always on top
    Marker(
        state   = MarkerState(position = CAMPUS_LATLNG),
        title   = CollegeHub.SHORT_NAME,
        snippet = CollegeHub.NAME,
        icon    = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW),
        zIndex  = 20f,
        alpha   = 1f,
    )
}

// ── "Arriving at College" banner ──────────────────────────────────────────────

@Composable
private fun ArrivingAtCollegeBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier       = modifier,
        shape          = RoundedCornerShape(12.dp),
        color          = APPROACHING_BG,
        tonalElevation = 4.dp,
        shadowElevation = 6.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(text = "🎓", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
            Column {
                Text(
                    text = "Arriving at College",
                    style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037),
                )
                Text(
                    text  = CollegeHub.NAME,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    color = Color(0xFF6D4C41),
                )
            }
        }
    }
}

// ── Bus marker composable ─────────────────────────────────────────────────────

@Composable
private fun BusMarker(
    position: LatLng,
    isOnline: Boolean,
    busNumber: String,
    heading: Float,
) {
    if (position.latitude == 0.0 && position.longitude == 0.0) return
    Marker(
        state   = MarkerState(position = position),
        title   = "Bus $busNumber",
        snippet = if (isOnline) "Online" else "Offline",
        icon    = BitmapDescriptorFactory.defaultMarker(
            if (isOnline) BitmapDescriptorFactory.HUE_GREEN
            else BitmapDescriptorFactory.HUE_RED,
        ),
        rotation = heading,
        flat     = true,        // marker rotates with the map
        zIndex   = 10f,
    )
}

// ── Bus status bottom card ────────────────────────────────────────────────────

@Composable
private fun BusStatusCard(
    update: BusLocationUpdate,
    nextStopName: String?,
    distanceM: Float,
    modifier: Modifier = Modifier,
) {
    val timeFormat = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
        tonalElevation = 8.dp,
        shadowElevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                if (update.isOnline) Color(0xFF2ECC71) else Color(0xFFE74C3C),
                            ),
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Bus ${update.busNumber}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = "Updated: ${timeFormat.format(Date(update.lastUpdated))}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                StatusChip(
                    icon  = Icons.Rounded.Speed,
                    label = "${update.speed.roundToInt()} km/h",
                )
                if (nextStopName != null) {
                    StatusChip(
                        icon  = Icons.Rounded.DirectionsBus,
                        label = "Next: $nextStopName (${distanceM.roundToInt()} m)",
                    )
                }
                if (!update.isOnline) {
                    StatusChip(
                        icon  = Icons.Rounded.NotificationImportant,
                        label = "Bus offline",
                        tint  = Color(0xFFE74C3C),
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color = MaterialTheme.colorScheme.primary,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = tint,
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text  = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
