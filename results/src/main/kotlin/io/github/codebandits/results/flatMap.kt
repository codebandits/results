package io.github.codebandits.results

fun <FailureType, SuccessType, newSuccessType> Result<FailureType, SuccessType>.flatMap(
        transform: (SuccessType) -> Result<FailureType, newSuccessType>
): Result<FailureType, newSuccessType> {

    return when (this) {
        is Success -> transform(content)
        is Failure -> Failure(content = content)
    }
}

fun <FailureType, newFailureType, SuccessType> Result<FailureType, SuccessType>.flatMapError(
        transform: (FailureType) -> Result<newFailureType, SuccessType>
): Result<newFailureType, SuccessType> {

    return when (this) {
        is Success -> Success(content = content)
        is Failure -> transform(content)
    }
}
