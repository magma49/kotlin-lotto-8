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

    fun check(number: Int): Boolean { //있으면 true, 없으면 false
        return numbers.find { it == number } != null
    }

    fun match(win: Lotto, bonus: Int): Int {
        var count = numbers.count { win.check(it) }
        if (count == 6) ++count
        if (count == 5 && check(bonus))
            ++count
        return count
    }
}