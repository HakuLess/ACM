package leetcode.contest.c450

class SolutionA {
    fun smallestIndex(nums: IntArray): Int {
        for (i in nums.indices) {
            val sum = nums[i].toString().map { it - '0' }.sum()
            if (sum == i) return i
        }
        return -1
    }
}