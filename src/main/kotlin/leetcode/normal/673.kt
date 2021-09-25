package leetcode.normal

import utils.print

fun main() {
    val s = Solution673()
//    s.findNumberOfLIS(intArrayOf(2, 2, 2, 2, 2)).print()
    s.findNumberOfLIS(intArrayOf(1, 3, 5, 4, 7)).print()
}

// 最长递增子序列 的 长度 及 个数
class Solution673 {
    fun findNumberOfLIS(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n) { 1 }
        val cnt = IntArray(n) { 1 }
        var max = 0
        for (i in nums.indices) {
            for (j in i - 1 downTo 0) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j]
                    } else if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1
                        cnt[i] = cnt[j]
                    }
                }
            }
            max = maxOf(max, dp[i])
        }
        var ans = 0
        for (i in dp.indices) {
            if (dp[i] == max) ans += cnt[i]
        }
        return ans
    }
}