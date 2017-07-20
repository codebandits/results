package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import java.util.*

fun <successType> Optional<successType>.asResult(): Result<Unit, successType> = this
        .map { it: successType -> Success<Unit, successType>(it) as Result<Unit, successType> }
        .orElse(Failure<Unit, successType>(Unit))
