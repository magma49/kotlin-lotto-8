package lotto

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun 기능() {
        assertSimpleTest {
            Lotto(listOf())
        }
    }

    @Test
    fun 기능_번호출력() {
        assertSimpleTest {
            Lotto(listOf(1, 2, 3, 4, 5, 6)).print()
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