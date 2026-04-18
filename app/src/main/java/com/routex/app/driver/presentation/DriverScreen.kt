package com.routex.app.driver.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.core.navigation.Screen
import com.routex.app.core.ui.components.OfflineBanner
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.ui.components.RouteXOutlinedButton
import com.routex.app.core.ui.components.TripStatusChip
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverScreen(
    navController: NavController,
    viewModel: DriverViewModel = hiltViewModel(),
) {
    val user          by viewModel.currentUser.collectAsState()
    val driverRecord  by viewModel.driverRecord.collectAsState()
    val assignedRoute by viewModel.assignedRoute.collectAsState()
    val isTracking    by viewModel.isTracking.collectAsState()
    val activeTrip    by viewModel.activeTrip.collectAsState()
    val busNumber     by viewModel.busNumber.collectAsState()
    val errorMessage  by viewModel.errorMessage.collectAsState()
    val isOffline     by viewModel.isOffline.collectAsState()

    val snackbarState  = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            snackbarState.showSnackbar(errorMessage!!)
            viewModel.clearError()
        }
    }

    // Driver is ready to start only when both bus and route are assigned
    val isAssigned = driverRecord != null &&
        driverRecord!!.assignedBusId.isNotBlank() &&
        assignedRoute != null

    Scaffold(
        modifier    = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarState) },
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Driver Portal",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text  = driverRecord?.name?.ifBlank { user?.displayName ?: "" }
                                ?: (user?.displayName ?: ""),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.signOut {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.DriverDashboard.route) { inclusive = true }
                                }
                            }
                        },
                    ) {
                        Icon(Icons.Rounded.Logout, contentDescription = "Sign out")
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            OfflineBanner(isOffline = isOffline)
            LazyColumn(
                modifier        = Modifier.weight(1f),
                contentPadding  = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // ── Assignment info cards ─────────────────────────────────────
                item {
                    AssignmentInfoSection(
                        driverRecord   = driverRecord,
                        assignedRoute  = assignedRoute,
                        resolvedBusNum = busNumber,
                    )
                }

                // ── Trip control card ─────────────────────────────────────────
                item {
                    TripControlCard(
                        isTracking     = isTracking,
                        activeTrip     = activeTrip,
                        driverRecord   = driverRecord,
                        assignedRoute  = assignedRoute,
                        resolvedBusNum = busNumber,
                        isAssigned     = isAssigned,
                        onStartTrip    = viewModel::startTrip,
                        onEndTrip      = viewModel::endTrip,
                    )
                }

                // ── Navigation map shortcut (active only when trip is running) ─
                item {
                    AnimatedVisibility(visible = isTracking && assignedRoute != null) {
                        RouteXOutlinedButton(
                            text    = "View Route Map",
                            enabled = true,
                            onClick = {
                                navController.navigate(
                                    Screen.DriverMap.withRouteId(assignedRoute!!.id),
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

// ── Assignment Info Section ───────────────────────────────────────────────────

@Composable
private fun AssignmentInfoSection(
    driverRecord: com.routex.app.admin.domain.model.Driver?,
    assignedRoute: com.routex.app.student.domain.model.Route?,
    resolvedBusNum: String,
) {
    if (driverRecord == null) {
        // Not linked yet — show info card
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    Icons.Rounded.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                )
                Column {
                    Text(
                        "No Assignment Found",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        "Contact admin to assign a bus and route to your account.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        return
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Bus card
        ElevatedCard(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    Icons.Rounded.DirectionsBus,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text  = "Assigned Bus",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text  = resolvedBusNum.ifBlank { 
                        if (driverRecord.assignedBusId.isNotBlank()) "Bus ${driverRecord.assignedBusId.takeLast(6)}" else "Not Assigned"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (driverRecord.assignedBusId.isNotBlank())
                        MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.error,
                )
            }
        }

        // Route card
        ElevatedCard(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    Icons.Rounded.Route,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text  = "Assigned Route",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text  = assignedRoute?.routeName?.ifBlank { "Route ${assignedRoute.routeNumber}" }
                        ?: "Not Assigned",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (assignedRoute != null)
                        MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.error,
                    maxLines = 2,
                )
            }
        }
    }
}

// ── Trip control card ─────────────────────────────────────────────────────────

@Composable
private fun TripControlCard(
    isTracking: Boolean,
    activeTrip: Trip?,
    driverRecord: com.routex.app.admin.domain.model.Driver?,
    assignedRoute: com.routex.app.student.domain.model.Route?,
    resolvedBusNum: String,
    isAssigned: Boolean,
    onStartTrip: () -> Unit,
    onEndTrip: () -> Unit,
) {
    val tripStatus = activeTrip?.status ?: TripStatus.NOT_STARTED

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors   = CardDefaults.cardColors(
            containerColor = if (isTracking)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ── Header row ────────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    LiveIndicator(isLive = isTracking)
                    Column {
                        Text(
                            text  = if (isTracking) "Trip Active" else "No Active Trip",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        if (isTracking && assignedRoute != null) {
                            Text(
                                text  = "${assignedRoute.routeName} · ${resolvedBusNum.ifBlank { activeTrip?.busNumber ?: "" }}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        } else if (isAssigned) {
                            Text(
                                text  = "Ready to start. Tap Start Trip.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        } else {
                            Text(
                                text  = "Waiting for admin assignment…",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                            )
                        }
                    }
                }

                // Status chip (only while tracking)
                AnimatedVisibility(
                    visible = isTracking,
                    enter   = fadeIn(),
                    exit    = fadeOut(),
                ) {
                    TripStatusChip(status = tripStatus)
                }
            }

            // ── Active trip details ───────────────────────────────────────────
            if (isTracking && activeTrip != null) {
                Spacer(Modifier.height(12.dp))
                TripInfoRow(trip = activeTrip)
            }

            Spacer(Modifier.height(16.dp))

            AnimatedContent(targetState = isTracking, label = "trip_button") { tracking ->
                if (tracking) {
                    RouteXButton(
                        text     = "End Trip",
                        onClick  = onEndTrip,
                        modifier = Modifier.fillMaxWidth(),
                    )
                } else {
                    RouteXButton(
                        text     = "Start Trip",
                        onClick  = onStartTrip,
                        enabled  = isAssigned,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            if (!isAssigned && !isTracking) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text  = "You need an assigned bus and route to start a trip. Contact your admin.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun TripInfoRow(trip: Trip) {
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text  = "Started",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text  = if (trip.startTime > 0)
                    formatter.format(Date(trip.startTime)) else "–",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text  = "Status",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text  = trip.status.description,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = trip.status.color,
            )
        }
    }
}

// ── Pulsing live indicator ────────────────────────────────────────────────────

@Composable
private fun LiveIndicator(isLive: Boolean) {
    if (isLive) {
        val transition = rememberInfiniteTransition(label = "live")
        val alpha by transition.animateFloat(
            initialValue  = 1f,
            targetValue   = 0.2f,
            animationSpec = infiniteRepeatable(
                animation  = tween(700),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "pulse",
        )
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .alpha(alpha)
                .background(Color(0xFF2ECC71)),
        )
    } else {
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.outlineVariant),
        )
    }
}
