package leetcode.normal

class Solution837 {
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        if (k == 0 || n >= k + maxPts) {
            return 1.0
        }
        val dp = DoubleArray(n + 1) { 0.0 }
        dp[0] = 1.0
        var sum = 1.0
        for (i in 1..n) {
            dp[i] = sum / maxPts
            if (i < k) {
                sum += dp[i]
            }
            if (i >= maxPts) {
                sum -= dp[i - maxPts]
            }
        }
        var ans = 0.0
        for (i in k until dp.size) {
            ans += dp[i]
        }
        return ans
    }
}