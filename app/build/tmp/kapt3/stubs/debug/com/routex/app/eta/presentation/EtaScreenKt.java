package com.routex.app.eta.presentation;

import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.SuggestionChipDefaults;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.navigation.NavController;
import com.routex.app.core.utils.UiState;
import com.routex.app.eta.domain.model.BusStatus;
import com.routex.app.eta.domain.model.EtaResult;
import com.routex.app.student.domain.model.BusStop;
import com.routex.app.student.domain.model.Route;
import com.routex.app.student.domain.usecase.MissLikelihood;
import com.routex.app.student.domain.usecase.MissedBusPrediction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0003\u001a\u001a\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a \u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0003\u001a\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0003\u001a4\u0010\u0019\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u001fH\u0003\u001a4\u0010 \u001a\u00020\u00012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\b\u0010#\u001a\u0004\u0018\u00010\"2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010\u001fH\u0003\u001a7\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0003\u00a2\u0006\u0002\u0010,\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"EtaArc", "", "etaMinutes", "", "statusColor", "Landroidx/compose/ui/graphics/Color;", "EtaArc-4WTKRHQ", "(FJ)V", "EtaResultCard", "eta", "Lcom/routex/app/eta/domain/model/EtaResult;", "EtaScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/routex/app/eta/presentation/EtaViewModel;", "MetricItem", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "value", "MissedBusPredictionCard", "prediction", "Lcom/routex/app/student/domain/usecase/MissedBusPrediction;", "RoutePicker", "routes", "", "Lcom/routex/app/student/domain/model/Route;", "selectedRoute", "onSelect", "Lkotlin/Function1;", "StopPicker", "stops", "Lcom/routex/app/student/domain/model/BusStop;", "selectedStop", "StopTimelineItem", "stop", "isSelected", "", "busStatus", "Lcom/routex/app/eta/domain/model/BusStatus;", "totalStops", "", "(Lcom/routex/app/student/domain/model/BusStop;ZLjava/lang/Float;Lcom/routex/app/eta/domain/model/BusStatus;I)V", "app_debug"})
public final class EtaScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void EtaScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.eta.presentation.EtaViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void RoutePicker(java.util.List<com.routex.app.student.domain.model.Route> routes, com.routex.app.student.domain.model.Route selectedRoute, kotlin.jvm.functions.Function1<? super com.routex.app.student.domain.model.Route, kotlin.Unit> onSelect) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void StopPicker(java.util.List<com.routex.app.student.domain.model.BusStop> stops, com.routex.app.student.domain.model.BusStop selectedStop, kotlin.jvm.functions.Function1<? super com.routex.app.student.domain.model.BusStop, kotlin.Unit> onSelect) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EtaResultCard(com.routex.app.eta.domain.model.EtaResult eta) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MetricItem(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MissedBusPredictionCard(com.routex.app.student.domain.usecase.MissedBusPrediction prediction) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void StopTimelineItem(com.routex.app.student.domain.model.BusStop stop, boolean isSelected, java.lang.Float etaMinutes, com.routex.app.eta.domain.model.BusStatus busStatus, int totalStops) {
    }
}