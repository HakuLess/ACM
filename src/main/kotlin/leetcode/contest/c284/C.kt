package leetcode.contest.c284

import utils.print

fun main() {
    val s = SolutionC()
    s.maximumTop(intArrayOf(99, 95, 68, 24, 18), 69).print()
    s.maximumTop(intArrayOf(2), 3).print()
}

class SolutionC {
    fun maximumTop(nums: IntArray, k: Int): Int {
        if (k > nums.size) {
            val left = k - nums.size
            val max = nums.maxOrNull()!!
//            val max = nums.max()!!
            if (nums.indexOf(max) == nums.lastIndex && left % 2 == 0 && nums.size == 1) {
                return -1
            } else {
                return max
            }
        }
        var max = -1
        for (i in 0 until k - 1) {
            max = maxOf(max, nums[i])
        }
        if (k in nums.indices) {
            max = maxOf(max, nums[k])
        }
        return max
    }
}