package com.dgr.data.api

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: String): Result<Nothing>()
    object Loading : Result<Nothing>()

}

val Result<*>.isSuccessFull
    get() = this is Result.Success && data != null