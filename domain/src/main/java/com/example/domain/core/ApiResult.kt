package com.example.domain.core

import kotlin.Exception

sealed class ApiResult<T> {
    data class Success<T>(val value: T) : ApiResult<T>()

    data class Error(val exception: Exception) : ApiResult<Unit>()
}

inline fun <R, T> ApiResult<T>.execute(
    onSuccess: (value: ApiResult.Success<T>) -> R,
    onError: (error: ApiResult.Error) -> R
): R {
    return when (this) {
        is ApiResult.Success -> onSuccess(this)
        is ApiResult.Error -> onError(this)
    }
}
