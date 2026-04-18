package com.routex.app.driver.presentation;

import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.routex.app.core.navigation.Screen;
import com.routex.app.trips.domain.model.Trip;
import com.routex.app.trips.domain.model.TripStatus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u001a\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0003\u001aZ\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0003\u001a\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0013H\u0003\u00a8\u0006\u001a"}, d2 = {"AssignmentInfoSection", "", "driverRecord", "Lcom/routex/app/admin/domain/model/Driver;", "assignedRoute", "Lcom/routex/app/student/domain/model/Route;", "resolvedBusNum", "", "DriverScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/driver/presentation/DriverViewModel;", "LiveIndicator", "isLive", "", "TripControlCard", "isTracking", "activeTrip", "Lcom/routex/app/trips/domain/model/Trip;", "isAssigned", "onStartTrip", "Lkotlin/Function0;", "onEndTrip", "TripInfoRow", "trip", "app_debug"})
public final class DriverScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DriverScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.driver.presentation.DriverViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AssignmentInfoSection(com.routex.app.admin.domain.model.Driver driverRecord, com.routex.app.student.domain.model.Route assignedRoute, java.lang.String resolvedBusNum) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TripControlCard(boolean isTracking, com.routex.app.trips.domain.model.Trip activeTrip, com.routex.app.admin.domain.model.Driver driverRecord, com.routex.app.student.domain.model.Route assignedRoute, java.lang.String resolvedBusNum, boolean isAssigned, kotlin.jvm.functions.Function0<kotlin.Unit> onStartTrip, kotlin.jvm.functions.Function0<kotlin.Unit> onEndTrip) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TripInfoRow(com.routex.app.trips.domain.model.Trip trip) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LiveIndicator(boolean isLive) {
    }
}