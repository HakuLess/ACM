package leetcode.contest.c315

class SolutionA {
    fun findMaxK(nums: IntArray): Int {
        var ans = -1
        val set = nums.toSet()
        for (i in nums.indices) {
            if (nums[i] > 0 && -nums[i] in set) {
                ans = maxOf(ans, nums[i])
            }
        }
        return ans
    }
}