package com.routex.app.admin.presentation.emergency;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.model.EmergencyAlert;
import com.routex.app.admin.domain.model.EmergencyType;
import com.routex.app.core.utils.UiState;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a$\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001aV\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u00a8\u0006\u0019"}, d2 = {"AlertCard", "", "alert", "Lcom/routex/app/admin/domain/model/EmergencyAlert;", "onResolve", "Lkotlin/Function0;", "EmergencyScreen", "navController", "Landroidx/navigation/NavController;", "adminName", "", "viewModel", "Lcom/routex/app/admin/presentation/emergency/EmergencyViewModel;", "SendAlertDialog", "form", "Lcom/routex/app/admin/presentation/emergency/AlertForm;", "routes", "", "Lcom/routex/app/admin/domain/model/BusRoute;", "isLoading", "", "onChange", "Lkotlin/Function1;", "onDismiss", "onConfirm", "app_debug"})
public final class EmergencyScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void EmergencyScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.lang.String adminName, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.emergency.EmergencyViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AlertCard(com.routex.app.admin.domain.model.EmergencyAlert alert, kotlin.jvm.functions.Function0<kotlin.Unit> onResolve) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void SendAlertDialog(com.routex.app.admin.presentation.emergency.AlertForm form, java.util.List<com.routex.app.admin.domain.model.BusRoute> routes, boolean isLoading, kotlin.jvm.functions.Function1<? super com.routex.app.admin.presentation.emergency.AlertForm, kotlin.Unit> onChange, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
}