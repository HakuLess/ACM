package leetcode.contest.c357

import utils.print

fun main() {
    val s = SolutionB()
    s.canSplitArray(listOf(2, 3, 3, 2, 3), 6).print()
}

class SolutionB {
    fun canSplitArray(nums: List<Int>, m: Int): Boolean {
        if (nums.size <= 2) return true
        for (i in 0 until nums.lastIndex) {
            if (nums[i] + nums[i + 1] >= m) return true
        }
        return false
    }
}