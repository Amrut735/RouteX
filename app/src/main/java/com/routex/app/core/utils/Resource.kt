package com.routex.app.core.utils

/**
 * A domain/data-layer result wrapper, distinct from [UiState].
 * Repository and use-case functions return Resource; ViewModels map it to UiState.
 */
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(
        val message: String,
        val throwable: Throwable? = null,
    ) : Resource<Nothing>()

    data object Loading : Resource<Nothing>()
}

inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> = when (this) {
    is Resource.Success -> Resource.Success(transform(data))
    is Resource.Error   -> Resource.Error(message, throwable)
    is Resource.Loading -> Resource.Loading
}

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

inline fun <T> Resource<T>.onError(action: (String, Throwable?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message, throwable)
    return this
}
