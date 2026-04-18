package com.routex.app.eta.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Straight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.utils.UiState
import com.routex.app.eta.domain.model.BusStatus
import com.routex.app.eta.domain.model.EtaResult
import com.routex.app.student.domain.model.BusStop
import com.routex.app.student.domain.model.Route
import com.routex.app.student.domain.usecase.MissLikelihood
import com.routex.app.student.domain.usecase.MissedBusPrediction
import kotlinx.coroutines.delay
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EtaScreen(
    navController: NavController,
    viewModel: EtaViewModel = hiltViewModel(),
) {
    val routesState   by viewModel.routesState.collectAsState()
    val selectedRoute by viewModel.selectedRoute.collectAsState()
    val selectedStop  by viewModel.selectedStop.collectAsState()
    val etaState      by viewModel.etaState.collectAsState()
    val prediction    by viewModel.prediction.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ETA Tracker") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // ── Route picker (only when "all" mode) ──────────────────────────
            if ((routesState as? UiState.Success)?.data?.size?.let { it > 1 } == true) {
                item {
                    RoutePicker(
                        routes        = (routesState as UiState.Success).data,
                        selectedRoute = selectedRoute,
                        onSelect      = viewModel::onRouteSelected,
                    )
                }
            }

            // ── Stop picker ───────────────────────────────────────────────────
            val stops = selectedRoute?.stops.orEmpty()
            if (stops.isNotEmpty()) {
                item {
                    StopPicker(
                        stops        = stops,
                        selectedStop = selectedStop,
                        onSelect     = { stop ->
                            selectedRoute?.let { viewModel.onStopSelected(it.id, stop) }
                        },
                    )
                }
            }

            // ── Main ETA card ─────────────────────────────────────────────────
            item {
                when (val state = etaState) {
                    is UiState.Loading -> LoadingScreen()
                    is UiState.Error   -> ErrorScreen(state.message)
                    is UiState.Success -> EtaResultCard(eta = state.data)
                    is UiState.Idle    -> {
                        Text(
                            text = if (selectedRoute == null) "Select a route to start tracking."
                                   else "Select your boarding stop.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                        )
                    }
                }
            }

            // ── Missed-bus prediction card ─────────────────────────────────────
            if (prediction != null) {
                item { MissedBusPredictionCard(prediction = prediction!!) }
            }

            // ── Stop timeline ─────────────────────────────────────────────────
            val successEta = (etaState as? UiState.Success)?.data
            if (selectedRoute != null && successEta != null) {
                item {
                    Text(
                        text = "Route Timeline",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                items(
                    items = selectedRoute!!.stops.sortedBy { it.sequence },
                    key   = { it.id },
                ) { stop ->
                    StopTimelineItem(
                        stop         = stop,
                        isSelected   = stop.id == selectedStop?.id,
                        etaMinutes   = if (stop.id == selectedStop?.id) successEta.etaMinutes else null,
                        busStatus    = successEta.status,
                        totalStops   = selectedRoute!!.stops.size,
                    )
                }
            }
        }
    }
}

// ── Route Picker ──────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoutePicker(
    routes: List<Route>,
    selectedRoute: Route?,
    onSelect: (Route) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            value = selectedRoute?.routeName?.ifEmpty { "Route ${selectedRoute.routeNumber}" }
                ?: "Select route",
            onValueChange = {},
            readOnly = true,
            label = { Text("Route") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            routes.forEach { route ->
                DropdownMenuItem(
                    text = {
                        Text(route.routeName.ifEmpty { "Route ${route.routeNumber}" })
                    },
                    onClick = {
                        onSelect(route)
                        expanded = false
                    },
                )
            }
        }
    }
}

// ── Stop Picker ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StopPicker(
    stops: List<BusStop>,
    selectedStop: BusStop?,
    onSelect: (BusStop) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            value = selectedStop?.name ?: "Select boarding stop",
            onValueChange = {},
            readOnly = true,
            label = { Text("Boarding stop") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            stops.sortedBy { it.sequence }.forEach { stop ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(stop.name, style = MaterialTheme.typography.bodyMedium)
                            if (stop.arrivalTime.isNotBlank()) {
                                Text(
                                    text = "Scheduled: ${stop.arrivalTime}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    },
                    onClick = {
                        onSelect(stop)
                        expanded = false
                    },
                )
            }
        }
    }
}

// ── Main ETA Result Card ──────────────────────────────────────────────────────

