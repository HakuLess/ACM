package leetcode.contest.c356

import utils.print

fun main() {
    val s = SolutionB()
    s.countCompleteSubarrays(intArrayOf(1, 3, 1, 2, 2)).print()
    s.countCompleteSubarrays(intArrayOf(5, 5, 5, 5)).print()
}

class SolutionB {
    fun countCompleteSubarrays(nums: IntArray): Int {
        val t = nums.toHashSet().size
        var ans = 0
        for (i in nums.indices) {
            val set = hashSetOf<Int>(nums[i])
            for (j in i until nums.size) {
                set.add(nums[j])
                if (set.size == t) {
                    ans++
                }
            }
        }
        return ans
    }
}