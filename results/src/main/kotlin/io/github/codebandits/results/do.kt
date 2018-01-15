package io.github.codebandits.results

fun <FailureType, SuccessType> Result<FailureType, SuccessType>.successDo(
    function: (SuccessType) -> Unit
): Result<FailureType, SuccessType> = this.also {
    when (it) {
        is Success -> function(it.content)
    }
}

fun <FailureType, SuccessType> Result<FailureType, SuccessType>.failureDo(
    function: (FailureType) -> Unit
): Result<FailureType, SuccessType> = this.also {
    when (it) {
        is Failure -> function(it.content)
    }
}
