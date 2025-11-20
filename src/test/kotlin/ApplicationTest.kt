package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

import org.junit.jupiter.api.Test

internal class ApplicationTest : NsTest() {

    @Test
    fun 기능1() {
        assertRandomUniqueNumbersInRangeTest(
            {
                run("")
                assertThat(output()).contains("")
            },
            mutableListOf<Int>()
        )
    }

    @Test
    fun 기능2() {
        assertSimpleTest {
            run("")
            assertThat(output()).contains("")
        }
    }

    @Test
    fun 예외() {
        assertSimpleTest {
            assertThatThrownBy { runException("") }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    override fun runMain() {
        main()
    }
}