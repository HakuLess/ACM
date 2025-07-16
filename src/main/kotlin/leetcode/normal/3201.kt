package leetcode.normal

class Solution3201 {
    fun maximumLength(nums: IntArray): Int {
        // 奇偶性
        val dp = IntArray(4)
        for (i in nums.indices) {
            if (nums[i] % 2 == 0) {
                dp[0]++
                if (dp[2] % 2 == 0) {
                    dp[2]++
                }
                if (dp[3] % 2 != 0) {
                    dp[3]++
                }
            } else {
                dp[1]++
                if (dp[2] % 2 != 0) {
                    dp[2]++
                }
                if (dp[3] % 2 == 0) {
                    dp[3]++
                }
            }
        }

        return dp.maxOrNull()!!
    }
}