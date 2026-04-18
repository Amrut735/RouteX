package com.routex.app.admin.presentation.dashboard;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.BusRoute;
import com.routex.app.admin.domain.repository.AdminStats;
import com.routex.app.core.location.CollegeHub;
import com.routex.app.core.navigation.Screen;
import com.routex.app.core.ui.ThemeViewModel;
import com.routex.app.core.utils.UiState;
import com.routex.app.maps.domain.model.BusLocationUpdate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a$\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0003\u001a\u0010\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0003\u001a*\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0003\u001a\u0010\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bH\u0003\u00a8\u0006\u001c"}, d2 = {"AdminActionCard", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "AdminDashboardScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/admin/presentation/dashboard/AdminDashboardViewModel;", "themeViewModel", "Lcom/routex/app/core/ui/ThemeViewModel;", "AdminRouteCard", "route", "Lcom/routex/app/admin/domain/model/BusRoute;", "ApproachingCampusBusCard", "bus", "Lcom/routex/app/maps/domain/model/BusLocationUpdate;", "StatCard", "value", "StatsGrid", "stats", "Lcom/routex/app/admin/domain/repository/AdminStats;", "app_debug"})
public final class AdminDashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AdminDashboardScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.dashboard.AdminDashboardViewModel viewModel, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.ui.ThemeViewModel themeViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AdminActionCard(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatsGrid(com.routex.app.admin.domain.repository.AdminStats stats) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StatCard(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AdminRouteCard(com.routex.app.admin.domain.model.BusRoute route) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ApproachingCampusBusCard(com.routex.app.maps.domain.model.BusLocationUpdate bus) {
    }
}