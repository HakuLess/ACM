package leetcode.contest.c354

class SolutionA {
    fun sumOfSquares(nums: IntArray): Int {
        val n = nums.size
        var ans = 0
        for (i in nums.indices) {
            if (n % (i + 1) == 0) {
                ans += nums[i] * nums[i]
            }
        }
        return ans
    }
}