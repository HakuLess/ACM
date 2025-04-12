package leetcode.contest.b154

class SolutionA {
    fun minOperations(nums: IntArray, k: Int): Int {
        return nums.sum() % k
    }
}