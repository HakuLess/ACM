package leetcode.contest.c451

import utils.print

fun main() {
    val s = SolutionA()
    s.minCuttingCost(6, 5, 5).print()
    s.minCuttingCost(4, 4, 6).print()
}

class SolutionA {
    fun minCuttingCost(n: Int, m: Int, k: Int): Long {
        var ans = 0L
        if (n > k) {
            ans += 1L * k * (n - k)
        }
        if (m > k) {
            ans += 1L * k * (m - k)
        }
        return ans
    }
}