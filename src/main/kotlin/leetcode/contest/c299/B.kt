package leetcode.contest.c299

import utils.print

fun main() {
    val s = SolutionB()
    // 25
    s.countHousePlacements(3).print()
}

class SolutionB {
    fun countHousePlacements(n: Int): Int {
        val mod = 1000000007L
        if (n == 1) return 4
        val dp = LongArray(n + 1)
        dp[0] = 1L
        dp[1] = 2L
        for (i in 2 until n + 1) {
            dp[i] = dp[i - 1] + dp[i - 2]
            dp[i] %= mod
        }
        return ((dp.last() * dp.last()) % mod).toInt()
    }
}