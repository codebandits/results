package io.github.codebandits.results

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.AssertionError

class ResultHelpers {

    @Nested
    inner class `when result is a Success` {

        val result = Success<Int, Int>(1)

        @Test
        fun `succeedsAnd yields the content`() {

            result succeedsAnd {
                assertThat(it).isEqualTo(1)
            }
        }

        @Test
        fun `failsAnd fails`() {

            assertThrows(AssertionError::class.java, {
                result failsAnd {}
            })
        }
    }

    @Nested
    inner class `when result is a Failure` {

        val result = Failure<Int, Int>(1)

        @Test
        fun `failsAnd yields the content`() {

            result failsAnd {
                assertThat(it).isEqualTo(1)
            }
        }

        @Test
        fun `succeedsAnd fails`() {

            assertThrows(AssertionError::class.java, {
                result succeedsAnd {}
            })
        }
    }
}
