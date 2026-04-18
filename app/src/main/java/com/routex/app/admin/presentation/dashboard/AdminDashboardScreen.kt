package com.routex.app.admin.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.repository.AdminStats
import com.routex.app.core.location.CollegeHub
import com.routex.app.core.navigation.Screen
import com.routex.app.core.ui.ThemeViewModel
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.utils.UiState
import com.routex.app.maps.domain.model.BusLocationUpdate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    navController: NavController,
    viewModel: AdminDashboardViewModel = hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel(),
) {
    val user by viewModel.currentUser.collectAsState()
    val statsState by viewModel.statsState.collectAsState()
    val routesState by viewModel.recentRoutesState.collectAsState()
    val approachingCampusBuses by viewModel.approachingCampusBuses.collectAsState()
    val isDark by themeViewModel.isDarkTheme.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Admin Panel",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Welcome, ${user?.displayName?.split(" ")?.firstOrNull() ?: "Admin"}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { themeViewModel.toggleTheme() }) {
                        Icon(
                            imageVector = if (isDark) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                            contentDescription = "Toggle theme",
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.signOut {
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.AdminDashboard.route) { inclusive = true }
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
        floatingActionButton = {
            androidx.compose.foundation.layout.Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Seed demo data — tap once to bootstrap Firebase with sample routes
                androidx.compose.material3.SmallFloatingActionButton(
                    onClick = {
                        viewModel.seedDemoData { ok ->
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    if (ok) "Demo data seeded!" else "Seed failed — check Firebase",
                                )
                            }
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ) {
                    Icon(
                        Icons.Rounded.CloudUpload,
                        contentDescription = "Seed demo data",
                        tint = MaterialTheme.colorScheme.tertiary,
                    )
                }
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate(Screen.AdminRouteEditor.create()) },
                    icon = { Icon(Icons.Rounded.EditNote, contentDescription = null) },
                    text = { Text("Route Editor") },
                )
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Stats cards
            item {
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(8.dp))
                when (val state = statsState) {
                    is UiState.Loading -> LoadingScreen()
                    is UiState.Error   -> Text(
                        text = state.message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                    )
                    is UiState.Success -> StatsGrid(stats = state.data)
                    else -> Unit
                }
            }

            // Admin Quick Actions grid
            item {
                Text(
                    text = "Admin Tools",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.Route,
                            label = "Manage Routes",
                            onClick = { navController.navigate(Screen.AdminManageRoutes.route) },
                        )
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.DirectionsBus,
                            label = "Buses",
                            onClick = { navController.navigate(Screen.AdminBuses.route) },
                        )
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.Map,
                            label = "Route Editor",
                            onClick = { navController.navigate(Screen.AdminRouteEditor.create()) },
                        )
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.Analytics,
                            label = "Analytics",
                            onClick = { navController.navigate(Screen.AdminAnalytics.route) },
                        )
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.DirectionsBus,
                            label = "Trip Monitor",
                            onClick = { navController.navigate(Screen.AdminTrips.route) },
                        )
                        AdminActionCard(
                            modifier = Modifier.weight(1f),
                            icon = Icons.Rounded.Person,
                            label = "Drivers",
                            onClick = { navController.navigate(Screen.AdminBuses.route) },
                        )
                    }
                    // Emergency full-width button
                    ElevatedCard(
                        onClick = { navController.navigate(Screen.AdminEmergency.route) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                        ),
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Icon(Icons.Rounded.Warning, null,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(24.dp))
                            Column {
                                Text("Emergency Alerts",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onErrorContainer)
                                Text("Send urgent notifications to all students",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.7f))
                            }
                        }
                    }
                }
            }

            // ── Approaching Campus ────────────────────────────────────────────
            if (approachingCampusBuses.isNotEmpty()) {
                item {
                    Text(
                        text = "🎓  Approaching KLS GIT  (${approachingCampusBuses.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(Modifier.height(4.dp))
                }
                items(approachingCampusBuses, key = { "campus_${it.busNumber}" }) { bus ->
                    ApproachingCampusBusCard(bus = bus)
                }
                item { Spacer(Modifier.height(8.dp)) }
            }

            // Recent routes
            item {
                Text(
                    text = "Recent Routes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            when (val state = routesState) {
                is UiState.Loading -> item { LoadingScreen() }
                is UiState.Error   -> item { ErrorScreen(state.message) }
                is UiState.Success -> {
                    if (state.data.isEmpty()) {
                        item {
                            Text(
                                text = "No routes configured yet. Tap 'Manage Routes' to add one.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    } else {
                        items(state.data, key = { it.id }) { route ->
                            AdminRouteCard(route = route)
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable
private fun AdminActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.primary)
            }
            Text(label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun StatsGrid(stats: AdminStats) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.Route,
                label = "Total Routes",
                value = stats.totalRoutes.toString(),
            )
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.DirectionsBus,
                label = "Active Buses",
                value = stats.activeRoutes.toString(),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.Groups,
                label = "Students",
                value = stats.totalStudents.toString(),
            )
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.Person,
                label = "Drivers",
                value = stats.totalDrivers.toString(),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.DirectionsBus,
                label = "Total Buses",
                value = stats.totalBuses.toString(),
            )
            StatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.Warning,
                label = "Active Alerts",
                value = stats.activeAlertsCount.toString(),
            )
        }
    }
}

@Composable
private fun StatCard(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun AdminRouteCard(route: BusRoute) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        if (route.isActive) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Rounded.DirectionsBus,
                    contentDescription = null,
                    tint = if (route.isActive)
                        MaterialTheme.colorScheme.secondary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = route.routeName.ifEmpty { "Route ${route.routeNumber}" },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    if (route.isActive) {
                        Badge {
                            Text("Active", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
                Text(
                    text = "${route.startPoint} → ${route.endPoint}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (route.driverName.isNotBlank()) {
                    Text(
                        text = "Driver: ${route.driverName}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Text(
                text = "Bus ${route.busNumber}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

// ── Approaching-campus bus card ───────────────────────────────────────────────

@Composable
private fun ApproachingCampusBusCard(bus: BusLocationUpdate) {
    androidx.compose.material3.ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.elevatedCardColors(
            containerColor = androidx.compose.ui.graphics.Color(0xFFFFF8E1),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
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
                        .background(androidx.compose.ui.graphics.Color(0xFFFFE082)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Rounded.DirectionsBus,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color(0xFF5D4037),
                        modifier = Modifier.size(20.dp),
                    )
                }
                Column {
                    Text(
                        text  = "Bus ${bus.busNumber}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color(0xFF4E342E),
                    )
                    Text(
                        text  = "Approaching ${CollegeHub.SHORT_NAME}",
                        style = MaterialTheme.typography.bodySmall,
                        color = androidx.compose.ui.graphics.Color(0xFF6D4C41),
                    )
                }
            }
            Text(
                text  = "${bus.speed.toInt()} km/h",
                style = MaterialTheme.typography.labelMedium,
                color = androidx.compose.ui.graphics.Color(0xFF5D4037),
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
