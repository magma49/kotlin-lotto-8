package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val purchase: Int = getValidPurchase()
    print("\n")
    println(purchase.toString() + "개를 구매했습니다.")

    val lottos: Array<Lotto> = makeLotto(purchase)
    val (win: Lotto, bonus: Int) = choose()

    val winning = IntArray(5)
    for (lotto in lottos) {
        val count: Int = lotto.match(win, bonus)
        if (count > 2)
            ++winning[count - 3]
    }
    printWinning(winning, purchase)
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
        throw IllegalArgumentException("[ERROR] 구입금액에 숫자만 입력해야 합니다.")
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
        throw IllegalArgumentException("[ERROR] 1 또는 2만 입력해야 합니다.")
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
        throw IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
    }
    return win
}

fun getRandomWin(): Pair<Lotto, Int> {
    val random: List<Int> = Randoms.pickUniqueNumbersInRange(1, 45, 7)
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
        throw IllegalArgumentException("[ERROR] 숫자만 입력해야 합니다.")
    }
    require(!win.check(bonus)) { "[ERROR] 보너스 번호는 당첨번호들과 달라야 합니다." }
    return bonus
}

fun makeLotto(purchase: Int): Array<Lotto> {
    val lottos: Array<Lotto> = Array(purchase) { i ->
        Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6))
    }
    lottos.forEach { it.print() }
    print("\n")
    return lottos
}

enum class Rank(val index: Int, val prize: Int, val print: String) {
    FIFTH(0, 5, "3개 일치"),
    FOURTH(1, 50, "4개 일치"),
    THIRD(2, 1_500, "5개 일치"),
    SECOND(3, 30_000, "5개 일치, 보너스 볼 일치"),
    FIRST(4, 2_000_000, "6개 일치");

    fun getPrint(count: Int): String {
        return "$print (%,d원) - ${count}개".format(prize * 1000)
    }

    companion object {
        fun getPrize(index: Int, count: Int): Int {
            val rank: Rank = entries.find { it.index == index } ?: FIFTH
            println(rank.getPrint(count))
            return rank.prize * count
        }
    }
}

fun printWinning(winning: IntArray, purchase: Int) {
    println("당첨 통계")
    var prize = 0
    for ((index, count) in winning.withIndex()) {
        prize += Rank.getPrize(index, count)
    }
    println("총 수익률은 %.1f%%입니다.".format(prize.toDouble() * 100 / purchase))
}
