package com.creativijaya.basiccompose.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class ResultWrapper<out T>(
    private val data: T?
)

object Uninitialized : ResultWrapper<Nothing>(null)

data class Success<out T>(val data: T) : ResultWrapper<T>(data)

data class Error<out T>(val error: Throwable) : ResultWrapper<T>(null)

inline fun <T, R> T.mapSuspend(block: (T) -> R): ResultWrapper<R> {
    return try {
        Success(data = block(this))
    } catch (e: Exception) {
        Error(error = e)
    }
}

inline fun <T, R> Flow<T>.mapFlow(crossinline block: (T) -> R): Flow<ResultWrapper<R>> {
    return transform { value ->
        emit(try {
            Success(data = block(value))
        } catch (e: Exception) {
            Error(error = e)
        })
    }
}

