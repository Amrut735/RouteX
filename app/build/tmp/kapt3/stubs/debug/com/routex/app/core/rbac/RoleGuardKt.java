package com.routex.app.core.rbac;

import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.navigation.NavController;
import com.routex.app.auth.domain.model.UserRole;
import com.routex.app.core.navigation.Screen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\nH\u0007\u001a;\u0010\u0000\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t\u00a2\u0006\u0002\b\nH\u0007\u00a8\u0006\r"}, d2 = {"RoleGuard", "", "requiredRole", "Lcom/routex/app/auth/domain/model/UserRole;", "navController", "Landroidx/navigation/NavController;", "sessionViewModel", "Lcom/routex/app/core/rbac/SessionViewModel;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "allowedRoles", "", "app_debug"})
public final class RoleGuardKt {
    
    /**
     * Composable guard that enforces role-based access at the navigation layer.
     *
     * How it works:
     * 1. Reads the live [SessionState] from [SessionViewModel] (scoped to the NavGraph).
     * 2. While loading → shows a shimmer skeleton.
     * 3. If unauthenticated → navigates to Login and clears the back stack.
     * 4. If authenticated but wrong role → navigates to the user's actual home screen.
     * 5. If role matches → renders [content].
     *
     * Usage in NavGraph:
     * ```kotlin
     * composable(Screen.AdminDashboard.route) {
     *    RoleGuard(requiredRole = UserRole.ADMIN, navController = navController) {
     *        AdminDashboardScreen(navController)
     *    }
     * }
     * ```
     *
     * This is the UI-level enforcement layer.  Even if a user deep-links or
     * manipulates the back stack to reach an admin screen, they will be redirected
     * before the admin UI renders.  The backend security rules (Firestore + RTDB)
     * provide the second, server-side enforcement layer.
     */
    @androidx.compose.runtime.Composable()
    public static final void RoleGuard(@org.jetbrains.annotations.NotNull()
    com.routex.app.auth.domain.model.UserRole requiredRole, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.rbac.SessionViewModel sessionViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
    
    /**
     * Variant that accepts multiple allowed roles (e.g., Admin AND Driver can both
     * access the live map, but Students cannot).
     */
    @androidx.compose.runtime.Composable()
    public static final void RoleGuard(@org.jetbrains.annotations.NotNull()
    java.util.Set<? extends com.routex.app.auth.domain.model.UserRole> allowedRoles, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.routex.app.core.rbac.SessionViewModel sessionViewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> content) {
    }
}