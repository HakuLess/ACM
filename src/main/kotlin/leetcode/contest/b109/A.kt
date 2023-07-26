package leetcode.contest.b109

class SolutionA {
    fun isGood(nums: IntArray): Boolean {
        if (nums.size == 1) return false
        nums.sort()
        val n = nums.size
        for (i in 0 until n - 1) {
            if (nums[i] != i + 1) {
                return false
            }
        }
        return nums[n - 1] == n - 1
    }
}