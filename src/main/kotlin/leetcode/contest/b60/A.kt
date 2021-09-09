package leetcode.contest.b60

import utils.print

fun main() {
    val s = Solution5846()
    s.findMiddleIndex(intArrayOf(2, 3, -1, 8, 4)).print()
}

class Solution5846 {
    fun findMiddleIndex(nums: IntArray): Int {
        var left = 0
        var right = nums.sum()
        for (i in nums.indices) {
            right -= nums[i]
            if (left == right) return i
            left += nums[i]
        }
        return -1
    }
}