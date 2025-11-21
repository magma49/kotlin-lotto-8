package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val purchase = getValidPurchase()
    print("\n")
    println(purchase.toString() + "개를 구매했습니다.")

    val lottos = makeLotto(purchase)
    val (win, bonus) = choose()
    win.print()
    println(bonus)
}

fun getValidPurchase(): Int {
    while (true) {
        try {
            return validPurchase()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validPurchase(): Int {
    val purchase: Int
    try {
        println("구입금액을 입력해 주세요.")
        purchase = Console.readLine().toInt()
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 구입금액에 숫자만 입력해야 합니다.")
    }

    require(purchase > 0) { "[ERROR] 로또를 사셔야 합니다." }
    require(purchase % 1000 == 0) { "[ERROR] 구입 금액은 1,000원 단위여야 합니다." }

    return purchase / 1000
}

fun choose(): Pair<Lotto, Int> {
    while (true) {
        try {
            return getValidChoose()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun getValidChoose(): Pair<Lotto, Int> {
    if (validChoose() == 1) {
        val win: Lotto = getValidWin()
        return win to getValidBonus(win)
    } else {
        return getRandomWin()
    }
}

fun validChoose(): Int {
    val choice: Int
    try {
        println("당첨번호를 입력하시겠습니까? 입력을 하시려면 1, 입력을 안 하시려면 2를 입력해 주세요.")
        choice = Console.readLine().toInt()
        print("\n")
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 1 또는 2만 입력해야 합니다.")
    }
    require(choice in 1..2) { "[ERROR] 1 또는 2만 입력해야 합니다." }
    return choice
}

fun getValidWin(): Lotto {
    while (true) {
        try {
            return Lotto(validWin())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validWin(): List<Int> {
    val win: List<Int>
    try {
        println("당첨 번호를 입력해 주세요.")
        win = Console.readLine().split(",").map { it.toInt() }
        print("\n")
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
    }
    return win
}

fun getRandomWin(): Pair<Lotto, Int> {
    val random = Randoms.pickUniqueNumbersInRange(1, 45, 7)
    return Lotto(random.slice(0..5)) to random[6]
}

fun getValidBonus(win: Lotto): Int {
    while (true) {
        try {
            return validBonus(win)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

}

fun validBonus(win: Lotto): Int {
    val bonus: Int
    try {
        println("보너스 번호를 입력해 주세요.")
        bonus = Console.readLine().toInt()
        print("\n")
    } catch (e: NumberFormatException) {
        throw java.lang.IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
    }
    require(!win.checkBonus(bonus)) { "[ERROR] 보너스 번호는 당첨번호들과 달라야 합니다." }
    return bonus
}

fun makeLotto(purchase: Int): Array<Lotto> {
    val lottos = Array(purchase) { i ->
        Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6))
    }
    lottos.forEach { it.print() }
    print("\n")
    return lottos
}