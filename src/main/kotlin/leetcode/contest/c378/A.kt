package leetcode.contest.c378

class SolutionA {
    fun hasTrailingZeros(nums: IntArray): Boolean {
        return nums.count { it % 2 == 0 } > 1
    }
}