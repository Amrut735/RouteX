package com.routex.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.routex.app.admin.presentation.analytics.AnalyticsScreen
import com.routex.app.admin.presentation.buses.BusManagementScreen
import com.routex.app.admin.presentation.dashboard.AdminDashboardScreen
import com.routex.app.admin.presentation.emergency.EmergencyScreen
import com.routex.app.admin.presentation.manage.ManageRoutesScreen
import com.routex.app.admin.presentation.routes.RouteEditorScreen
import com.routex.app.admin.presentation.trips.AdminTripsScreen
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.auth.presentation.login.LoginScreen
import com.routex.app.auth.presentation.onboarding.OnboardingScreen
import com.routex.app.auth.presentation.register.RegisterScreen
import com.routex.app.auth.presentation.splash.SplashScreen
import com.routex.app.core.permission.WithLocationPermission
import com.routex.app.core.rbac.RoleGuard
import com.routex.app.driver.presentation.DriverMapScreen
import com.routex.app.driver.presentation.DriverScreen
import com.routex.app.eta.presentation.EtaScreen
import com.routex.app.maps.presentation.MapScreen
import com.routex.app.student.presentation.dashboard.StudentDashboardScreen
import com.routex.app.student.presentation.routes.RoutesScreen

@Composable
fun RouteXNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        // ── Entry (no guard — not yet authenticated) ──────────────────────────
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }

        // ── Auth (no guard — these are the auth entry points) ─────────────────
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        // ── Student (STUDENT role required) ───────────────────────────────────
        composable(Screen.StudentDashboard.route) {
            RoleGuard(requiredRole = UserRole.STUDENT, navController = navController) {
                StudentDashboardScreen(navController = navController)
            }
        }

        composable(Screen.StudentRoutes.route) {
            RoleGuard(requiredRole = UserRole.STUDENT, navController = navController) {
                RoutesScreen(navController = navController)
            }
        }

        composable(
            route = Screen.BoardingSelection.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) {
            RoleGuard(requiredRole = UserRole.STUDENT, navController = navController) {
                com.routex.app.student.presentation.boarding.BoardingSelectionScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onStopSelected = { rId, stopId ->
                        navController.navigate(Screen.AvailableBuses.withArgs(rId, stopId))
                    }
                )
            }
        }

        composable(
            route = Screen.AvailableBuses.route,
            arguments = listOf(
                navArgument("routeId") { type = NavType.StringType },
                navArgument("stopId") { type = NavType.StringType }
            )
        ) {
            RoleGuard(requiredRole = UserRole.STUDENT, navController = navController) {
                com.routex.app.student.presentation.boarding.AvailableBusesScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        // ── Admin (ADMIN role required) ────────────────────────────────────────
        composable(Screen.AdminDashboard.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                AdminDashboardScreen(navController = navController)
            }
        }

        composable(Screen.AdminManageRoutes.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                ManageRoutesScreen(navController = navController)
            }
        }

        composable(Screen.AdminBuses.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                BusManagementScreen(navController = navController)
            }
        }

        composable(
            route = Screen.AdminRouteEditor.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType }),
        ) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                WithLocationPermission {
                    RouteEditorScreen(navController = navController)
                }
            }
        }

        composable(Screen.AdminAnalytics.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                AnalyticsScreen(navController = navController)
            }
        }

        composable(Screen.AdminEmergency.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                EmergencyScreen(navController = navController)
            }
        }

        composable(Screen.AdminTrips.route) {
            RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
                AdminTripsScreen(navController = navController)
            }
        }

        // ── Driver (DRIVER role required) ──────────────────────────────────────
        composable(Screen.DriverDashboard.route) {
            RoleGuard(requiredRole = UserRole.DRIVER, navController = navController) {
                WithLocationPermission {
                    DriverScreen(navController = navController)
                }
            }
        }

        // ── Driver Map (GPS navigation view for the driver) ────────────────────
        composable(
            route     = Screen.DriverMap.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType }),
        ) {
            RoleGuard(requiredRole = UserRole.DRIVER, navController = navController) {
                WithLocationPermission {
                    DriverMapScreen(onBack = { navController.popBackStack() })
                }
            }
        }

        // ── Map (Student + Driver — both roles can view the live map) ──────────
        composable(
            route = Screen.Map.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId").orEmpty()
            RoleGuard(
                allowedRoles = setOf(UserRole.STUDENT, UserRole.DRIVER, UserRole.ADMIN),
                navController = navController,
            ) {
                WithLocationPermission {
                    MapScreen(navController = navController, routeId = routeId)
                }
            }
        }

        // ── ETA Tracker (Student + Admin can view) ─────────────────────────────
        composable(
            route = Screen.EtaTracker.route,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType }),
        ) {
            RoleGuard(
                allowedRoles = setOf(UserRole.STUDENT, UserRole.ADMIN),
                navController = navController,
            ) {
                EtaScreen(navController = navController)
            }
        }
    }
}
