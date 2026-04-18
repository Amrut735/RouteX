package com.routex.app.student.presentation.boarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.utils.UiState
import com.routex.app.student.domain.model.BusStop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardingSelectionScreen(
    onNavigateBack: () -> Unit,
    onStopSelected: (routeId: String, stopId: String) -> Unit,
    viewModel: BoardingSelectionViewModel = hiltViewModel()
) {
    val routeState by viewModel.routeState.collectAsState()
    val userLoc    by viewModel.userLocation.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Boarding Stop") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (routeState) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error -> ErrorScreen(message = (routeState as UiState.Error).message)
                is UiState.Success -> {
                    val route = (routeState as UiState.Success).data
                    val sortedStops = route.stops.sortedBy { it.sequence }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            Text(
                                "Where will you board?",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        items(sortedStops, key = { it.id }) { stop ->
                            StopSelectionCard(
                                stop = stop,
                                userLocation = userLoc,
                                onClick = { onStopSelected(route.id, stop.id) }
                            )
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun StopSelectionCard(
    stop: BusStop, 
    userLocation: android.location.Location?,
    onClick: () -> Unit
) {
    val distance = remember(userLocation, stop) {
        if (userLocation == null) null
        else {
            val results = FloatArray(1)
            android.location.Location.distanceBetween(
                userLocation.latitude, userLocation.longitude,
                stop.latitude, stop.longitude,
                results
            )
            results[0]
        }
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = stop.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Stop ${stop.sequence + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (distance != null) {
                Text(
                    text = formatDistance(distance),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun formatDistance(meters: Float): String = when {
    meters < 1000f -> "${meters.toInt()} m"
    else -> "%.1f km".format(meters / 1000f)
}
