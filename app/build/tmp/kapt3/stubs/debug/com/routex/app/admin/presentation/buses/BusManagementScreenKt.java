package com.routex.app.admin.presentation.buses;

import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.Bus;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.model.Driver;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001az\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00010\u0013H\u0003\u001a,\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a^\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u001e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u001a\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$H\u0007\u001a:\u0010%\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u00a8\u0006\'"}, d2 = {"AddDriverDialog", "", "form", "Lcom/routex/app/admin/presentation/buses/BusManagementViewModel$DriverForm;", "isLoading", "", "onChange", "Lkotlin/Function1;", "onDismiss", "Lkotlin/Function0;", "onConfirm", "AssignDriverDialog", "driver", "Lcom/routex/app/admin/domain/model/Driver;", "buses", "", "Lcom/routex/app/admin/domain/model/Bus;", "routes", "Lcom/routex/app/admin/domain/model/BusRoute;", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "busId", "routeId", "BusCard", "bus", "onEdit", "onDelete", "BusFormDialog", "Lcom/routex/app/admin/presentation/buses/BusForm;", "isEditing", "BusManagementScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/admin/presentation/buses/BusManagementViewModel;", "DriverCard", "onAssign", "app_debug"})
public final class BusManagementScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void BusManagementScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.buses.BusManagementViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DriverCard(com.routex.app.admin.domain.model.Driver driver, java.util.List<com.routex.app.admin.domain.model.Bus> buses, java.util.List<com.routex.app.admin.domain.model.BusRoute> routes, kotlin.jvm.functions.Function0<kotlin.Unit> onAssign) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AssignDriverDialog(com.routex.app.admin.domain.model.Driver driver, java.util.List<com.routex.app.admin.domain.model.Bus> buses, java.util.List<com.routex.app.admin.domain.model.BusRoute> routes, boolean isLoading, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BusCard(com.routex.app.admin.domain.model.Bus bus, kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void BusFormDialog(com.routex.app.admin.presentation.buses.BusForm form, java.util.List<com.routex.app.admin.domain.model.BusRoute> routes, boolean isEditing, boolean isLoading, kotlin.jvm.functions.Function1<? super com.routex.app.admin.presentation.buses.BusForm, kotlin.Unit> onChange, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AddDriverDialog(com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm form, boolean isLoading, kotlin.jvm.functions.Function1<? super com.routex.app.admin.presentation.buses.BusManagementViewModel.DriverForm, kotlin.Unit> onChange, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
}