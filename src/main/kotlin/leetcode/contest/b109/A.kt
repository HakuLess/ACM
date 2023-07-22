package leetcode.contest.b109

class SolutionA {
    fun isGood(nums: IntArray): Boolean {
        if (nums.size == 1) return false
        nums.sort()
        val n = nums.size
        for (i in 0 until n - 2) {
            if (nums[i] != i + 1) {
                return false
            }
        }
        if (nums[n - 1] != n - 1) return false
        return true
    }
}