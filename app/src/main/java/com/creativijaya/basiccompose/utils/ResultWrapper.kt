package com.creativijaya.basiccompose.utils

sealed class ResultWrapper<out T>(
    private val data: T?
)

object Uninitialized : ResultWrapper<Nothing>(null)

data class Success<out T>(val data: T) : ResultWrapper<T>(data)

data class Error<out T>(val error: Throwable) : ResultWrapper<T>(null)
