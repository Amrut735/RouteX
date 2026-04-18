package com.routex.app.core.utils

/**
 * Represents the UI state for screens following Loading → Success | Error lifecycle.
 */
sealed class UiState<out T> {
    /** Initial idle state before any action is triggered. */
    data object Idle : UiState<Nothing>()

    /** A loading / in-progress state. */
    data object Loading : UiState<Nothing>()

    /** Successfully completed with [data]. */
    data class Success<T>(val data: T) : UiState<T>()

    /** Completed with an error [message]. */
    data class Error(val message: String, val throwable: Throwable? = null) : UiState<Nothing>()
}

val <T> UiState<T>.isLoading get() = this is UiState.Loading
val <T> UiState<T>.isSuccess get() = this is UiState.Success
val <T> UiState<T>.isError get() = this is UiState.Error
