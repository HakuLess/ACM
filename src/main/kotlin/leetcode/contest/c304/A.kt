package leetcode.contest.c304

class SolutionA {
    fun minimumOperations(nums: IntArray): Int {
        return nums.distinct().filter { it != 0 }.size
    }
}