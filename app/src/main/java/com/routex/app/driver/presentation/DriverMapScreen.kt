package com.routex.app.driver.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.ui.components.TripStatusChip
import com.routex.app.core.utils.UiState
import com.routex.app.trips.domain.model.TripStatus
import kotlinx.coroutines.launch

// ── Color palette ─────────────────────────────────────────────────────────────

private val RoutePolylineColor   = Color(0xFF1565C0)
private val CompletedPolylineColor = Color(0xFF2E7D32)
private val DriverMarkerColor    = Color(0xFF0288D1)
private val NextStopColor        = Color(0xFFFF6F00)
private val StopCircleFill       = Color(0x3300BCD4)
private val CampusColor          = Color(0xFFFFD600)

private val CAMPUS_LATLNG = LatLng(CollegeHub.LATITUDE, CollegeHub.LONGITUDE)

@Composable
fun DriverMapScreen(
    onBack: () -> Unit,
    viewModel: DriverMapViewModel = hiltViewModel(),
) {
    val routeState     by viewModel.routeState.collectAsState()
    val driverLocation by viewModel.driverLocation.collectAsState()
    val nextStop       by viewModel.nextStop.collectAsState()
    val nextStopDist   by viewModel.nextStopDistanceM.collectAsState()
    val activeTrip     by viewModel.activeTrip.collectAsState()
    val stops          by viewModel.stops.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(CAMPUS_LATLNG, 14f)
    }
    val scope = rememberCoroutineScope()
    var autoFollow by remember { mutableStateOf(true) }

    // Auto-follow driver when position updates
    LaunchedEffect(driverLocation) {
        if (autoFollow && driverLocation != null) {
            scope.launch {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(driverLocation!!, 16f),
                    durationMs = 800,
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = routeState) {
            is UiState.Loading -> LoadingScreen("Loading route…")
            is UiState.Error   -> ErrorScreen(
                message = state.message.ifBlank { "Failed to load route. Check your connection." },
                onRetry = { viewModel.retryLoad() },
            )

            is UiState.Success, is UiState.Idle -> {
                val route = (routeState as? UiState.Success)?.data

                // ── Map ───────────────────────────────────────────────────────
                GoogleMap(
                    modifier              = Modifier.fillMaxSize(),
                    cameraPositionState   = cameraPositionState,
                    properties            = MapProperties(isMyLocationEnabled = false),
                    uiSettings            = MapUiSettings(
                        myLocationButtonEnabled = false,
                        zoomControlsEnabled     = false,
                    ),
                    onMapClick            = { autoFollow = false },
                ) {
                    // Route polyline
                    if (stops.size >= 2) {
                        val polylinePoints = stops.sortedBy { it.sequence }
                            .map { LatLng(it.latitude, it.longitude) }
                        Polyline(
                            points    = polylinePoints,
                            color     = RoutePolylineColor,
                            width     = 8f,
                            geodesic  = true,
                        )
                    }

                    // Stop markers + circles
                    stops.forEach { stop ->
                        val pos        = LatLng(stop.latitude, stop.longitude)
                        val isNext     = stop.id == nextStop?.id
                        val hue        = if (isNext) BitmapDescriptorFactory.HUE_ORANGE
                                         else BitmapDescriptorFactory.HUE_CYAN
                        Marker(
                            state   = MarkerState(position = pos),
                            title   = stop.name,
                            snippet = if (isNext) "Next stop" else stop.arrivalTime,
                            icon    = BitmapDescriptorFactory.defaultMarker(hue),
                        )
                        Circle(
                            center      = pos,
                            radius      = 60.0,
                            fillColor   = if (isNext) Color(0x33FF6F00) else StopCircleFill,
                            strokeColor = if (isNext) NextStopColor else Color(0xFF00BCD4),
                            strokeWidth = 2f,
                        )
                    }

                    // Driver / Bus marker
                    driverLocation?.let { pos ->
                        Marker(
                            state   = MarkerState(position = pos),
                            title   = "Your Location",
                            snippet = activeTrip?.busNumber ?: "Bus",
                            icon    = BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE,
                            ),
                        )
                    }

                    // Campus / final destination marker
                    Marker(
                        state   = MarkerState(position = CAMPUS_LATLNG),
                        title   = CollegeHub.NAME,
                        snippet = "Final destination",
                        icon    = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_YELLOW,
                        ),
                    )
                    Circle(
                        center      = CAMPUS_LATLNG,
                        radius      = CollegeHub.GEOFENCE_RADIUS_M.toDouble(),
                        fillColor   = Color(0x33FFD600),
                        strokeColor = CampusColor,
                        strokeWidth = 3f,
                    )
                }

                // ── Top bar ───────────────────────────────────────────────────
                TopBar(
                    routeName  = route?.routeName ?: "Route Map",
                    tripStatus = activeTrip?.status,
                    onBack     = onBack,
                    modifier   = Modifier.align(Alignment.TopStart),
                )

                // ── Re-center FAB ─────────────────────────────────────────────
                IconButton(
                    onClick = {
                        autoFollow = true
                        driverLocation?.let { pos ->
                            scope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(pos, 16f),
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 140.dp)
                        .size(48.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Icon(
                        Icons.Rounded.MyLocation,
                        contentDescription = "Re-center",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }

                // ── Bottom info card ──────────────────────────────────────────
                NextStopCard(
                    nextStopName  = nextStop?.name ?: "—",
                    distanceM     = nextStopDist,
                    tripStatus    = activeTrip?.status,
                    modifier      = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
    }
}

// ── Sub-composables ───────────────────────────────────────────────────────────

@Composable
private fun TopBar(
    routeName: String,
    tripStatus: TripStatus?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier  = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        color     = MaterialTheme.colorScheme.surface.copy(alpha = 0.93f),
        shadowElevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment   = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
            }
            Icon(
                Icons.Rounded.DirectionsBus,
                contentDescription = null,
                tint     = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp),
            )
            Text(
                text       = routeName,
                style      = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier   = Modifier.weight(1f),
            )
            if (tripStatus != null) {
                TripStatusChip(status = tripStatus)
            }
        }
    }
}

@Composable
private fun NextStopCard(
    nextStopName: String,
    distanceM: Float,
    tripStatus: TripStatus?,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier  = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .navigationBarsPadding(),
        shape     = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors    = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment   = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        shape = RoundedCornerShape(10.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Rounded.Place,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text  = "Next Stop",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                AnimatedContent(
                    targetState = nextStopName,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "nextStop",
                ) { name ->
                    Text(
                        text       = name,
                        style      = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                AnimatedContent(
                    targetState = formatDistance(distanceM),
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "distance",
                ) { dist ->
                    Text(
                        text       = dist,
                        style      = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color      = MaterialTheme.colorScheme.primary,
                    )
                }
                Text(
                    text  = "away",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

private fun formatDistance(meters: Float): String = when {
    meters <= 0f    -> "—"
    meters < 1000f  -> "${meters.toInt()} m"
    else            -> "${"%.1f".format(meters / 1000)} km"
}
