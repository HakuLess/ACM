package leetcode.contest.c441

class SolutionA {
    fun maxSum(nums: IntArray): Int {
        if (nums.all { it < 0 }) {
            return nums.maxOrNull()!!
        }
        return nums.toHashSet().filter { it > 0 }.sum()
    }
}