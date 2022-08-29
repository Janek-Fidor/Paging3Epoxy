package com.example.data.core

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()

    data class Error(val exception: Exception) : ApiResult<Nothing>()
}