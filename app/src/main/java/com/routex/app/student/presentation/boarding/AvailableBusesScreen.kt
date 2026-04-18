package com.routex.app.student.presentation.boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.routex.app.student.domain.simulator.EtaCalculator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableBusesScreen(
    onNavigateBack: () -> Unit,
    viewModel: AvailableBusesViewModel = hiltViewModel()
) {
    val targetStop by viewModel.targetStop.collectAsState()
    val buses by viewModel.buses.collectAsState()

    val cameraPositionState = rememberCameraPositionState()
    val stopLocation = targetStop?.let { LatLng(it.latitude, it.longitude) }

    if (stopLocation != null && cameraPositionState.position.target.latitude == 0.0) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(stopLocation, 14f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Available Buses") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Map Portion
            Box(modifier = Modifier.weight(0.4f).fillMaxWidth()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    stopLocation?.let {
                        Marker(
                            state = MarkerState(position = it),
                            title = "Boarding Stop: ${targetStop?.name}"
                        )
                    }

                    buses.forEach { busModel ->
                        Marker(
                            state = MarkerState(
                                position = LatLng(
                                    busModel.update.location.latitude,
                                    busModel.update.location.longitude
                                )
                            ),
                            title = busModel.update.busNumber,
                            snippet = "ETA: ${busModel.etaResult.estimationMinutes} min"
                        )
                    }
                }
            }

            // Bottom List Portion
            Surface(
                modifier = Modifier.weight(0.6f).fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            "Next buses available for your stop",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    items(buses, key = { it.update.busNumber }) { model ->
                        BusEtaCard(model)
                    }

                    val passedBuses = buses.filter { it.etaResult.status == EtaCalculator.BusStatus.PASSED }
                    if (passedBuses.isNotEmpty()) {
                        item {
                            MissedBusWarning()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusEtaCard(model: BoardingBusModel) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.DirectionsBus, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Bus ${model.update.busNumber}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text("Status: ${model.etaResult.status.name}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${model.etaResult.estimationMinutes} min",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "%.1f km away".format(model.etaResult.distanceMeters / 1000),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MissedBusWarning() {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Warning, contentDescription = null, tint = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "You missed a bus. Another bus is on the way.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        }
    }
}
