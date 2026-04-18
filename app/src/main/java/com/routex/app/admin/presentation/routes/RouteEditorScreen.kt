package com.routex.app.admin.presentation.routes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState

// Default camera center (Pune, India — adjust to your campus)
private val DEFAULT_CENTER = LatLng(18.5204, 73.8567)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteEditorScreen(
    navController: NavController,
    viewModel: RouteEditorViewModel = hiltViewModel(),
) {
    val stops        by viewModel.stops.collectAsState()
    val editingStop  by viewModel.editingStop.collectAsState()
    val saveState    by viewModel.saveState.collectAsState()
    val showForm     by viewModel.showFormPanel.collectAsState()
    val routeNumber  by viewModel.routeNumber.collectAsState()
    val routeName    by viewModel.routeName.collectAsState()
    val busNumber    by viewModel.busNumber.collectAsState()
    val driverName   by viewModel.driverName.collectAsState()
    val scheduleTime by viewModel.scheduleTime.collectAsState()

    val snackbarState = remember { SnackbarHostState() }
    val cameraState   = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(DEFAULT_CENTER, 13f)
    }

    LaunchedEffect(saveState) {
        when (val s = saveState) {
            is UiState.Success -> {
                snackbarState.showSnackbar("Route saved!")
                navController.popBackStack()
            }
            is UiState.Error -> {
                snackbarState.showSnackbar(s.message)
                viewModel.resetSaveState()
            }
            else -> Unit
        }
    }

    // Auto-pan to last tapped stop
    LaunchedEffect(stops.size) {
        val last = stops.lastOrNull() ?: return@LaunchedEffect
        cameraState.animate(CameraUpdateFactory.newLatLng(last.latLng))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Route Editor") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = viewModel::undoLastStop) {
                        Icon(Icons.Rounded.Undo, "Undo last stop")
                    }
                    IconButton(
                        onClick = { viewModel.clearAllStops() },
                        enabled = stops.isNotEmpty(),
                    ) {
                        Icon(Icons.Rounded.Delete, "Clear all stops",
                            tint = if (stops.isNotEmpty()) MaterialTheme.colorScheme.error
                                   else MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    IconButton(onClick = viewModel::toggleFormPanel) {
                        Icon(Icons.Rounded.EditNote, "Edit details")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            // ── Google Map ─────────────────────────────────────────────────────
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraState,
                properties = MapProperties(isMyLocationEnabled = false),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    compassEnabled = true,
                ),
                onMapClick = viewModel::onMapTapped,
            ) {
                // Draw polyline connecting all stops
                if (stops.size >= 2) {
                    Polyline(
                        points = stops.map { it.latLng },
                        color  = Color(0xFF3A86FF),
                        width  = 6f,
                    )
                }

                // Draw numbered markers for each stop
                stops.forEachIndexed { idx, stop ->
                    Marker(
                        state   = MarkerState(position = stop.latLng),
                        title   = stop.name,
                        snippet = stop.arrivalTime.ifBlank { "Stop ${idx + 1}" },
                        onClick = { viewModel.startEditStop(stop); false },
                    )
                }
            }

            // ── Tap hint overlay ───────────────────────────────────────────────
            if (stops.isEmpty()) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f),
                    tonalElevation = 2.dp,
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Icon(Icons.Rounded.Info, null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer)
                        Text(
                            text = "Tap on the map to add stops",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            }

            // ── Stop count badge ───────────────────────────────────────────────
            if (stops.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                ) {
                    Text(
                        text = "${stops.size} stop${if (stops.size != 1) "s" else ""}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                }
            }

            // ── Save FAB ───────────────────────────────────────────────────────
            FloatingActionButton(
                onClick = { viewModel.saveRoute { navController.popBackStack() } },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                if (saveState is UiState.Loading) {
                    androidx.compose.material3.CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                    )
                } else {
                    Icon(Icons.Rounded.Check, "Save route",
                        tint = MaterialTheme.colorScheme.onPrimary)
                }
            }

            // ── Route details panel ────────────────────────────────────────────
            AnimatedVisibility(
                visible = showForm,
                modifier = Modifier.align(Alignment.BottomCenter),
                enter  = slideInVertically { it },
                exit   = slideOutVertically { it },
            ) {
                RouteDetailsPanel(
                    routeNumber  = routeNumber,
                    routeName    = routeName,
                    busNumber    = busNumber,
                    driverName   = driverName,
                    scheduleTime = scheduleTime,
                    stopsCount   = stops.size,
                    onClose      = viewModel::toggleFormPanel,
                    onRouteNumberChange  = viewModel::onRouteNumberChange,
                    onRouteNameChange    = viewModel::onRouteNameChange,
                    onBusNumberChange    = viewModel::onBusNumberChange,
                    onDriverNameChange   = viewModel::onDriverNameChange,
                    onScheduleTimeChange = viewModel::onScheduleTimeChange,
                )
            }
        }
    }

    // ── Rename stop dialog ─────────────────────────────────────────────────────
    editingStop?.let { stop ->
        RenameStopDialog(
            stop     = stop,
            onDismiss = viewModel::cancelEditStop,
            onConfirm = { name, time -> viewModel.confirmRenameStop(stop.id, name, time) },
        )
    }
}

