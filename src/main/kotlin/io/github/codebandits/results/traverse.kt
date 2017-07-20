package io.github.codebandits.results

fun <failureType, successType> List<Result<failureType, successType>>.traverse() : Result<failureType, List<successType>> {
    val list : Result<failureType, List<successType>> = Success(content = emptyList())
    return this.fold(list) { accumulator: Result<failureType, List<successType>>, result : Result<failureType, successType> ->
        when(accumulator) {
            is Success -> {
                when(result) {
                    is Success -> Success<failureType, List<successType>>(content = accumulator.content.plus(result.content))
                    is Failure -> Failure<failureType, List<successType>>(content = result.content)
                }
            }
            is Failure -> Failure<failureType, List<successType>>(content = accumulator.content)
        }
    }
}
