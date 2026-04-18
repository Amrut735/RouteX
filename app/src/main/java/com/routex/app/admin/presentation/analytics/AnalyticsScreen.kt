package com.routex.app.admin.presentation.analytics

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Route
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.admin.domain.model.AnalyticsSnapshot
import com.routex.app.admin.domain.model.HourlyActivity
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(
    navController: NavController,
    viewModel: AnalyticsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics Dashboard") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = viewModel::load) {
                        Icon(Icons.Rounded.Refresh, "Refresh")
                    }
                },
            )
        },
    ) { padding ->
        when (val s = state) {
            is UiState.Loading -> LoadingScreen("Loading analytics…")
            is UiState.Error   -> ErrorScreen(s.message, onRetry = viewModel::load)
            is UiState.Success -> AnalyticsContent(data = s.data, modifier = Modifier.padding(padding))
            else -> Unit
        }
    }
}

@Composable
private fun AnalyticsContent(data: AnalyticsSnapshot, modifier: Modifier = Modifier) {
    val primary     = MaterialTheme.colorScheme.primary
    val secondary   = MaterialTheme.colorScheme.secondary
    val surfaceVar  = MaterialTheme.colorScheme.surfaceVariant

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ── KPI row 1 ─────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                KpiCard(modifier = Modifier.weight(1f),
                    label = "Trips Today",   value = data.totalTripsToday.toString(),
                    color = primary)
                KpiCard(modifier = Modifier.weight(1f),
                    label = "Active Drivers", value = data.activeDriversNow.toString(),
                    color = secondary)
            }
        }

        // ── KPI row 2 ─────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                KpiCard(modifier = Modifier.weight(1f),
                    label = "On-Time",  value = "${data.onTimePercentage.toInt()}%",
                    color = Color(0xFF2ECC71))
                KpiCard(modifier = Modifier.weight(1f),
                    label = "Avg Speed", value = "${data.avgSpeedKmh.toInt()} km/h",
                    color = Color(0xFF3498DB))
            }
        }

        // ── KPI row 3 ─────────────────────────────────────────────────────────
        item {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                KpiCard(modifier = Modifier.weight(1f),
                    label = "Total Dist.", value = "${"%.1f".format(data.totalDistanceTodayKm)} km",
                    color = Color(0xFFE67E22))
                KpiCard(modifier = Modifier.weight(1f),
                    label = "Alerts Sent", value = data.alertsSentToday.toString(),
                    color = Color(0xFFE74C3C))
            }
        }

        // ── Peak hour banner ──────────────────────────────────────────────────
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(Icons.Rounded.Analytics, null,
                        tint = MaterialTheme.colorScheme.secondary)
                    Column {
                        Text("Current Traffic Period",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer)
                        Text(data.peakHour,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }
                }
            }
        }

        // ── Hourly bar chart ──────────────────────────────────────────────────
        if (data.hourlyActivity.isNotEmpty()) {
            item {
                ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Hourly Activity",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(12.dp))
                        HourlyBarChart(activity = data.hourlyActivity, barColor = primary)
                    }
                }
            }
        }

        // ── Route load ────────────────────────────────────────────────────────
        if (data.routeLoadMap.isNotEmpty()) {
            item {
                Text("Subscriber Load by Route",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold)
            }
            val maxLoad = data.routeLoadMap.values.maxOrNull() ?: 1
            items(data.routeLoadMap.entries.toList(), key = { it.key }) { (routeName, count) ->
                RouteLoadRow(
                    routeName = routeName,
                    count     = count,
                    maxCount  = maxLoad,
                    barColor  = primary,
                )
            }
        }
    }
}

// ── KPI Card ──────────────────────────────────────────────────────────────────

@Composable
private fun KpiCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = color,
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ── Hourly bar chart ──────────────────────────────────────────────────────────

@Composable
private fun HourlyBarChart(activity: List<HourlyActivity>, barColor: Color) {
    val max = activity.maxOfOrNull { it.tripCount.toFloat() }?.coerceAtLeast(1f) ?: 1f

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        val barWidth = size.width / (activity.size * 1.5f)
        val gap      = barWidth / 2
        activity.forEachIndexed { idx, hour ->
            val barHeight = (hour.tripCount / max) * size.height * 0.85f
            val x = idx * (barWidth + gap)
            val y = size.height - barHeight
            drawRect(
                color   = barColor.copy(alpha = if (hour.hour in 7..9 || hour.hour in 17..19) 1f else 0.5f),
                topLeft = Offset(x, y),
                size    = Size(barWidth, barHeight),
            )
        }
    }

    // Hour labels (just show every 3 hours)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        activity.filter { it.hour % 3 == 0 }.forEach { h ->
            Text(
                text  = "${h.hour}:00",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

// ── Route load row ────────────────────────────────────────────────────────────

@Composable
private fun RouteLoadRow(routeName: String, count: Int, maxCount: Int, barColor: Color) {
    val fraction = count.toFloat() / maxCount.toFloat()
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(Icons.Rounded.Route, null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.primary)
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(routeName,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium)
                Text("$count students",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(barColor),
                )
            }
        }
    }
}
