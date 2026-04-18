package com.routex.app.core.ui.components;

import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bH\u0007\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bH\u0007\u001a\u0012\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u0012\u0010\u0011\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007\u001a\u0012\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a<\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00102\b\b\u0002\u0010\u0017\u001a\u00020\u0010H\u0007\u001a2\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u0010H\u0007\u001a\u001c\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u001bH\u0007\u001a\u0012\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\u001d"}, d2 = {"EmptyState", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "title", "", "message", "modifier", "Landroidx/compose/ui/Modifier;", "actionLabel", "onAction", "Lkotlin/Function0;", "ErrorScreen", "onRetry", "LoadingOverlay", "visible", "", "LoadingScreen", "PulsingDot", "RouteXButton", "text", "onClick", "enabled", "isLoading", "RouteXOutlinedButton", "ShimmerBox", "shape", "Landroidx/compose/ui/graphics/Shape;", "ShimmerCardSkeleton", "app_debug"})
public final class CommonComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingOverlay(boolean visible) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RouteXButton(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean enabled, boolean isLoading) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RouteXOutlinedButton(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, boolean enabled) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.Nullable()
    java.lang.String actionLabel, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAction) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShimmerBox(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.Shape shape) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ShimmerCardSkeleton(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PulsingDot(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}