package com.dgr.data.api.helpers

import android.util.Log
import retrofit2.Response

abstract class SafeAPIRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            val result =  response.body()
            Log.i("SafeAPIRequest SUCCESS", result.toString())
            Result.Success(result!!)
        } else {
            val errorMessage = errorMessage(response)
            Log.i("SafeAPIRequest ERROR", errorMessage)
            Result.Error(errorMessage)
        }
    }

    private fun errorMessage(response: Response<*>): String {
        val error = response.errorBody()?.string()
        val message = StringBuilder()
        message.append("Error code: ${response.code()}\n")
        error?.let { message.append(it) }
        return message.toString()
    }
}