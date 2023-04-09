package leetcode.contest.c340

import utils.biMin
import utils.print

fun main() {
    val s = SolutionC()
    s.minimizeMax(intArrayOf(10, 1, 2, 7, 1, 3), 2).print()
    s.minimizeMax(intArrayOf(4, 2, 1, 2), 1).print()
}

class SolutionC {
    fun minimizeMax(nums: IntArray, p: Int): Int {
        nums.sort()
        return biMin(0L, Long.MAX_VALUE / 2) {
            var cur = 0
            var skip = false
            for (i in 1 until nums.size) {
                if (skip) {
                    skip = false
                    continue
                }
                if (nums[i] - nums[i - 1] <= it) {
                    cur++
                    skip = true
                }
            }
            cur >= p
        }.toInt()
    }

}