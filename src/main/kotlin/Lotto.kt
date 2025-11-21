package lotto

class Lotto(numbers: List<Int>) {

    private val numbers = numbers.sorted()

    init {
        require(numbers.size == 6) {
            "[ERROR] 6개의 숫자만 입력해야 합니다."
        }
        require(numbers.all { it in 1..45 }) {
            "[ERROR] 1 ~ 45의 숫자만 입력해야 합니다."
        }
        require(numbers.distinct().size == numbers.size) {
            "[ERROR] 서로 다른 숫자만 입력해야 합니다."
        }
    }

    fun print() {
        println(numbers)
    }

    fun checkBonus(bonus: Int): Boolean { //있으면 true, 없으면 false
        return numbers.find { it == bonus } != null
    }

    fun match(win: Lotto, bonus: Int) {

    }
}