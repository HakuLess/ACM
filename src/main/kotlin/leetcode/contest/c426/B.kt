package leetcode.contest.c426

import utils.print

fun main() {
    val s = SolutionB()
    s.getLargestOutlier(intArrayOf(2, 3, 5, 10)).print()
}

class SolutionB {
    fun getLargestOutlier(nums: IntArray): Int {
        nums.sort()
        val totalSum = nums.sum()

        for (i in nums.indices.reversed()) {
            if ((totalSum -  nums[i]) % 2 != 0) continue
            val half = (totalSum -  nums[i]) / 2
            val v = nums[i]
            val c = if (half == v) 2 else 1
            if (nums.count { it == half } >= c) {
                return v
            }
        }
        return -1
    }
}