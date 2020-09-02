package com.dgr.data.repository

import com.dgr.domain.functional.Either
import com.dgr.domain.functional.FailureBo
import com.dgr.domain.functional.FailureFactory
import retrofit2.Response
import java.lang.Exception

inline fun <T, R> safeApiCall(
    block: () -> Response<T>,
    transform: (T) -> R,
    errorFactory: FailureFactory = FailureFactory()
): Either<FailureBo, R> =
    try {
        val response = block()
        when(response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))
            false -> Either.Left(errorFactory.handleError())
        }
    } catch (e: Exception) {
        Either.Left(errorFactory.handleException(e))
    }

inline fun <T, R> safeDbCall(
    block: () -> T,
    transform: (T) -> R,
    errorFactory: FailureFactory = FailureFactory()
): Either<FailureBo, R> =
    try {
        val response = block()
        Either.Right(transform(response))
    } catch (e: Exception) {
        Either.Left(errorFactory.handleException(e))
    }
