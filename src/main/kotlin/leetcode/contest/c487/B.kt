package leetcode.contest.c487

class SolutionB {
    fun finalElement(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return nums[0]
        return maxOf(nums[0], nums[n - 1])
    }
}