package com.example.domain.core


sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Unit>()
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
