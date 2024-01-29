package com.slayer.domain.models

sealed class NetworkResult<out T : Any> {
    class Success<T: Any>(val code: Int,val data: T) : NetworkResult<T>()
    class Error<T: Any>(val code: Int, val errorMsg: String?) : NetworkResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkResult<T>()
}