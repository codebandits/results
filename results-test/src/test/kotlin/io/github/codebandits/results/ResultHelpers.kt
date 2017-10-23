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
        fun `succeeds returns the content`() {

            val content = result.succeeds()
            assertThat(content).isEqualTo(1)
        }

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

        @Test
        fun `succeedsWith matches the expected value`() {
            result succeedsWith 1

            assertThrows(AssertionError::class.java, {
                result succeedsWith 2
            })
        }

        @Test
        fun `failsWith fails`() {
            assertThrows(AssertionError::class.java, {
                result failsWith 1
            })
        }

        @Test
        fun `succeedsWithA matches the expected class`() {
            result succeedsWithA Number::class

            assertThrows(AssertionError::class.java, {
                result succeedsWithA String::class
            })
        }

        @Test
        fun `failsWithA fails`() {
            assertThrows(AssertionError::class.java, {
                result failsWithA Number::class
            })
        }
    }

    @Nested
    inner class `when result is a Failure` {

        val result = Failure<Int, Int>(1)

        @Test
        fun `succeeds fails`() {

            assertThrows(AssertionError::class.java, {
                result.succeeds()
            })
        }

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

        @Test
        fun `succeedsWith fails`() {
            assertThrows(AssertionError::class.java, {
                result succeedsWith 1
            })
        }

        @Test
        fun `failsWith matches the expected value`() {
            result failsWith 1

            assertThrows(AssertionError::class.java, {
                result failsWith 2
            })
        }

        @Test
        fun `succeedsWithA fails`() {
            assertThrows(AssertionError::class.java, {
                result succeedsWithA Number::class
            })
        }

        @Test
        fun `failsWithA matches the expected class`() {
            result failsWithA Number::class

            assertThrows(AssertionError::class.java, {
                result failsWithA String::class
            })
        }
    }
}
