package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val purchase = getValidPurchase()
    print("\n")
    println(purchase.toString() + "개를 구매했습니다.")

    val win = choose()
    println(win)
}

fun getValidPurchase(): Int {
    while (true) {
        try {
            println("구입금액을 입력해 주세요.")
            return validPurchase()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validPurchase(): Int {
    val purchase: Int
    try {
        purchase = Console.readLine().toInt()
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 구입금액에 숫자만 입력해야 합니다.")
    }

    require(purchase > 0) { "[ERROR] 로또를 사셔야 합니다." }
    require(purchase % 1000 == 0) { "[ERROR] 구입 금액은 1,000원 단위여야 합니다." }

    return purchase / 1000
}

fun choose(): List<Int> {
    while (true) {
        try {
            println("당첨번호를 입력하시겠습니까? 입력을 하시려면 1, 입력을 안 하시려면 2를 입력해 주세요.")
            if (validChoose() == 1) {
                val win: List<Int> = getValidWin()
                val bonus: Int = getValidBonus()
                return win + bonus
            } else {
                return getRandomWin()
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validChoose(): Int {
    val choice: Int
    try {
        choice = Console.readLine().toInt()
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 1 또는 2만 입력해야 합니다.")
    }
    require(choice in 1..2) { "[ERROR] 1 또는 2만 입력해야 합니다." }
    return choice
}

fun getValidWin(): List<Int> {
    println("당첨 번호를 입력해 주세요.")
    val input: List<Int> = Console.readLine().split(",").map { it.toInt() }

    return input
}

fun getRandomWin(): List<Int> {
    val random = Randoms.pickUniqueNumbersInRange(1, 45, 7)
    return random.slice(0..5).sorted() + random[6]
}

fun getValidBonus(): Int {
    println("보너스 번호를 입력해 주세요.");
    val input = Console.readLine().toInt()

    return input
}