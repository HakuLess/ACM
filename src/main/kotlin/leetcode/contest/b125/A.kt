package leetcode.contest.b125

class SolutionA {
    fun minOperations(nums: IntArray, k: Int): Int {
        return nums.count { it < k }
    }
}