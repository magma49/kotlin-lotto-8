package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    private val LOTTO: Lotto = Lotto(listOf(4, 5, 6, 1, 2, 3))

    @Test
    fun 기능_번호출력() {
        assertSimpleTest {
            LOTTO.print()
        }
    }

    @Test
    fun 기능_보너스번호_포함여부() {
        assertSimpleTest {
            println(LOTTO.check(4))
        }
    }

    @Test
    fun 기능_1등() {
        assertSimpleTest {
            println(LOTTO.match(LOTTO, 7))
        }
    }

    @Test
    fun 기능_2등() {
        assertSimpleTest {
            println(LOTTO.match(Lotto(listOf(1, 2, 3, 4, 5, 7)), 6))
        }
    }

    @Test
    fun 기능_3등() {
        assertSimpleTest {
            println(LOTTO.match(Lotto(listOf(1, 2, 3, 4, 5, 7)), 8))
        }
    }

    @Test
    fun 예외_당첨번호_잘못된_개수_입력() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR] 6개의 숫자만 입력해야 합니다.")
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