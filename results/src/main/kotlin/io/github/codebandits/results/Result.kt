package io.github.codebandits.results

sealed class Result<FailureType, SuccessType>
data class Success<FailureType, SuccessType>(val content: SuccessType) : Result<FailureType, SuccessType>()
data class Failure<FailureType, SuccessType>(val content: FailureType) : Result<FailureType, SuccessType>()
