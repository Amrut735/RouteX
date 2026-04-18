package com.routex.app.auth.domain.model

enum class UserRole {
    STUDENT, ADMIN, DRIVER, UNKNOWN;

    companion object {
        fun fromString(value: String): UserRole = when (value.lowercase().trim()) {
            "admin"   -> ADMIN
            "student" -> STUDENT
            "driver"  -> DRIVER
            else      -> UNKNOWN
        }
    }
}
