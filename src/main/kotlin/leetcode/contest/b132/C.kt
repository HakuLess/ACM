package leetcode.contest.b132

class SolutionC {
    fun maximumLength(nums: IntArray, k: Int): Int {
        val n = nums.size
        val dp = Array(n) { IntArray(k + 1) }

        for (i in 0 until n) {
            for (j in 0..k) {
                dp[i][j] = 1 // 每个元素单独成为子序列
                for (l in 0 until i) {
                    if (nums[i] == nums[l]) {
                        dp[i][j] = maxOf(dp[i][j], dp[l][j] + 1)
                    } else if (j > 0) {
                        dp[i][j] = maxOf(dp[i][j], dp[l][j - 1] + 1)
                    }
                }
            }
        }

        var result = 0
        for (i in 0 until n) {
            for (j in 0..k) {
                result = maxOf(result, dp[i][j])
            }
        }

        return result
    }
}