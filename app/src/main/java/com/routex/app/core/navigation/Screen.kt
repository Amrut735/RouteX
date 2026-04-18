package com.routex.app.core.navigation

sealed class Screen(val route: String) {
    // ── Entry ─────────────────────────────────────────────────────────────────
    data object Splash      : Screen("splash")
    data object Onboarding  : Screen("onboarding")

    // ── Auth ──────────────────────────────────────────────────────────────────
    data object Login       : Screen("login")
    data object Register    : Screen("register")

    // ── Student ───────────────────────────────────────────────────────────────
    data object StudentDashboard : Screen("student_dashboard")
    data object StudentRoutes    : Screen("student_routes")
    data object BoardingSelection : Screen("boarding_selection/{routeId}") {
        fun withRouteId(routeId: String) = "boarding_selection/$routeId"
    }
    data object AvailableBuses : Screen("available_buses/{routeId}/{stopId}") {
        fun withArgs(routeId: String, stopId: String) = "available_buses/$routeId/$stopId"
    }

    // ── Admin ─────────────────────────────────────────────────────────────────
    data object AdminDashboard    : Screen("admin_dashboard")
    data object AdminManageRoutes : Screen("admin_manage_routes")
    data object AdminBuses        : Screen("admin_buses")
    data object AdminRouteEditor  : Screen("admin_route_editor/{routeId}") {
        fun create(routeId: String = "new") = "admin_route_editor/$routeId"
    }
    data object AdminAnalytics    : Screen("admin_analytics")
    data object AdminEmergency    : Screen("admin_emergency")
    data object AdminTrips        : Screen("admin_trips")
    data object AdminManageDrivers : Screen("admin_manage_drivers")

    // ── Driver ────────────────────────────────────────────────────────────────
    data object DriverDashboard : Screen("driver_dashboard")
    data object DriverMap : Screen("driver_map/{routeId}") {
        fun withRouteId(routeId: String) = "driver_map/$routeId"
    }

    // ── Shared ────────────────────────────────────────────────────────────────
    data object Map : Screen("map/{routeId}") {
        fun withRouteId(routeId: String) = "map/$routeId"
    }

    // ── ETA Tracker ───────────────────────────────────────────────────────────
    data object EtaTracker : Screen("eta/{routeId}") {
        fun withRouteId(routeId: String) = "eta/$routeId"
    }
}
