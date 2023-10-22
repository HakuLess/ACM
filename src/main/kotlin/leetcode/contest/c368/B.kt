package leetcode.contest.c368

class SolutionB {
    fun minimumSum(nums: IntArray): Int {
        val n = nums.size
        val left = IntArray(n)
        val right = IntArray(n)
        for (i in nums.indices) {
            if (i == 0) {
                left[i] = nums[i]
            } else {
                left[i] = minOf(nums[i], left[i - 1])
            }
        }
        for (i in nums.indices.reversed()) {
            if (i == nums.lastIndex) {
                right[i] = nums[i]
            } else {
                right[i] = minOf(nums[i], right[i + 1])
            }
        }
        var ans = Int.MAX_VALUE
        for (i in 1 until nums.lastIndex) {
            if (nums[i] > left[i - 1] && nums[i] > right[i + 1]) {
                ans = minOf(ans, nums[i] + left[i - 1] + right[i + 1])
            }
        }
        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}