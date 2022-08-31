package com.example.domain.core


sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()
}

inline fun <R, T> Result<T>.execute(
    onSuccess: (value: Result.Success<T>) -> R,
    onError: (error: Result.Error) -> R
): R {
    return when (this) {
        is Result.Success -> onSuccess(this)
        is Result.Error -> onError(this)
    }
}
