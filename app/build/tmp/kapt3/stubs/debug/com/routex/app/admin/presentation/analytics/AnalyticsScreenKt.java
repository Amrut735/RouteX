package com.routex.app.admin.presentation.analytics;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavController;
import com.routex.app.admin.domain.model.AnalyticsSnapshot;
import com.routex.app.admin.domain.model.HourlyActivity;
import com.routex.app.core.utils.UiState;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a(\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a4\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a2\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"AnalyticsContent", "", "data", "Lcom/routex/app/admin/domain/model/AnalyticsSnapshot;", "modifier", "Landroidx/compose/ui/Modifier;", "AnalyticsScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/admin/presentation/analytics/AnalyticsViewModel;", "HourlyBarChart", "activity", "", "Lcom/routex/app/admin/domain/model/HourlyActivity;", "barColor", "Landroidx/compose/ui/graphics/Color;", "HourlyBarChart-4WTKRHQ", "(Ljava/util/List;J)V", "KpiCard", "label", "", "value", "color", "KpiCard-9LQNqLg", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;)V", "RouteLoadRow", "routeName", "count", "", "maxCount", "RouteLoadRow-g2O1Hgs", "(Ljava/lang/String;IIJ)V", "app_debug"})
public final class AnalyticsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AnalyticsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.admin.presentation.analytics.AnalyticsViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AnalyticsContent(com.routex.app.admin.domain.model.AnalyticsSnapshot data, androidx.compose.ui.Modifier modifier) {
    }
}