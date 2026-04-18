package com.routex.app.auth.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.core.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val destination by viewModel.destination.collectAsState()

    // Animate logo in
    val scale = remember { Animatable(0.5f) }
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        alpha.animateTo(1f)
    }

    // Navigate once the destination is resolved
    LaunchedEffect(destination) {
        destination?.let { dest ->
            val route = when (dest) {
                SplashDestination.Onboarding       -> Screen.Onboarding.route
                SplashDestination.Login            -> Screen.Login.route
                SplashDestination.StudentDashboard -> Screen.StudentDashboard.route
                SplashDestination.AdminDashboard   -> Screen.AdminDashboard.route
                SplashDestination.DriverDashboard  -> Screen.DriverDashboard.route
            }
            navController.navigate(route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value),
        ) {
            Icon(
                imageVector = Icons.Rounded.DirectionsBus,
                contentDescription = "RouteX Logo",
                modifier = Modifier.size(96.dp),
                tint = Color.White,
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "RouteX",
                style = MaterialTheme.typography.displayLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Text(
                text = "Smart Campus Transport",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White.copy(alpha = 0.8f),
                ),
            )
        }
    }
}
