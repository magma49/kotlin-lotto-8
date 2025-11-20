package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest
import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

import org.junit.jupiter.api.Test

internal class ApplicationTest : NsTest() {

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
    fun 예외_구입금액에_문자_입력() {
        assertSimpleTest {
            run("a", "8000", "1", "1,2,3,4,5,6", "7")
            assertThat(output()).contains(
                "[ERROR] 구입금액에 숫자만 입력해야 합니다.",
                "8개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6, 7]"
            )
        }
    }

    @Test
    fun 예외_구입금액에_0_입력() {
        assertSimpleTest {
            run("0", "8000", "1", "1,2,3,4,5,6", "7")
            assertThat(output()).contains(
                "[ERROR] 로또를 사셔야 합니다.",
                "8개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6, 7]"
            )
        }
    }

    @Test
    fun 예외_구입금액에_100단위_입력() {
        assertSimpleTest {
            run("800", "8000", "1", "1,2,3,4,5,6", "7")
            assertThat(output()).contains(
                "[ERROR] 구입 금액은 1,000원 단위여야 합니다.",
                "8개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6, 7]"
            )
        }
    }

    @Test
    fun 예외_선택번호_잘못_입력() {
        assertSimpleTest {
            run("8000", "3", "1", "1,2,3,4,5,6", "7")
            assertThat(output()).contains(
                "[ERROR] 1 또는 2만 입력해야 합니다.",
                "8개를 구매했습니다.",
                "[1, 2, 3, 4, 5, 6, 7]"
            )
        }
    }

    override fun runMain() {
        main()
    }
}