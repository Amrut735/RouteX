package com.routex.app.core.rbac

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.routex.app.auth.domain.model.UserRole
import com.routex.app.core.navigation.Screen
import com.routex.app.core.ui.components.ShimmerCardSkeleton

/**
 * Composable guard that enforces role-based access at the navigation layer.
 *
 * How it works:
 *  1. Reads the live [SessionState] from [SessionViewModel] (scoped to the NavGraph).
 *  2. While loading → shows a shimmer skeleton.
 *  3. If unauthenticated → navigates to Login and clears the back stack.
 *  4. If authenticated but wrong role → navigates to the user's actual home screen.
 *  5. If role matches → renders [content].
 *
 * Usage in NavGraph:
 * ```kotlin
 * composable(Screen.AdminDashboard.route) {
 *     RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
 *         AdminDashboardScreen(navController)
 *     }
 * }
 * ```
 *
 * This is the UI-level enforcement layer.  Even if a user deep-links or
 * manipulates the back stack to reach an admin screen, they will be redirected
 * before the admin UI renders.  The backend security rules (Firestore + RTDB)
 * provide the second, server-side enforcement layer.
 */
@Composable
fun RoleGuard(
    requiredRole: UserRole,
    navController: NavController,
    sessionViewModel: SessionViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val session by sessionViewModel.session.collectAsState()

    when (val s = session) {

        // ── Still resolving the session ───────────────────────────────────────
        is SessionState.Loading -> {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                ShimmerCardSkeleton()
            }
        }

        // ── Not signed in at all ──────────────────────────────────────────────
        is SessionState.Unauthenticated -> {
            LaunchedEffect(Unit) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }

        // ── Signed in — check role ────────────────────────────────────────────
        is SessionState.Authenticated -> {
            val user = s.user
            if (user.role == requiredRole) {
                // ✅ Correct role — render the protected screen
                content()
            } else {
                // 🚫 Wrong role — redirect to the user's actual home
                LaunchedEffect(user.uid) {
                    navController.navigate(user.homeRoute) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}

/**
 * Variant that accepts multiple allowed roles (e.g., Admin AND Driver can both
 * access the live map, but Students cannot).
 */
@Composable
fun RoleGuard(
    allowedRoles: Set<UserRole>,
    navController: NavController,
    sessionViewModel: SessionViewModel = hiltViewModel(),
    content: @Composable () -> Unit,
) {
    val session by sessionViewModel.session.collectAsState()

    when (val s = session) {
        is SessionState.Loading -> {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                ShimmerCardSkeleton()
            }
        }
        is SessionState.Unauthenticated -> {
            LaunchedEffect(Unit) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
        is SessionState.Authenticated -> {
            if (s.user.role in allowedRoles) {
                content()
            } else {
                LaunchedEffect(s.user.uid) {
                    navController.navigate(s.user.homeRoute) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
    }
}
