package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ThrowableTest {

    @Nested
    inner class `when the function returns a value` {

        val function: () -> Int = { 1 }
        val result = wrapThrowableInResult { function() }

        @Test
        fun `wrapThrowableInResult returns a Success with the return value`() {
            assertThat(result).isInstanceOf(Success::class.java)
            assertThat((result as Success).content).isEqualTo(1)
        }
    }

    @Nested
    inner class `when the function throws an exception` {

        val exception = java.lang.Exception("Incoming Bald Eagle!")
        val function: () -> Int = { throw exception }
        val result = wrapThrowableInResult { function() }

        @Test
        fun `wrapThrowableInResult returns a Failure with the exception`() {
            assertThat(result).isInstanceOf(Failure::class.java)
            assertThat((result as Failure).content).isSameAs(exception)
        }
    }
}
