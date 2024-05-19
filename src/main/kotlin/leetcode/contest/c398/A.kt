package leetcode.contest.c398

import kotlin.math.abs

class SolutionA {
    fun isArraySpecial(nums: IntArray): Boolean {
        for (i in nums.indices) {
            if (i == 0) continue
            if (abs(nums[i] - nums[i - 1]) % 2 == 0) return false
        }
        return true
    }
}