package com.routex.app.auth.domain.model

data class User(
    val uid: String = "",
    val email: String = "",
    val displayName: String = "",
    val role: UserRole = UserRole.STUDENT,
    val phoneNumber: String? = null,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
) {
    val isAdmin:   Boolean get() = role == UserRole.ADMIN
    val isDriver:  Boolean get() = role == UserRole.DRIVER
    val isStudent: Boolean get() = role == UserRole.STUDENT

    /** Returns the correct home destination route string for this user. */
    val homeRoute: String get() = when (role) {
        UserRole.ADMIN  -> "admin_dashboard"
        UserRole.DRIVER -> "driver_dashboard"
        else            -> "student_dashboard"
    }
}
