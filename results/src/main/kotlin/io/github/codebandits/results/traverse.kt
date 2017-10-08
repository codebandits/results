package io.github.codebandits.results

fun <FailureType, SuccessType> List<Result<FailureType, SuccessType>>.traverse() : Result<FailureType, List<SuccessType>> {
    val list : Result<FailureType, List<SuccessType>> = Success(content = emptyList())
    return this.fold(list) { accumulator: Result<FailureType, List<SuccessType>>, result : Result<FailureType, SuccessType> ->
        when(accumulator) {
            is Success -> {
                when(result) {
                    is Success -> Success<FailureType, List<SuccessType>>(content = accumulator.content.plus(result.content))
                    is Failure -> Failure<FailureType, List<SuccessType>>(content = result.content)
                }
            }
            is Failure -> Failure<FailureType, List<SuccessType>>(content = accumulator.content)
        }
    }
}
