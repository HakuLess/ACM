package leetcode.contest.c317

class SolutionA {
    fun averageValue(nums: IntArray): Int {
        val n = nums.count { it % 6 == 0 }
        if (n == 0) return 0
        return nums.filter { it % 6 == 0 }.sum() / n
    }
}