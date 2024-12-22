package leetcode.contest.c429

import utils.print

fun main() {
    val s = SolutionB()
    s.maxDistinctElements(intArrayOf(4, 4, 4, 4), 1).print()
    s.maxDistinctElements(intArrayOf(4, 4, 4, 4), 0).print()
}

class SolutionB {
    fun maxDistinctElements(nums: IntArray, k: Int): Int {

        nums.sort()

        var ans = 0
        var lst = Long.MIN_VALUE
        for (i in nums.indices) {
            val num: Long = nums[i].toLong()
            val min: Long = num - k
            val max: Long = num + k
            if (min > lst) {
                lst = min
                ans++
            } else if (max <= lst) {

            } else {
                lst++
                ans++
            }
        }

        return ans
    }
}