package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class OptionalTest {

    @Nested
    inner class `when the Optional has a value` {

        val optional: Optional<Int> = Optional.of(1)
        val result = optional.asResult()

        @Test
        fun `asResult returns a Success with the Optional's value`() {
            assertThat(result).isInstanceOf(Success::class.java)
            assertThat((result as Success).content).isEqualTo(1)
        }
    }

    @Nested
    inner class `when the Optional is empty` {

        val optional: Optional<Int> = Optional.empty()
        val result = optional.asResult()

        @Test
        fun `asResult returns a Failure of type Unit`() {
            assertThat(result).isInstanceOf(Failure::class.java)
            assertThat((result as Failure).content).isEqualTo(Unit)
        }
    }
}
