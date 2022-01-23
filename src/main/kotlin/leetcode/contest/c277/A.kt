package leetcode.contest.c277

class SolutionA {
    fun countElements(nums: IntArray): Int {
//        val max = nums.max()!!
        val max = nums.maxOrNull()!!
//        val min = nums.min()!!
        val min = nums.maxOrNull()!!
        return nums.count { it != max && it != min }
    }
}