package leetcode.contest.c333

import utils.countOne
import utils.print

fun main() {
    val s = SolutionB()
    s.minOperations(39).print()
    s.minOperations(54).print()
    s.minOperations(27).print()
}

class SolutionB {
    fun minOperations(n: Int): Int {
        if (n.countOne() == 1) return 1
        val x = n - (n and n - 1)
        return minOf(minOperations(n - x), minOperations(n + x)) + 1
    }
}