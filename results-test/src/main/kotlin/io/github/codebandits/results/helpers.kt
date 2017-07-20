package io.github.codebandits.results

import java.lang.AssertionError

infix fun <failureType, successType> Result<failureType, successType>.succeedsAnd(onSuccess: (successType) -> Unit) {
    when (this) {
        is Success -> onSuccess(this.content)
        is Failure -> throw AssertionError("Result should have been a Success")
    }
}

infix fun <failureType, successType> Result<failureType, successType>.failsAnd(onFailure: (failureType) -> Unit) {
    when (this) {
        is Success -> throw AssertionError("Result should have been a Failure")
        is Failure -> onFailure(this.content)
    }
}
