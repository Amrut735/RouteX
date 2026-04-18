package com.routex.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.routex.app.core.navigation.RouteXNavGraph
import com.routex.app.core.permission.WithNotificationPermission
import com.routex.app.core.ui.ThemeViewModel
import com.routex.app.core.ui.theme.RouteXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            RouteXTheme(darkTheme = isDarkTheme) {
                WithNotificationPermission {
                    RouteXNavGraph()
                }
            }
        }
    }
}
