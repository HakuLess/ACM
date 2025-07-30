package leetcode.contest.c263

import utils.print

fun main() {
    val s = Solution5904()
    s.countMaxOrSubsets(intArrayOf(88)).print()
}

class Solution5904 {
    fun countMaxOrSubsets(nums: IntArray): Int {
        val n = nums.size
        var high = -1
        var ans = 0
        // 状态压缩，取or的最大值
        for (mask in 0 until (1 shl n)) {
            var cur = 0
            for (i in 0 until n) {
                if (mask and (1 shl i) != 0) {
                    cur = cur or nums[i]
                }
            }
            if (cur > high) {
                high = cur
                ans = 1
            } else if (cur == high) {
                ans++
            }
        }
        return ans
    }
}