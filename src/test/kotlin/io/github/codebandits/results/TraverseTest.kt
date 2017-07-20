package io.github.codebandits.results

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TraverseTest {

    @Nested
    inner class `when all the result objects in the List are a Success` {

        val results = listOf(
                Success<Int, Int>(1),
                Success<Int, Int>(2),
                Success<Int, Int>(3),
                Success<Int, Int>(4),
                Success<Int, Int>(5)
        )

        val traverseResult = results.traverse()

        @Test
        fun `traverse should return a new Success`() {
            assertThat(traverseResult).isInstanceOf(Success::class.java)
        }

        @Test
        fun `traverse should return a list containing each Success content`() {
            assertThat((traverseResult as Success).content).containsExactly(1, 2, 3, 4, 5)
        }
    }

    @Nested
    inner class `when some the result objects in the List are a Failure` {

        val results = listOf(
                Success<Int, Int>(1),
                Failure<Int, Int>(2),
                Failure<Int, Int>(3),
                Success<Int, Int>(4),
                Success<Int, Int>(5)
        )

        val traverseResult = results.traverse()

        @Test
        fun `traverse should return a new Failure`() {
            assertThat(traverseResult).isInstanceOf(Failure::class.java)
        }

        @Test
        fun `traverse should return the first Failure`() {
            assertThat((traverseResult as Failure).content).isEqualTo(2)
        }
    }

    @Nested
    inner class `when the List of Result objects is empty` {

        val results = emptyList<Result<Int, Int>>()

        val traverseResult = results.traverse()

        @Test
        fun `traverse should return a new Success`() {
            assertThat(traverseResult).isInstanceOf(Success::class.java)
        }

        @Test
        fun `traverse should return an empty List`() {
            assertThat((traverseResult as Success).content).isEmpty()
        }
    }
}
