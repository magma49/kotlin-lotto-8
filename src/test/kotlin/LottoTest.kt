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
    fun 예외() {
        assertThatThrownBy { Lotto(listOf()) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}