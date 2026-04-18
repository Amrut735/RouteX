package com.routex.app.admin.presentation.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageRoutesScreen(
    navController: NavController,
    viewModel: ManageRoutesViewModel = hiltViewModel(),
) {
    val routesState   by viewModel.routesState.collectAsState()
    val actionState   by viewModel.actionState.collectAsState()
    val showDialog    by viewModel.showAddDialog.collectAsState()
    val form          by viewModel.form.collectAsState()
    val showUndoEvent by viewModel.showUndoEvent.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    // ── Generic action feedback (errors only now) ─────────────────────────────
    LaunchedEffect(actionState) {
        when (val state = actionState) {
            is UiState.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.resetActionState()
            }
            is UiState.Success -> viewModel.resetActionState()
            else -> Unit
        }
    }

    // ── Undo delete snackbar ──────────────────────────────────────────────────
    LaunchedEffect(showUndoEvent) {
        if (!showUndoEvent) return@LaunchedEffect
        val result = snackbarHostState.showSnackbar(
            message      = "Route deleted",
            actionLabel  = "UNDO",
            duration     = SnackbarDuration.Long,   // ~10 s
        )
        when (result) {
            SnackbarResult.ActionPerformed -> viewModel.undoDelete()
            SnackbarResult.Dismissed       -> viewModel.clearUndoState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Routes") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::showDialog) {
                Icon(Icons.Rounded.Add, contentDescription = "Add route")
            }
        },
    ) { padding ->
        when (val state = routesState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Error   -> ErrorScreen(state.message)
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    if (state.data.isEmpty()) {
                        item {
                            Text(
                                text = "No routes yet. Tap + to add the first route.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    } else {
                        items(state.data, key = { it.id }) { route ->
                            ManageRouteCard(
                                route    = route,
                                // Pass the full route object so undo can restore it
                                onDelete = { viewModel.deleteRoute(route) },
                            )
                        }
                    }
                }
            }
            else -> Unit
        }
    }

    if (showDialog) {
        AddRouteDialog(
            form         = form,
            onFormChange = viewModel::onFormChange,
            isLoading    = actionState is UiState.Loading,
            onDismiss    = viewModel::dismissDialog,
            onConfirm    = viewModel::submitAddRoute,
        )
    }
}

@Composable
private fun ManageRouteCard(route: BusRoute, onDelete: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${route.routeNumber} – ${route.routeName}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "${route.startPoint} → ${route.endPoint}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (route.stopNames.isNotEmpty()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text  = "Stops: ${route.stopNames.joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (route.scheduleTime.isNotBlank()) {
                    Text(
                        text  = "Scheduled: ${route.scheduleTime}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                if (route.busNumber.isNotBlank()) {
                    Text(
                        text = "Bus: ${route.busNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Composable
private fun AddRouteDialog(
    form: AddRouteForm,
    onFormChange: (AddRouteForm) -> Unit,
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Route") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = form.routeNumber,
                    onValueChange = { onFormChange(form.copy(routeNumber = it)) },
                    label = { Text("Route Number *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.routeName,
                    onValueChange = { onFormChange(form.copy(routeName = it)) },
                    label = { Text("Route Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.startPoint,
                    onValueChange = { onFormChange(form.copy(startPoint = it)) },
                    label = { Text("Start Point") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.endPoint,
                    onValueChange = { onFormChange(form.copy(endPoint = it)) },
                    label = { Text("End Point") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.busNumber,
                    onValueChange = { onFormChange(form.copy(busNumber = it)) },
                    label = { Text("Bus Number") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.driverName,
                    onValueChange = { onFormChange(form.copy(driverName = it)) },
                    label = { Text("Driver Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.scheduleTime,
                    onValueChange = { onFormChange(form.copy(scheduleTime = it)) },
                    label = { Text("Schedule (e.g. 8:00 AM)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            RouteXButton(
                text = "Add Route",
                onClick = onConfirm,
                isLoading = isLoading,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
    )
}
