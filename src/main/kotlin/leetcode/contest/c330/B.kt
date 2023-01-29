package leetcode.contest.c330

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionB()
    s.monkeyMove(500000003).print()
}

class SolutionB {
    fun monkeyMove(n: Int): Int {
        val mod = 1000000007L
        return ((quickPower(2L, n.toLong()) + mod - 2) % mod).toInt()
    }
}