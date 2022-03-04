package leetcode.normal

import utils.getStackIndex
import utils.print

fun main() {
    val s = Solution2104()
    s.subArrayRanges(intArrayOf(4, -2, -3, 4, 1)).print()
    s.subArrayRanges(intArrayOf(1, 2, 3)).print()
    s.subArrayRanges(intArrayOf(1, 3, 3)).print()
}

// 单调栈
class Solution2104 {
    fun subArrayRanges(nums: IntArray): Long {
        // 左侧比自己小的Index
        val leftMin = nums.getStackIndex(reversed = false, isMin = true, strict = false)
        val leftMax = nums.getStackIndex(reversed = false, isMin = false, strict = false)
        val rightMin = nums.getStackIndex(reversed = true, isMin = true, strict = true)
        val rightMax = nums.getStackIndex(reversed = true, isMin = false, strict = true)
        var min = 0L
        var max = 0L
        for (i in nums.indices) {
            min += 1L * (rightMin[i] - i) * (i - leftMin[i]) * nums[i]
            max += 1L * (rightMax[i] - i) * (i - leftMax[i]) * nums[i]
        }
        return max - min
    }
}