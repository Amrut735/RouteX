package com.routex.app.core.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean =
    isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword(): Boolean = length >= 6

fun String.isValidName(): Boolean = trim().length >= 2

/** Returns a user-friendly error message from a Throwable. */
fun Throwable.toUserMessage(): String =
    message?.takeIf { it.isNotBlank() }
        ?: "An unexpected error occurred. Please try again."
