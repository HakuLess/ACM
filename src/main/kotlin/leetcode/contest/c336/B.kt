package leetcode.contest.c336

import utils.preSumArray

class SolutionB {
    fun maxScore(nums: IntArray): Int {
        nums.sortDescending()
        val pre = nums.preSumArray(false)
        return pre.count { it > 0 }
    }
}