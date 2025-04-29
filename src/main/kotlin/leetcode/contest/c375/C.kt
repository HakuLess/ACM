package leetcode.contest.c375

import utils.print

fun main() {
    val s = SolutionC()
    s.countSubarrays(intArrayOf(1, 3, 2, 3, 3), 2).print()
}

class SolutionC {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val maxItem = nums.maxOrNull()!!
        var ans = 0L
        var c = 0
        var l = 0
        for (item in nums) {
            if (item == maxItem) {
                c++
            }
            while (c >= k) {
                if (nums[l++] == maxItem) c--
            }
            ans += l
        }
        return ans
    }
}