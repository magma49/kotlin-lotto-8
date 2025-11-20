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
    println("구입금액을 입력해 주세요.")
    val input = Console.readLine().toInt() / 1000

    return input
}

fun choose(): List<Int> {
    println("당첨번호를 입력하시겠습니까? 입력을 하실 거면 1, 랜덤을 원하실 경우 2를 입력해 주세요.")
    val input = Console.readLine().toInt()
    if (input == 1) {
        val win: List<Int> = getValidWin()
        val bonus: Int = getValidBonus()
        return win + bonus
    } else {
        return getRandomWin()
    }
}

fun getValidWin(): List<Int> {
    println("당첨 번호를 입력해 주세요.")
    val input: List<Int> = Console.readLine().split(",").map { it.toInt() }

    return input
}

fun getRandomWin(): List<Int> {
    val random = Randoms.pickUniqueNumbersInRange(1, 45, 7)
    println(random)
    return random.slice(0..5).sorted() + random[6]
}

fun getValidBonus(): Int {
    println("보너스 번호를 입력해 주세요.");
    val input = Console.readLine().toInt()

    return input
}