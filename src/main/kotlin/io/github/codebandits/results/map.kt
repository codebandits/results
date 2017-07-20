package io.github.codebandits.results

fun <failureType, successType, newSuccessType> Result<failureType, successType>.map(
        transform: (successType) -> newSuccessType
): Result<failureType, newSuccessType> {

    return when (this) {
        is Success -> Success(transform(content))
        is Failure -> Failure<failureType, newSuccessType>(content = content)
    }
}

fun <failureType, newFailureType, successType> Result<failureType, successType>.mapError(
        transform: (failureType) -> newFailureType
): Result<newFailureType, successType> {

    return when (this) {
        is Success -> Success<newFailureType, successType>(content = content)
        is Failure -> Failure(transform(content))
    }
}
