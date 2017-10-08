package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Result
import io.github.codebandits.results.Success

fun <Type: Any> Type?.presenceAsResult(): Result<Unit, Type> = if (this == null) Failure(Unit) else Success(this)
