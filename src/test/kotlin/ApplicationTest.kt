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
    fun 기능_당첨번호_입력() {
        assertSimpleTest {
            run("8000", "1", "1,2,3,4,5,6", "7")
            assertThat(output()).contains("8개를 구매했습니다.", "[1, 2, 3, 4, 5, 6, 7]")
        }
    }

    @Test
    fun 기능_당첨번호_미입력() {
        assertRandomUniqueNumbersInRangeTest(
            {
                run("8000", "2")
                assertThat(output()).contains("")
            },
            mutableListOf<Int>(41, 42, 8, 21, 23, 43, 3)
        )
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