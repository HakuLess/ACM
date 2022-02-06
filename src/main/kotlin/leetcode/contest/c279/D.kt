package leetcode.contest.c279

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumTime("11000101").print()
    s.minimumTime("00000000111100000000").print()
}

class SolutionD {
    fun minimumTime(s: String): Int {
        val n = s.length
        val dp = IntArray(n + 1)
        dp[n] = 0
        for (i in s.indices.reversed()) {
            dp[i] = minOf(n - i, if (s[i] == '1') dp[i + 1] + 2 else dp[i + 1])
        }
        var ans = Int.MAX_VALUE
        for (i in 0 until n) {
            ans = minOf(ans, i + dp[i])
        }
        return ans
    }
}