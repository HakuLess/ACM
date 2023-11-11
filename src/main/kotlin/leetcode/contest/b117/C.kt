package leetcode.contest.b117

import utils.print

fun main() {
    val s = SolutionC()
    s.stringCount(4).print()
//    s.stringCount(5).print()
//    s.stringCount(10).print()
}

class SolutionC {
    fun stringCount(n: Int): Int {
        if (n < 4) return 0
        val mod = 1000000007L
        val dp = LongArray(n + 1)
        dp[0] = 1L
        for (i in 1..n) {
            dp[i] = dp[i - 1] * 26L
        }
        dp.print()

        // 无L方案数
        val dp1 = LongArray(n + 1)
        dp1[0] = 1L
        for (i in 1..n) {
            dp1[i] = dp1[i - 1] * 25L
        }
        dp1.print()

        // 无L 也 无T
        val dp2 = LongArray(n + 1)
        dp2[0] = 1L
        for (i in 1..n) {
            dp2[i] = dp2[i - 1] * 24L
        }
        dp2.print()
        return 0
    }
}