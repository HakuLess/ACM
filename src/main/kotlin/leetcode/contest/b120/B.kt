package leetcode.contest.b120

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionB()
    s.largestPerimeter(intArrayOf(1, 12, 1, 2, 5, 50, 3)).print()
}

class SolutionB {
    fun largestPerimeter(nums: IntArray): Long {
        nums.sort()
        val presum = nums.preSumArray(false)
//        presum.print()
        for (i in presum.indices.reversed()) {
            if (i - 1 in presum.indices && presum[i] < presum[i - 1] * 2) {
                return presum[i]
            }
        }
        return -1
    }
}