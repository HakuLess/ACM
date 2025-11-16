package leetcode.contest.c476

class SolutionA {
    fun maximizeExpressionOfThree(nums: IntArray): Int {
        nums.sortDescending()
        return nums[0] + nums[1] - nums.last()
    }
}