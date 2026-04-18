package com.routex.app.admin.presentation.emergency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.model.EmergencyAlert
import com.routex.app.admin.domain.model.EmergencyType
import com.routex.app.core.ui.components.EmptyState
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyScreen(
    navController: NavController,
    adminName: String = "Admin",
    viewModel: EmergencyViewModel = hiltViewModel(),
) {
    val activeAlerts by viewModel.activeAlerts.collectAsState()
    val form         by viewModel.form.collectAsState()
    val sendState    by viewModel.sendState.collectAsState()
    val showDialog   by viewModel.showDialog.collectAsState()
    val routes       by viewModel.routes.collectAsState()

    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(sendState) {
        when (val s = sendState) {
            is UiState.Success -> { snackbarState.showSnackbar("Alert sent!"); viewModel.resetSendState() }
            is UiState.Error   -> { snackbarState.showSnackbar(s.message); viewModel.resetSendState() }
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Emergency Alerts") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = viewModel::openDialog,
                icon = { Icon(Icons.Rounded.Warning, null) },
                text = { Text("Send Alert") },
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor   = MaterialTheme.colorScheme.onErrorContainer,
            )
        },
    ) { padding ->
        if (activeAlerts.isEmpty()) {
            EmptyState(
                icon    = Icons.Rounded.NotificationsActive,
                title   = "No Active Alerts",
                message = "All clear! Tap 'Send Alert' if you need to notify students.",
                modifier = Modifier.padding(padding),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Text(
                        text = "${activeAlerts.size} Active Alert${if (activeAlerts.size != 1) "s" else ""}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                items(activeAlerts, key = { it.id }) { alert ->
                    AlertCard(
                        alert     = alert,
                        onResolve = { viewModel.resolveAlert(alert.id) },
                    )
                }
            }
        }
    }

    if (showDialog) {
        SendAlertDialog(
            form      = form,
            routes    = routes,
            isLoading = sendState is UiState.Loading,
            onChange  = viewModel::onFormChange,
            onDismiss = viewModel::closeDialog,
            onConfirm = { viewModel.sendAlert(adminName) },
        )
    }
}

@Composable
private fun AlertCard(alert: EmergencyAlert, onResolve: () -> Unit) {
    val typeColor = when (alert.type) {
        EmergencyType.ACCIDENT  -> Color(0xFFE74C3C)
        EmergencyType.BREAKDOWN -> Color(0xFFE67E22)
        EmergencyType.DELAY     -> Color(0xFFF39C12)
        EmergencyType.FULL      -> Color(0xFF3498DB)
        EmergencyType.GENERAL   -> Color(0xFF9B59B6)
    }

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = typeColor.copy(alpha = 0.08f),
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = alert.type.emoji,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 12.dp, top = 2.dp),
            )
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = alert.type.label,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = typeColor,
                    )
                    if (alert.busNumber.isNotBlank()) {
                        Text(
                            text = "Bus ${alert.busNumber}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                Text(
                    text = alert.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Sent by ${alert.sentBy} · ${
                        SimpleDateFormat("HH:mm", Locale.getDefault())
                            .format(Date(alert.timestamp))
                    }",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            IconButton(onClick = onResolve, modifier = Modifier.size(36.dp)) {
                Icon(
                    Icons.Rounded.CheckCircle,
                    contentDescription = "Resolve",
                    tint = Color(0xFF2ECC71),
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SendAlertDialog(
    form: AlertForm,
    routes: List<BusRoute>,
    isLoading: Boolean,
    onChange: (AlertForm) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    var typeExpanded  by remember { mutableStateOf(false) }
    var routeExpanded by remember { mutableStateOf(false) }

    val selectedRouteName = routes.find { it.id == form.selectedRouteId }?.routeName
        ?: if (form.selectedRouteId.isBlank()) "All Routes" else form.selectedRouteId

    AlertDialog(
        onDismissRequest = onDismiss,
        icon  = { Icon(Icons.Rounded.Warning, null, tint = MaterialTheme.colorScheme.error) },
        title = { Text("Send Emergency Alert") },
        text  = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Type dropdown
                ExposedDropdownMenuBox(
                    expanded = typeExpanded,
                    onExpandedChange = { typeExpanded = it },
                ) {
                    OutlinedTextField(
                        value = "${form.type.emoji} ${form.type.label}",
                        onValueChange = {}, readOnly = true,
                        label = { Text("Alert Type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(typeExpanded) },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                    )
                    ExposedDropdownMenu(
                        expanded = typeExpanded,
                        onDismissRequest = { typeExpanded = false },
                    ) {
                        EmergencyType.entries.forEach { type ->
                            DropdownMenuItem(
                                text = { Text("${type.emoji} ${type.label}") },
                                onClick = { onChange(form.copy(type = type)); typeExpanded = false },
                            )
                        }
                    }
                }

                // Route dropdown
                if (routes.isNotEmpty()) {
                    ExposedDropdownMenuBox(
                        expanded = routeExpanded,
                        onExpandedChange = { routeExpanded = it },
                    ) {
                        OutlinedTextField(
                            value = selectedRouteName, onValueChange = {}, readOnly = true,
                            label = { Text("Affected Route") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(routeExpanded) },
                            modifier = Modifier.fillMaxWidth().menuAnchor(),
                        )
                        ExposedDropdownMenu(
                            expanded = routeExpanded,
                            onDismissRequest = { routeExpanded = false },
                        ) {
                            DropdownMenuItem(text = { Text("All Routes") },
                                onClick = { onChange(form.copy(selectedRouteId = "")); routeExpanded = false })
                            routes.forEach { route ->
                                DropdownMenuItem(
                                    text = { Text(route.routeName.ifEmpty { "Route ${route.routeNumber}" }) },
                                    onClick = { onChange(form.copy(selectedRouteId = route.id)); routeExpanded = false },
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = form.busNumber, onValueChange = { onChange(form.copy(busNumber = it)) },
                    label = { Text("Bus Number (optional)") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.message, onValueChange = { onChange(form.copy(message = it)) },
                    label = { Text("Message *") }, maxLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            RouteXButton(
                text = "Send Alert", onClick = onConfirm, isLoading = isLoading,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
