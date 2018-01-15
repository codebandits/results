package io.github.codebandits.results

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DoTest {

    @Test
    fun `successDo on Success should call the function with the content`() {

        val resultA = Success<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.successDo { passedContent = it }

        assertThat(passedContent).isEqualTo(1)
    }

    @Test
    fun `successDo on Failure should not call the function`() {

        val resultA = Failure<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.successDo { passedContent = it }

        assertThat(passedContent).isNull()
    }

    @Test
    fun `successDo on Success should return an equal Success`() {

        val resultA = Success<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.successDo { passedContent = it }

        assertThat(resultB).isInstanceOf(Success::class.java)
        assertThat((resultB as Success).content).isEqualTo(1)
    }

    @Test
    fun `successDo on Failure should return an equal Failure`() {

        val resultA = Failure<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.successDo { passedContent = it }

        assertThat(resultB).isInstanceOf(Failure::class.java)
        assertThat((resultB as Failure).content).isEqualTo(1)
    }

    @Test
    fun `failureDo on Failure should call the function with the content`() {

        val resultA = Failure<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.failureDo { passedContent = it }

        assertThat(passedContent).isEqualTo(1)
    }

    @Test
    fun `failureDo on Success should not call the function`() {

        val resultA = Success<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.failureDo { passedContent = it }

        assertThat(passedContent).isNull()
    }

    @Test
    fun `failureDo on Failure should return an equal Failure`() {

        val resultA = Failure<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.failureDo { passedContent = it }

        assertThat(resultB).isInstanceOf(Failure::class.java)
        assertThat((resultB as Failure).content).isEqualTo(1)
    }

    @Test
    fun `failureDo on Success should return an equal Success`() {

        val resultA = Success<Int, Int>(1)

        var passedContent: Int? = null
        val resultB = resultA.failureDo { passedContent = it }

        assertThat(resultB).isInstanceOf(Success::class.java)
        assertThat((resultB as Success).content).isEqualTo(1)
    }
}
