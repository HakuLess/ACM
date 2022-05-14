package leetcode.contest.b78

class SolutionB {
    fun waysToSplitArray(nums: IntArray): Int {
        var right = 0L
        for (i in nums.indices) {
            right += nums[i]
        }
        var left = 0L
        var ans = 0
        for (i in 0 until nums.lastIndex) {
            left += nums[i]
            right -= nums[i]
            if (left >= right) {
                ans++
            }
        }
        return ans
    }
}