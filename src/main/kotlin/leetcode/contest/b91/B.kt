package leetcode.contest.b91

import utils.print

fun main() {
    val s = SolutionB()
//    s.countGoodStrings(3, 3, 1, 1).print()
//    s.countGoodStrings(2, 3, 1, 2).print()
    // 873327137
    s.countGoodStrings(500, 500, 5, 2).print()
}

class SolutionB {
    fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
        val mod = 1000000007L
        val dp = LongArray(high + 1)
        dp[zero] = dp[zero] + 1
        dp[one] = dp[one] + 1
        for (i in 0..high) {
            if (i - zero in dp.indices)
                dp[i] += dp[i - zero]
            if (i - one in dp.indices)
                dp[i] += dp[i - one]
            dp[i] %= mod
        }
        dp.print()
        var ans = 0L
        for (j in low..high) {
            ans += dp[j]
            ans %= mod
        }
        return ans.toInt()
    }
}