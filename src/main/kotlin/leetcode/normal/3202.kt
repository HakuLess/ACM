package leetcode.normal

class Solution3202 {
    fun maximumLength(nums: IntArray, k: Int): Int {
        val dp = Array<IntArray>(k) { IntArray(k) }
        var ans = 0
        for (i in nums.indices) {
            val tmp = nums[i] % k
            for (prev in 0 until k) {
                dp[prev][tmp] = dp[tmp][prev] + 1
                ans = maxOf(ans, dp[prev][tmp])
            }
        }
        return ans
    }
}