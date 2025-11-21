package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun 기능() {
        assertRandomUniqueNumbersInRangeTest(
            {
                Lotto(listOf())
            },
            mutableListOf<Int>()
        )
    }

    @Test
    fun 예외_당첨번호_범위밖_입력() {
        assertThatThrownBy { Lotto(listOf(1, 0, 3, 4, 5, 6)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR] 1 ~ 45의 숫자만 입력해야 합니다.")
    }

    @Test
    fun 예외_당첨번호_중복_입력() {
        assertThatThrownBy { Lotto(listOf(1, 1, 3, 4, 5, 6)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR] 서로 다른 숫자만 입력해야 합니다.")
    }
}