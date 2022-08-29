package com.example.data.core

sealed class ApiResult<T> {
    data class Success<T>(val value: T) : ApiResult<T>()

    data class Error(val exception: Exception) : ApiResult<Unit>()
}