package leetcode.contest.c439

class SolutionC {
    fun maxSum(nums: IntArray, k: Int, m: Int): Int {
        val n = nums.size

        val prefix = IntArray(n + 1)
        for (i in 0 until n) {
            prefix[i + 1] = prefix[i] + nums[i]
        }

        val dp = Array(k + 1) { IntArray(n + 1) { Int.MIN_VALUE / 2 } }
        for (i in 0..n) {
            dp[0][i] = 0
        }
        for (j in 0 until k) {
            val best = IntArray(n + 1)
            best[0] = dp[j][0] - prefix[0]
            for (i in 1..n) {
                best[i] = maxOf(best[i - 1], dp[j][i] - prefix[i])
            }
            for (i in m..n) {
                val candidate = prefix[i] + best[i - m]
                dp[j + 1][i] = if (i == m) candidate else maxOf(dp[j + 1][i - 1], candidate)
            }
        }
        return dp[k][n]
    }
}