// ── Route Details Panel ────────────────────────────────────────────────────────

@Composable
private fun RouteDetailsPanel(
    routeNumber: String, routeName: String, busNumber: String,
    driverName: String, scheduleTime: String, stopsCount: Int,
    onClose: () -> Unit,
    onRouteNumberChange: (String) -> Unit, onRouteNameChange: (String) -> Unit,
    onBusNumberChange: (String) -> Unit, onDriverNameChange: (String) -> Unit,
    onScheduleTimeChange: (String) -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge.copy(
            bottomStart = androidx.compose.foundation.shape.ZeroCornerSize,
            bottomEnd   = androidx.compose.foundation.shape.ZeroCornerSize,
        ),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Route Details ($stopsCount stops)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                IconButton(onClick = onClose) {
                    Icon(Icons.Rounded.Close, "Close panel")
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = routeNumber, onValueChange = onRouteNumberChange,
                    label = { Text("Route No. *") }, singleLine = true,
                    modifier = Modifier.weight(1f),
                )
                OutlinedTextField(
                    value = busNumber, onValueChange = onBusNumberChange,
                    label = { Text("Bus No.") }, singleLine = true,
                    modifier = Modifier.weight(1f),
                )
            }
            OutlinedTextField(
                value = routeName, onValueChange = onRouteNameChange,
                label = { Text("Route Name *") }, singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = driverName, onValueChange = onDriverNameChange,
                label = { Text("Driver Name") }, singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = scheduleTime, onValueChange = onScheduleTimeChange,
                label = { Text("Schedule (e.g. 8:00 AM)") }, singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

// ── Rename Stop Dialog ─────────────────────────────────────────────────────────

@Composable
private fun RenameStopDialog(
    stop: MapStop,
    onDismiss: () -> Unit,
    onConfirm: (name: String, arrivalTime: String) -> Unit,
) {
    var name by remember(stop.id) { mutableStateOf(stop.name) }
    var time by remember(stop.id) { mutableStateOf(stop.arrivalTime) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title   = { Text("Name Stop ${stop.sequence}") },
        text    = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    label = { Text("Stop Name *") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = time, onValueChange = { time = it },
                    label = { Text("Arrival Time (optional)") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = "📍 ${stop.latLng.latitude.let { "%.4f".format(it) }}, " +
                           "${stop.latLng.longitude.let { "%.4f".format(it) }}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { if (name.isNotBlank()) onConfirm(name, time) },
                enabled = name.isNotBlank(),
            ) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
