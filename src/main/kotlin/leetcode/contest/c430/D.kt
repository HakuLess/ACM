package leetcode.contest.c430

import utils.print

fun main() {
    val s = SolutionD()
    s.countGoodArrays(5, 2, 0).print()
}

class SolutionD {
    fun countGoodArrays(n: Int, m: Int, k: Int): Int {
        val MOD = 1000000007
        if (k > n - 1) {
            return 0
        }

        // 使用两个一维数组来模拟滚动数组
        var dp = LongArray(k + 1) { 0 }
        var prevDp = LongArray(k + 1) { 0 }

        // 初始化第一行，也就是 n = 1 的情况
        prevDp[0] = m.toLong()

        for (i in 2..n) {
            for (j in 0..minOf(i - 1, k)) {
                dp[j] = 0
                if (j > 0) {
                    dp[j] = (dp[j] + prevDp[j - 1]) % MOD
                }
                dp[j] = (dp[j] + (prevDp[j] * (m - 1).toLong()) % MOD) % MOD
            }
            // 更新prevDp
            val temp = prevDp
            prevDp = dp
            dp = temp

        }
        return prevDp[k].toInt()
    }
}