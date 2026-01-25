package leetcode.contest.c486

class SolutionA {
    fun minimumPrefixLength(nums: IntArray): Int {
        val n = nums.size
        var i = n - 1
        while (i > 0 && nums[i - 1] < nums[i]) {
            i--
        }
        return i
    }
}