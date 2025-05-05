package leetcode.normal


class Solution790 {
    fun numTilings(n: Int): Int {
        val mod = 1_000_000_007L
        // 0 空
        // 1 上1
        // 2 下1
        // 3 满
        val dp = Array(n + 1) { LongArray(4) }
        dp[0][3] = 1L
        for (i in 1..n) {
            dp[i][0] = dp[i - 1][3] % mod
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod
            dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]) % mod
        }
        return (dp[n][3] % mod).toInt()
    }
}