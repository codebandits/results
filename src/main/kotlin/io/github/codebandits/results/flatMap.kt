package io.github.codebandits.results

fun <failureType, successType, newSuccessType> Result<failureType, successType>.flatMap(
        transform: (successType) -> Result<failureType, newSuccessType>
): Result<failureType, newSuccessType> {

    return when (this) {
        is Success -> transform(content)
        is Failure -> Failure<failureType, newSuccessType>(content = content)
    }
}

fun <failureType, newFailureType, successType> Result<failureType, successType>.flatMapError(
        transform: (failureType) -> Result<newFailureType, successType>
): Result<newFailureType, successType> {

    return when (this) {
        is Success -> Success<newFailureType, successType>(content = content)
        is Failure -> transform(content)
    }
}