@Composable
private fun EtaResultCard(eta: EtaResult) {
    // Countdown tick: interpolate between Firebase updates locally
    var displayedEta by remember(eta.etaMinutes) { mutableFloatStateOf(eta.etaMinutes) }
    val decrementPerSecond = 1f / 60f

    LaunchedEffect(eta.timestamp) {
        // tick down every second until next Firebase update arrives
        while (displayedEta > 0f) {
            delay(1_000L)
            displayedEta = (displayedEta - decrementPerSecond).coerceAtLeast(0f)
        }
    }

    val statusColor by animateColorAsState(
        targetValue = Color(eta.status.colorHex),
        animationSpec = tween(500),
        label = "statusColor",
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ── Status chip ───────────────────────────────────────────────────
            SuggestionChip(
                onClick = {},
                label = {
                    Text(
                        text = eta.status.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = statusColor,
                ),
                border = null,
            )

            Spacer(Modifier.height(16.dp))

            // ── Circular ETA arc + countdown ──────────────────────────────────
            EtaArc(etaMinutes = displayedEta, statusColor = statusColor)

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Until ${eta.stopName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(20.dp))

            // ── Metrics row ───────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                MetricItem(
                    icon  = Icons.Rounded.Straight,
                    label = "Distance",
                    value = if (eta.distanceKm < 1.0) {
                        "${(eta.distanceKm * 1000).toInt()} m"
                    } else {
                        "${"%.1f".format(eta.distanceKm)} km"
                    },
                )
                MetricItem(
                    icon  = Icons.Rounded.Speed,
                    label = "Speed",
                    value = "${eta.rawSpeedKmh.toInt()} km/h",
                )
                MetricItem(
                    icon  = Icons.Rounded.DirectionsBus,
                    label = "Traffic",
                    value = "${((eta.trafficFactor - 1f) * 100).toInt()}% slower",
                )
            }

            if (eta.status.description.isNotBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = eta.status.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

// ── Circular ETA arc ──────────────────────────────────────────────────────────

@Composable
private fun EtaArc(etaMinutes: Float, statusColor: Color) {
    val maxMinutes = 30f
    val sweepAngle = (1f - min(etaMinutes / maxMinutes, 1f)) * 270f

    val pulseAlpha by rememberInfiniteTransition(label = "pulse").animateFloat(
        initialValue = 0.6f,
        targetValue  = 1f,
        animationSpec = infiniteRepeatable(
            tween(900, easing = LinearEasing),
            RepeatMode.Reverse,
        ),
        label = "pulseAlpha",
    )

    val surfaceColor = MaterialTheme.colorScheme.surface
    val trackColor   = MaterialTheme.colorScheme.outlineVariant

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(160.dp)) {
            val stroke = Stroke(width = 18f, cap = StrokeCap.Round)
            // Track (background arc)
            drawArc(
                color      = trackColor,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter  = false,
                style      = stroke,
            )
            // Progress arc
            if (sweepAngle > 0f) {
                drawArc(
                    color      = statusColor.copy(alpha = pulseAlpha),
                    startAngle = 135f,
                    sweepAngle = sweepAngle,
                    useCenter  = false,
                    style      = stroke,
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedContent(
                targetState = etaMinutes.toInt(),
                transitionSpec = {
                    (slideInVertically { -it } + fadeIn()) togetherWith
                        (slideOutVertically { it } + fadeOut())
                },
                label = "etaCountdown",
            ) { mins ->
                Text(
                    text = if (etaMinutes < 1f) "<1" else "$mins",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = statusColor,
                )
            }
            Text(
                text = "min",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ── Metric item ───────────────────────────────────────────────────────────────

@Composable
private fun MetricItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp),
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(value, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
        Text(label, style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// ── Missed-bus prediction card ────────────────────────────────────────────────

@Composable
private fun MissedBusPredictionCard(prediction: MissedBusPrediction) {
    val color by animateColorAsState(
        targetValue = Color(prediction.likelihood.colorHex),
        label = "predColor",
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f),
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = prediction.likelihood.emoji,
                style = MaterialTheme.typography.titleLarge,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = prediction.likelihood.label,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = color,
                )
                Text(
                    text = prediction.reason,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = prediction.suggestion,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (prediction.urgentAction != null) {
                    Spacer(Modifier.height(8.dp))
                    SuggestionChip(
                        onClick = {},
                        label = {
                            Text(
                                prediction.urgentAction,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = color,
                        ),
                        border = null,
                    )
                }
            }
        }
    }
}

// ── Stop timeline item ────────────────────────────────────────────────────────

@Composable
private fun StopTimelineItem(
    stop: BusStop,
    isSelected: Boolean,
    etaMinutes: Float?,
    busStatus: BusStatus,
    totalStops: Int,
) {
    val isArrived = isSelected && etaMinutes != null && etaMinutes < 0.5f
    val isLast    = stop.sequence == totalStops

    val dotColor by animateColorAsState(
        targetValue = when {
            isArrived  -> Color(0xFF2ECC71)
            isSelected -> Color(busStatus.colorHex)
            else       -> MaterialTheme.colorScheme.outlineVariant
        },
        label = "dotColor",
    )

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth(),
    ) {
        // ── Timeline column ───────────────────────────────────────────────────
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(if (isSelected) 20.dp else 14.dp)
                    .clip(CircleShape)
                    .background(dotColor),
                contentAlignment = Alignment.Center,
            ) {
                if (isArrived) {
                    Icon(
                        Icons.Rounded.CheckCircle,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp),
                    )
                }
                if (isSelected && !isArrived) {
                    Icon(
                        Icons.Rounded.DirectionsBus,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(32.dp)
                        .background(MaterialTheme.colorScheme.outlineVariant),
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        // ── Stop info ─────────────────────────────────────────────────────────
        Column(modifier = Modifier.weight(1f).padding(bottom = if (isLast) 0.dp else 4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stop.name,
                    style = if (isSelected) MaterialTheme.typography.bodyMedium
                            else MaterialTheme.typography.bodySmall,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (isSelected && etaMinutes != null) {
                    Text(
                        text = if (etaMinutes < 0.5f) "Now"
                               else "${etaMinutes.toInt()} min",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = dotColor,
                    )
                } else if (stop.arrivalTime.isNotBlank()) {
                    Text(
                        text = stop.arrivalTime,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}
