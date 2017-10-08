package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Result
import io.github.codebandits.results.Success

fun <SuccessType> wrapThrowableInResult(fn: () -> SuccessType): Result<Throwable, SuccessType> {
    return try {
        Success(fn.invoke())
    } catch (e: Throwable) {
        Failure(e)
    }
}
