package io.github.codebandits.results

fun <FailureType, SuccessType, newSuccessType> Result<FailureType, SuccessType>.map(
        transform: (SuccessType) -> newSuccessType
): Result<FailureType, newSuccessType> {

    return when (this) {
        is Success -> Success(transform(content))
        is Failure -> Failure(content = content)
    }
}

fun <FailureType, newFailureType, SuccessType> Result<FailureType, SuccessType>.mapError(
        transform: (FailureType) -> newFailureType
): Result<newFailureType, SuccessType> {

    return when (this) {
        is Success -> Success(content = content)
        is Failure -> Failure(transform(content))
    }
}
