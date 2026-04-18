package com.routex.app.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary          = RouteXBlue40,
    onPrimary        = Color.White,
    primaryContainer = RouteXBlue80,
    onPrimaryContainer = RouteXBlue40,
    secondary        = RouteXBlueDim40,
    onSecondary      = Color.White,
    secondaryContainer = RouteXBlueDim80,
    onSecondaryContainer = RouteXBlueDim40,
    tertiary         = RouteXOrange40,
    onTertiary       = Color.White,
    tertiaryContainer = RouteXOrange80,
    onTertiaryContainer = RouteXOrange40,
    surface          = RouteXSurfaceLight,
    onSurface        = Color(0xFF1A1C1E),
    background       = RouteXSurfaceLight,
    onBackground     = Color(0xFF1A1C1E),
    error            = RedError,
    onError          = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary          = RouteXBlue80,
    onPrimary        = RouteXBlue40,
    primaryContainer = Color(0xFF062A45),
    onPrimaryContainer = RouteXBlue80,
    secondary        = RouteXBlueDim80,
    onSecondary      = RouteXBlueDim40,
    secondaryContainer = Color(0xFF1A3A4F),
    onSecondaryContainer = RouteXBlueDim80,
    tertiary         = RouteXOrange80,
    onTertiary       = RouteXOrange40,
    tertiaryContainer = Color(0xFF5C2900),
    onTertiaryContainer = RouteXOrange80,
    surface          = RouteXSurfaceDark,
    onSurface        = Color(0xFFE2E2E6),
    background       = RouteXSurfaceDark,
    onBackground     = Color(0xFFE2E2E6),
    error            = Color(0xFFFFB4AB),
    onError          = Color(0xFF690005),
)

@Composable
fun RouteXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = RouteXTypography,
        shapes      = RouteXShapes,
        content     = content,
    )
}
