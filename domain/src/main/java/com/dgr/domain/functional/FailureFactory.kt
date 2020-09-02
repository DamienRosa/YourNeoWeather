package com.dgr.domain.functional

import java.lang.Exception

open class FailureFactory {
    open fun handleException(exception: Exception): FailureBo = FailureBo.UnexpectedFailure
    fun handleError(): FailureBo = FailureBo.ServerError


//    private fun errorMessage(response: Response<*>): String {
//        val error = response.errorBody().string()
//        val message = StringBuilder()
//        message.append("Error code: ${response.code()}\n")
//        error?.let { message.append(it) }
//        return message.toString()
//    }
}