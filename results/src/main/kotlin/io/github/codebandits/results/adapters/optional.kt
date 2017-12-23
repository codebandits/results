package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Result
import io.github.codebandits.results.Success
import java.util.*

fun <SuccessType> Optional<SuccessType>.asResult(): Result<Unit, SuccessType> = this
        .map { it: SuccessType -> Success<Unit, SuccessType>(it) as Result<Unit, SuccessType> }
        .orElse(Failure(Unit))
