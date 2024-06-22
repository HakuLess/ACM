package leetcode.contest.b133

class SolutionA {
    fun minimumOperations(nums: IntArray): Int {
        return nums.count { it % 3 != 0 }
    }
}