package io.github.codebandits.results

import java.lang.AssertionError
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

infix fun <failureType, successType> Result<failureType, successType>.succeedsAnd(onSuccess: (successType) -> Unit) {
    when (this) {
        is Success -> onSuccess(this.content)
        is Failure -> throw AssertionError("Result should have been a Success but was a Failure with ${this.content}")
    }
}

infix fun <failureType, successType> Result<failureType, successType>.failsAnd(onFailure: (failureType) -> Unit) {
    when (this) {
        is Success -> throw AssertionError("Result should have been a Failure but was a Success with ${this.content}")
        is Failure -> onFailure(this.content)
    }
}

infix fun <failureType, successType> Result<failureType, successType>.succeedsWith(expectedSuccess: Any) {
    when (this) {
        is Success -> assert(this.content == expectedSuccess, { "Success should have been $expectedSuccess but was ${this.content}" })
        is Failure -> throw AssertionError("Result should have been a Success but was a Failure with ${this.content}")
    }
}

infix fun <failureType, successType> Result<failureType, successType>.failsWith(expectedFailure: Any) {
    when (this) {
        is Success -> throw AssertionError("Result should have been a Failure but was a Success with ${this.content}")
        is Failure -> assert(this.content == expectedFailure, { "Failure should have been $expectedFailure but was ${this.content}" })
    }
}

infix fun <failureType, successType> Result<failureType, successType>.succeedsWithA(expectedClass: KClass<*>) {
    when (this) {
        is Success -> assert(checkNotNull<Any>(this.content)::class.isSubclassOf(expectedClass) , { "Success should have been a subclass of $expectedClass but was ${checkNotNull<Any>(this.content)::class}" })
        is Failure -> throw AssertionError("Result should have been a Success but was a Failure of ${checkNotNull<Any>(this.content)::class}")
    }
}

infix fun <failureType, successType> Result<failureType, successType>.failsWithA(expectedClass: KClass<*>) {
    when (this) {
        is Success -> throw AssertionError("Result should have been a Failure but was a Success of ${checkNotNull<Any>(this.content)::class}")
        is Failure -> assert(checkNotNull<Any>(this.content)::class.isSubclassOf(expectedClass) , { "Success should have been a subclass of $expectedClass but was ${checkNotNull<Any>(this.content)::class}" })
    }
}
