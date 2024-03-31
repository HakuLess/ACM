package leetcode.contest.c391

import utils.print

fun main() {
    val s = SolutionB()
    s.maxBottlesDrunk(13, 6).print()
    s.maxBottlesDrunk(10, 3).print()
}

class SolutionB {
    fun maxBottlesDrunk(numBottles: Int, numExchange: Int): Int {
        var a = numBottles
        var b = 0
        var ans = 0
        var exchange = numExchange
        while (a > 0) {
            // 喝光满瓶
            ans += a
            b += a
            a = 0

            if (b >= exchange) {
                b -= exchange
                a++
                exchange++
            }
            println("cur a is $a b is $b with $ans")
        }
        return ans
    }
}