package io.github.codebandits.results

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FlatMapTest {

    @Nested
    inner class `when result is a Success` {

        val result = Success<Int, Int>(1)

        @Nested
        inner class `when the transform returns a Success` {

            val mappedResult = result.flatMap { Success<Int, Int>(it + 1) }

            @Test
            fun `flatMap should return a new Success`() {

                assertThat(mappedResult).isInstanceOf(Success::class.java)
                assertThat((mappedResult as Success).content).isEqualTo(2)
            }
        }

        @Nested
        inner class `when the transform returns a Failure` {

            val mappedResult = result.flatMap { Failure<Int, Int>(it + 1) }

            @Test
            fun `flatMap should return a new Failure`() {

                assertThat(mappedResult).isInstanceOf(Failure::class.java)
                assertThat((mappedResult as Failure).content).isEqualTo(2)
            }
        }

        @Test
        fun `flatMapError should return the same Success`() {

            val mappedResult = result.flatMapError { Success<Int, Int>(it + 1) }

            assertThat(mappedResult).isInstanceOf(Success::class.java)
            assertThat((mappedResult as Success).content).isEqualTo(1)
        }
    }

    @Nested
    inner class `when result is a Failure` {

        val result = Failure<Int, Int>(1)

        @Nested
        inner class `when the transform returns a Success` {

            val mappedResult = result.flatMapError { Success<Int, Int>(it + 1) }

            @Test
            fun `flatMapError should return a new Success`() {

                assertThat(mappedResult).isInstanceOf(Success::class.java)
                assertThat((mappedResult as Success).content).isEqualTo(2)
            }
        }

        @Nested
        inner class `when the transform returns a Failure` {

            val mappedResult = result.flatMapError { Failure<Int, Int>(it + 1) }

            @Test
            fun `flatMapError should return a new Failure`() {

                assertThat(mappedResult).isInstanceOf(Failure::class.java)
                assertThat((mappedResult as Failure).content).isEqualTo(2)
            }
        }

        @Test
        fun `flatMap should return the same Failure`() {

            val mappedResult = result.flatMap { Success<Int, Int>(it + 1) }

            assertThat(mappedResult).isInstanceOf(Failure::class.java)
            assertThat((mappedResult as Failure).content).isEqualTo(1)
        }
    }
}
