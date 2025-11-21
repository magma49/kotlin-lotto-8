package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.all { it in 1..45 }) {
            "[ERROR] 1 ~ 45의 숫자만 입력해야 합니다."
        }

        require(numbers.distinct().size == numbers.size) {
            "[ERROR] 서로 다른 숫자만 입력해야 합니다."
        }
    }

    fun match(win: Lotto, bonus: Int) {

    }
}