package leetcode.contest.c349

class SolutionA {
    fun findNonMinOrMax(nums: IntArray): Int {
//        val max = nums.max()!!
        val max = nums.maxOrNull()!!
//        val min = nums.min()!!
        val min = nums.minOrNull()!!
        return nums.firstOrNull { it != max && it != min } ?: -1
    }
}