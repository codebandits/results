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

infix fun <failureType, successType> Result<failureType, successType>.succeedsWith(expectedSuccess: Any) {
    when (this) {
        is Success -> assert(this.content == expectedSuccess, { "Result should have been $expectedSuccess but was ${this.content}" })
        is Failure -> throw AssertionError("Result should have been a Success")
    }
}

infix fun <failureType, successType> Result<failureType, successType>.failsWith(expectedFailure: Any) {
    when (this) {
        is Success -> throw AssertionError("Result should have been a Failure")
        is Failure -> assert(this.content == expectedFailure, { "Result should have been $expectedFailure but was ${this.content}" })
    }
}
