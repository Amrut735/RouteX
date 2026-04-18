package com.routex.app.admin.presentation.trips

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.core.ui.components.EmptyState
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.ui.components.TripStatusChip
import com.routex.app.core.utils.UiState
import com.routex.app.trips.domain.model.Trip
import com.routex.app.trips.domain.model.TripStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTripsScreen(
    navController: NavController,
    viewModel: AdminTripsViewModel = hiltViewModel(),
) {
    val filteredTrips  by viewModel.filteredTrips.collectAsState()
    val activeFilter   by viewModel.filter.collectAsState()
    val isLoadingMore  by viewModel.isLoadingMore.collectAsState()
    val hasReachedEnd  by viewModel.hasReachedEnd.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text  = "Trip Monitor",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text  = "All trips (last 50)",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            // ── Filter chips ──────────────────────────────────────────────────
            LazyRow(
                contentPadding     = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    StatusFilterChip(
                        label      = "All",
                        isSelected = activeFilter == null,
                        color      = MaterialTheme.colorScheme.primary,
                        onClick    = { viewModel.setFilter(null) },
                    )
                }
                items(TripStatus.entries.toList()) { status ->
                    StatusFilterChip(
                        label      = status.label,
                        isSelected = activeFilter == status,
                        color      = status.color,
                        onClick    = { viewModel.setFilter(status) },
                    )
                }
            }

            // ── Trip list ─────────────────────────────────────────────────────
            when (val state = filteredTrips) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Error   -> ErrorScreen(state.message)
                is UiState.Success -> {
                    if (state.data.isEmpty()) {
                        EmptyState(
                            icon    = Icons.Rounded.History,
                            title   = "No trips found",
                            message = if (activeFilter != null)
                                "No ${activeFilter!!.label.lowercase()} trips"
                            else
                                "Trips will appear here once drivers start them",
                        )
                    } else {
                        val listState = rememberLazyListState()

                        // Trigger next page when the user scrolls near the bottom
                        val shouldLoadMore by remember {
                            derivedStateOf {
                                val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()
                                    ?.index ?: 0
                                val totalItems = listState.layoutInfo.totalItemsCount
                                lastVisible >= totalItems - 3 && !hasReachedEnd
                            }
                        }
                        LaunchedEffect(shouldLoadMore) {
                            if (shouldLoadMore) viewModel.loadNextPage()
                        }

                        LazyColumn(
                            state           = listState,
                            contentPadding  = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(state.data, key = { it.id }) { trip ->
                                TripCard(trip = trip)
                            }
                            if (isLoadingMore) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(28.dp),
                                            strokeWidth = 2.dp,
                                        )
                                    }
                                }
                            }
                            item { Spacer(Modifier.height(80.dp)) }
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

// ── Filter chip ───────────────────────────────────────────────────────────────

@Composable
private fun StatusFilterChip(
    label: String,
    isSelected: Boolean,
    color: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
) {
    FilterChip(
        selected = isSelected,
        onClick  = onClick,
        label    = { Text(label, style = MaterialTheme.typography.labelMedium) },
        colors   = FilterChipDefaults.filterChipColors(
            selectedContainerColor     = color.copy(alpha = 0.18f),
            selectedLabelColor         = color,
            selectedLeadingIconColor   = color,
        ),
        border   = FilterChipDefaults.filterChipBorder(
            enabled          = true,
            selected         = isSelected,
            selectedBorderColor = color,
            selectedBorderWidth = 1.5.dp,
        ),
    )
}

// ── Trip card (admin view) ────────────────────────────────────────────────────

@Composable
private fun TripCard(trip: Trip) {
    val formatter = remember(trip.id) { SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault()) }

    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp)) {

            // ── Header ────────────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Rounded.DirectionsBus,
                            contentDescription = null,
                            tint     = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                    Column {
                        Text(
                            text  = "Bus ${trip.busNumber}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text  = trip.routeName.ifBlank { "Route ${trip.routeId.take(8)}" },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                TripStatusChip(status = trip.status)
            }

            Spacer(Modifier.height(10.dp))

            // ── Metadata row ──────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TripMetaItem(
                    label = "Driver",
                    value = trip.driverName.ifBlank { "–" },
                    modifier = Modifier.weight(1f),
                )
                TripMetaItem(
                    label = "Started",
                    value = if (trip.startTime > 0)
                        formatter.format(Date(trip.startTime)) else "–",
                    modifier = Modifier.weight(1f),
                )
                if (trip.status == TripStatus.COMPLETED && trip.endTime > 0) {
                    TripMetaItem(
                        label = "Duration",
                        value = "${trip.durationMinutes} min",
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun TripMetaItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text  = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text  = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
