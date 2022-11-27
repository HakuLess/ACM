package leetcode.contest.c321

import utils.print

fun main() {
    val s = SolutionA()
    s.pivotInteger(8).print()
}

class SolutionA {
    fun pivotInteger(n: Int): Int {
        val sum = (1 + n) * n / 2
        var cur = 0
        for (i in 1..n) {
            cur += i
            if (cur == (sum + i) / 2 && (sum + i) % 2 == 0) {
                return i
            }
        }
        return -1
    }
}