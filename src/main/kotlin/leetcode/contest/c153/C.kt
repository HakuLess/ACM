package leetcode.contest.c153

class SolutionC {
    fun minimumCost(nums: IntArray, cost: IntArray, k: Int): Long {
        val n = nums.size

        val preNum = LongArray(n + 1)
        val preCost = LongArray(n + 1)
        for (i in 0 until n) {
            preNum[i + 1] = preNum[i] + nums[i]
            preCost[i + 1] = preCost[i] + cost[i]
        }

        // dp[i][j] 前i个元素 分割为j个子数组 的最小总代价
        val dp = Array(n + 1) { LongArray(n + 1) { Long.MAX_VALUE / 2 } }
        dp[0][0] = 0L

        for (i in 1..n) {
            for (j in 1..i) {
                for (l in (j - 1) until i) {
                    val diff = (preNum[i] + 1L * k * j) * (preCost[i] - preCost[l])
                    dp[i][j] = minOf(dp[i][j], dp[l][j - 1] + diff)
                }
            }
        }

        var ans = Long.MAX_VALUE / 2
        for (j in 1..n) {
            ans = minOf(ans, dp[n][j])
        }
        return ans
    }
}

