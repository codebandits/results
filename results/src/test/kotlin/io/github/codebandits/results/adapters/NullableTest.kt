package io.github.codebandits.results.adapters

import io.github.codebandits.results.Failure
import io.github.codebandits.results.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NullableTest {

    @Nested
    inner class `when the value is not null` {

        val nullable: String? = "freedom"
        val result = nullable.presenceAsResult()

        @Test
        fun `presenceAsResult returns a Success with the non-nullable value`() {
            assertThat(result).isInstanceOf(Success::class.java)
            val content: String = (result as Success).content
            assertThat(content).isEqualTo("freedom")
        }
    }

    @Nested
    inner class `when the value is null` {

        val nullable: String? = null
        val result = nullable.presenceAsResult()

        @Test
        fun `presenceAsResult returns a Failure of type Unit`() {
            assertThat(result).isInstanceOf(Failure::class.java)
            assertThat((result as Failure).content).isEqualTo(Unit)
        }
    }
}
