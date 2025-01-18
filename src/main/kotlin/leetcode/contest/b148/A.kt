package leetcode.contest.b148

import kotlin.math.abs

class SolutionA {
    fun maxAdjacentDistance(nums: IntArray): Int {
        var maxDiff = 0
        for (i in nums.indices) {
            val nextIndex = (i + 1) % nums.size
            val diff = abs(nums[i] - nums[nextIndex])
            maxDiff = maxOf(maxDiff, diff)
        }
        return maxDiff
    }
}