package leetcode.contest.b89

import utils.biMin
import utils.print

fun main() {
    val s = SolutionC()
    s.minimizeArrayValue(intArrayOf(3, 7, 1, 6)).print()
    s.minimizeArrayValue(intArrayOf(3, 8, 1, 6)).print()
    s.minimizeArrayValue(intArrayOf(10, 1)).print()
    s.minimizeArrayValue(intArrayOf(6, 9, 3, 8, 14)).print()
}

class SolutionC {
    fun minimizeArrayValue(nums: IntArray): Int {
        fun check(max: Int): Boolean {
            var left = 0L
            for (i in nums.indices.reversed()) {
                if (nums[i] > max) {
                    left += nums[i] - max
                } else {
                    left -= max - nums[i]
                }
                left = maxOf(left, 0)
            }
            return left == 0L
        }
        return biMin(l = 0, r = Int.MAX_VALUE.toLong()) {
            check(it.toInt())
        }.toInt()
    }
}