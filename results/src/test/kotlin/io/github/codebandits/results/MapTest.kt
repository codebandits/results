package io.github.codebandits.results

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {

    @Test
    fun `map on Success should produce a new Success`() {

        val resultA = Success<Int, Int>(1)

        val resultB = resultA.map { "#$it" }

        assertThat(resultB).isInstanceOf(Success::class.java)
        assertThat((resultB as Success).content).isEqualTo("#1")
    }

    @Test
    fun `map on Failure should produce the same Failure`() {

        val resultA = Failure<Int, Int>(1)

        val resultB = resultA.map { "#$it" }

        assertThat(resultB).isInstanceOf(Failure::class.java)
        assertThat((resultB as Failure).content).isEqualTo(1)
    }

    @Test
    fun `mapError on Success should produce the same Success`() {

        val resultA = Success<Int, Int>(1)

        val resultB = resultA.mapError { "#$it" }

        assertThat(resultB).isInstanceOf(Success::class.java)
        assertThat((resultB as Success).content).isEqualTo(1)
    }

    @Test
    fun `mapError on Failure should produce a new Failure`() {

        val resultA = Failure<Int, Int>(1)

        val resultB = resultA.mapError { "#$it" }

        assertThat(resultB).isInstanceOf(Failure::class.java)
        assertThat((resultB as Failure).content).isEqualTo("#1")
    }
}
