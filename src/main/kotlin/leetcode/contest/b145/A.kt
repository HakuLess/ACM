package leetcode.contest.b145

class SolutionA {
    fun minOperations(nums: IntArray, k: Int): Int {
        val set = nums.toHashSet()
        val min = set.minOrNull()!!
        if (min < k) return -1
        if (min > k) return set.size
        return set.size - 1
    }
}