package leetcode.contest.c350

import utils.print

fun main() {
    val s = SolutionA()
//    s.distanceTraveled(5, 10).print()
//    s.distanceTraveled(1, 2).print()
    s.distanceTraveled(13, 3).print()
}

class SolutionA {
    fun distanceTraveled(mainTank: Int, additionalTank: Int): Int {
        var a = mainTank
        var b = additionalTank
        var ans = a * 10
        while (a >= 5) {
            val c = a / 5
            ans += 10 * minOf(b, c)
            a %= 5
            a += minOf(b, c)
            b -= minOf(b, c)
        }
        return ans
    }
}