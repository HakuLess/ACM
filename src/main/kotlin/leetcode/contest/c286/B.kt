package leetcode.contest.c286

import utils.print

fun main() {
    val s = SolutionB()
    s.minDeletion(intArrayOf(1, 1, 2, 3, 5)).print()
    s.minDeletion(intArrayOf(1, 1, 2, 2, 3, 3)).print()
}

class SolutionB {
    fun minDeletion(nums: IntArray): Int {
        var l = -1
        var cur = 0
        var ans = 0
        for (i in nums.indices) {
            if (cur % 2 != 0 && nums[i] == l) {
                ans++
            } else {
                cur++
                l = nums[i]
            }
        }
        if ((nums.size - ans) % 2 != 0) return ans + 1
        return ans
    }
}