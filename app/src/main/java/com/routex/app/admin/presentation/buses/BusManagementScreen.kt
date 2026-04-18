package com.routex.app.admin.presentation.buses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.admin.domain.model.Bus
import com.routex.app.admin.domain.model.BusRoute
import com.routex.app.admin.domain.model.Driver
import com.routex.app.core.ui.components.EmptyState
import com.routex.app.core.ui.components.ErrorScreen
import com.routex.app.core.ui.components.LoadingScreen
import com.routex.app.core.ui.components.RouteXButton
import com.routex.app.core.utils.UiState

private enum class ManageTab { BUSES, DRIVERS }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusManagementScreen(
    navController: NavController,
    viewModel: BusManagementViewModel = hiltViewModel(),
) {
    val busesState       by viewModel.busesState.collectAsState()
    val routesState      by viewModel.routesState.collectAsState()
    val actionState      by viewModel.actionState.collectAsState()
    val showBusDialog    by viewModel.showBusDialog.collectAsState()
    val form             by viewModel.busForm.collectAsState()
    val editingBus       by viewModel.editingBus.collectAsState()
    val showDriverDialog by viewModel.showDriverDialog.collectAsState()
    val driverForm       by viewModel.driverForm.collectAsState()
    val driversState     by viewModel.driversState.collectAsState()

    var activeTab by remember { mutableStateOf(ManageTab.BUSES) }

    // Assignment dialog state
    var showAssignDialog by remember { mutableStateOf(false) }
    var assigningDriver  by remember { mutableStateOf<Driver?>(null) }

    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(actionState) {
        when (val s = actionState) {
            is UiState.Success -> { snackbarState.showSnackbar("Saved!"); viewModel.resetActionState() }
            is UiState.Error   -> { snackbarState.showSnackbar(s.message); viewModel.resetActionState() }
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bus & Driver Management") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
            )
        },
        snackbarHost  = { SnackbarHost(snackbarState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (activeTab == ManageTab.BUSES) viewModel.openAddBusDialog()
                    else viewModel.openAddDriverDialog()
                },
            ) {
                Icon(Icons.Rounded.Add, if (activeTab == ManageTab.BUSES) "Add bus" else "Add driver")
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            // ── Tab switcher ─────────────────────────────────────────────────
            TabRow(selectedTabIndex = activeTab.ordinal) {
                Tab(
                    selected = activeTab == ManageTab.BUSES,
                    onClick  = { activeTab = ManageTab.BUSES },
                    text     = { Text("Buses") },
                    icon     = { Icon(Icons.Rounded.DirectionsBus, null, modifier = Modifier.size(18.dp)) },
                )
                Tab(
                    selected = activeTab == ManageTab.DRIVERS,
                    onClick  = { activeTab = ManageTab.DRIVERS },
                    text     = { Text("Drivers") },
                    icon     = { Icon(Icons.Rounded.Person, null, modifier = Modifier.size(18.dp)) },
                )
            }

            // ── Bus tab ───────────────────────────────────────────────────────
            if (activeTab == ManageTab.BUSES) {
                when (val state = busesState) {
                    is UiState.Loading -> LoadingScreen("Loading buses…")
                    is UiState.Error   -> ErrorScreen(state.message, onRetry = {})
                    is UiState.Success -> {
                        if (state.data.isEmpty()) {
                            EmptyState(
                                icon    = Icons.Rounded.DirectionsBus,
                                title   = "No Buses Yet",
                                message = "Tap + to add your first bus.",
                            )
                        } else {
                            LazyColumn(
                                modifier        = Modifier.fillMaxSize(),
                                contentPadding  = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                items(state.data, key = { it.id }) { bus ->
                                    BusCard(
                                        bus      = bus,
                                        onEdit   = { viewModel.openEditBusDialog(bus) },
                                        onDelete = { viewModel.deleteBus(bus.id) },
                                    )
                                }
                            }
                        }
                    }
                    else -> Unit
                }
            }

            // ── Driver tab ────────────────────────────────────────────────────
            if (activeTab == ManageTab.DRIVERS) {
                when (val state = driversState) {
                    is UiState.Loading -> LoadingScreen("Loading drivers…")
                    is UiState.Error   -> ErrorScreen(state.message, onRetry = {})
                    is UiState.Success -> {
                        if (state.data.isEmpty()) {
                            EmptyState(
                                icon    = Icons.Rounded.Person,
                                title   = "No Drivers Yet",
                                message = "Tap + to add a driver pre-record.",
                            )
                        } else {
                            LazyColumn(
                                modifier        = Modifier.fillMaxSize(),
                                contentPadding  = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                items(state.data, key = { it.id }) { driver ->
                                    DriverCard(
                                        driver   = driver,
                                        buses    = (busesState  as? UiState.Success)?.data ?: emptyList(),
                                        routes   = (routesState as? UiState.Success)?.data ?: emptyList(),
                                        onAssign = {
                                            assigningDriver = driver
                                            showAssignDialog = true
                                        },
                                    )
                                }
                            }
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    // ── Bus dialog ────────────────────────────────────────────────────────────
    if (showBusDialog) {
        val routes = (routesState as? UiState.Success)?.data ?: emptyList()
        BusFormDialog(
            form       = form,
            routes     = routes,
            isEditing  = editingBus != null,
            isLoading  = actionState is UiState.Loading,
            onChange   = viewModel::onBusFormChange,
            onDismiss  = viewModel::dismissBusDialog,
            onConfirm  = viewModel::submitBus,
        )
    }

    // ── Add Driver dialog ─────────────────────────────────────────────────────
    if (showDriverDialog) {
        AddDriverDialog(
            form      = driverForm,
            isLoading = actionState is UiState.Loading,
            onChange  = viewModel::onDriverFormChange,
            onDismiss = viewModel::dismissDriverDialog,
            onConfirm = viewModel::submitDriver,
        )
    }

    // ── Assign Driver dialog ──────────────────────────────────────────────────
    if (showAssignDialog && assigningDriver != null) {
        AssignDriverDialog(
            driver    = assigningDriver!!,
            buses     = (busesState  as? UiState.Success)?.data ?: emptyList(),
            routes    = (routesState as? UiState.Success)?.data ?: emptyList(),
            isLoading = actionState is UiState.Loading,
            onDismiss = { showAssignDialog = false; assigningDriver = null },
            onConfirm = { busId, routeId ->
                viewModel.assignDriverToBus(assigningDriver!!.id, busId, routeId)
                showAssignDialog = false
                assigningDriver = null
            },
        )
    }
}

// ── Driver card (list item) ───────────────────────────────────────────────────

@Composable
private fun DriverCard(
    driver: Driver,
    buses: List<Bus>,
    routes: List<BusRoute>,
    onAssign: () -> Unit,
) {
    val assignedBus   = buses.find  { it.id            == driver.assignedBusId   }
    val assignedRoute = routes.find { it.id            == driver.assignedRouteId }

    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text  = driver.name.ifBlank { "Unnamed Driver" },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text  = driver.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text  = "Code: ${driver.driverCode}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                FilledTonalButton(onClick = onAssign) {
                    Icon(Icons.Rounded.AssignmentInd, null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Assign")
                }
            }
            if (assignedBus != null || assignedRoute != null) {
                Spacer(Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (assignedBus != null) {
                        Column {
                            Text("Bus", style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("Bus ${assignedBus.number}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium)
                        }
                    }
                    if (assignedRoute != null) {
                        Column {
                            Text("Route", style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(assignedRoute.routeName.ifBlank { "Route ${assignedRoute.routeNumber}" },
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}

// ── Assign Driver dialog ──────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AssignDriverDialog(
    driver: Driver,
    buses: List<Bus>,
    routes: List<BusRoute>,
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (busId: String, routeId: String) -> Unit,
) {
    var selectedBusId   by remember { mutableStateOf(driver.assignedBusId) }
    var selectedRouteId by remember { mutableStateOf(driver.assignedRouteId) }
    var busExpanded     by remember { mutableStateOf(false) }
    var routeExpanded   by remember { mutableStateOf(false) }

    val selectedBusLabel   = buses.find  { it.id == selectedBusId   }?.number ?: "Unassigned"
    val selectedRouteLabel = routes.find { it.id == selectedRouteId }?.routeName ?: "Unassigned"

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Assign: ${driver.name.ifBlank { driver.email }}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Bus dropdown
                ExposedDropdownMenuBox(expanded = busExpanded, onExpandedChange = { busExpanded = it }) {
                    OutlinedTextField(
                        value         = "Bus $selectedBusLabel",
                        onValueChange = {},
                        readOnly      = true,
                        label         = { Text("Assign Bus") },
                        trailingIcon  = { ExposedDropdownMenuDefaults.TrailingIcon(busExpanded) },
                        modifier      = Modifier.fillMaxWidth().menuAnchor(),
                    )
                    ExposedDropdownMenu(expanded = busExpanded, onDismissRequest = { busExpanded = false }) {
                        DropdownMenuItem(
                            text    = { Text("Unassigned") },
                            onClick = { selectedBusId = ""; busExpanded = false },
                        )
                        buses.forEach { bus ->
                            DropdownMenuItem(
                                text    = { Text("Bus ${bus.number} – ${bus.model}") },
                                onClick = { selectedBusId = bus.id; busExpanded = false },
                            )
                        }
                    }
                }
                // Route dropdown
                ExposedDropdownMenuBox(expanded = routeExpanded, onExpandedChange = { routeExpanded = it }) {
                    OutlinedTextField(
                        value         = selectedRouteLabel,
                        onValueChange = {},
                        readOnly      = true,
                        label         = { Text("Assign Route") },
                        trailingIcon  = { ExposedDropdownMenuDefaults.TrailingIcon(routeExpanded) },
                        modifier      = Modifier.fillMaxWidth().menuAnchor(),
                    )
                    ExposedDropdownMenu(expanded = routeExpanded, onDismissRequest = { routeExpanded = false }) {
                        DropdownMenuItem(
                            text    = { Text("Unassigned") },
                            onClick = { selectedRouteId = ""; routeExpanded = false },
                        )
                        routes.forEach { route ->
                            DropdownMenuItem(
                                text    = { Text(route.routeName.ifBlank { "Route ${route.routeNumber}" }) },
                                onClick = { selectedRouteId = route.id; routeExpanded = false },
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            RouteXButton(
                text      = "Save Assignment",
                onClick   = { onConfirm(selectedBusId, selectedRouteId) },
                isLoading = isLoading,
                modifier  = Modifier.fillMaxWidth(),
            )
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

// ── Bus card ──────────────────────────────────────────────────────────────────

@Composable
private fun BusCard(bus: Bus, onEdit: () -> Unit, onDelete: () -> Unit) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Bus ${bus.number}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                if (bus.model.isNotBlank()) {
                    Text(
                        text = bus.model,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Text(
                    text = "Capacity: ${bus.capacity} | ${if (bus.isActive) "Active" else "Inactive"}",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (bus.isActive) MaterialTheme.colorScheme.tertiary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (bus.licensePlate.isNotBlank()) {
                    Text(
                        text = "Plate: ${bus.licensePlate}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Rounded.Edit, "Edit", tint = MaterialTheme.colorScheme.primary)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Rounded.Delete, "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

// ── Bus form dialog ───────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BusFormDialog(
    form: BusForm,
    routes: List<BusRoute>,
    isEditing: Boolean,
    isLoading: Boolean,
    onChange: (BusForm) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    var routeExpanded by remember { mutableStateOf(false) }
    val selectedRouteName = routes.find { it.id == form.assignedRouteId }?.routeName
        ?: if (form.assignedRouteId.isBlank()) "Unassigned" else form.assignedRouteId

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isEditing) "Edit Bus" else "Add New Bus") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = form.number, onValueChange = { onChange(form.copy(number = it)) },
                    label = { Text("Bus Number *") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.model, onValueChange = { onChange(form.copy(model = it)) },
                    label = { Text("Model (e.g. Volvo 9400)") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.capacity, onValueChange = { onChange(form.copy(capacity = it)) },
                    label = { Text("Seating Capacity") }, singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.licensePlate, onValueChange = { onChange(form.copy(licensePlate = it)) },
                    label = { Text("License Plate") }, singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                if (routes.isNotEmpty()) {
                    ExposedDropdownMenuBox(
                        expanded = routeExpanded,
                        onExpandedChange = { routeExpanded = it },
                    ) {
                        OutlinedTextField(
                            value = selectedRouteName,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Assign to Route") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(routeExpanded) },
                            modifier = Modifier.fillMaxWidth().menuAnchor(),
                        )
                        ExposedDropdownMenu(
                            expanded = routeExpanded,
                            onDismissRequest = { routeExpanded = false },
                        ) {
                            DropdownMenuItem(
                                text = { Text("Unassigned") },
                                onClick = { onChange(form.copy(assignedRouteId = "")); routeExpanded = false },
                            )
                            routes.forEach { route ->
                                DropdownMenuItem(
                                    text = { Text(route.routeName.ifEmpty { "Route ${route.routeNumber}" }) },
                                    onClick = { onChange(form.copy(assignedRouteId = route.id)); routeExpanded = false },
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            RouteXButton(
                text = if (isEditing) "Update Bus" else "Add Bus",
                onClick = onConfirm, isLoading = isLoading,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

// ── Add Driver dialog ─────────────────────────────────────────────────────────

@Composable
private fun AddDriverDialog(
    form: BusManagementViewModel.DriverForm,
    isLoading: Boolean,
    onChange: (BusManagementViewModel.DriverForm) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Driver Record") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "This creates a pre-record. The driver uses their Email + Code to register in the app.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(10.dp),
                    )
                }
                OutlinedTextField(
                    value = form.name,
                    onValueChange = { onChange(form.copy(name = it)) },
                    label = { Text("Full Name *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.email,
                    onValueChange = { onChange(form.copy(email = it)) },
                    label = { Text("Email *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.driverCode,
                    onValueChange = { onChange(form.copy(driverCode = it)) },
                    label = { Text("Driver Code * (e.g. DRV001)") },
                    leadingIcon = { Icon(Icons.Rounded.Key, null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = form.phoneNumber,
                    onValueChange = { onChange(form.copy(phoneNumber = it)) },
                    label = { Text("Phone Number (optional)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            RouteXButton(
                text = "Create Driver Record",
                onClick = onConfirm,
                isLoading = isLoading,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
