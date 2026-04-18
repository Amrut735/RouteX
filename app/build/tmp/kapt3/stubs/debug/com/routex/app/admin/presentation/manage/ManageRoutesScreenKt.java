package com.routex.app.admin.presentation.manage;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarDuration;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.SnackbarResult;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u001e\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u001a\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u00a8\u0006\u0014"}, d2 = {"AddRouteDialog", "", "form", "Lcom/routex/app/admin/presentation/manage/AddRouteForm;", "onFormChange", "Lkotlin/Function1;", "isLoading", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "ManageRouteCard", "route", "Lcom/routex/app/admin/domain/model/BusRoute;", "onDelete", "ManageRoutesScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/admin/presentation/manage/ManageRoutesViewModel;", "app_debug"})
public final class ManageRoutesScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ManageRoutesScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.manage.ManageRoutesViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ManageRouteCard(com.routex.app.admin.domain.model.BusRoute route, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AddRouteDialog(com.routex.app.admin.presentation.manage.AddRouteForm form, kotlin.jvm.functions.Function1<? super com.routex.app.admin.presentation.manage.AddRouteForm, kotlin.Unit> onFormChange, boolean isLoading, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
}