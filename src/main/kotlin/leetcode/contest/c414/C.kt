package leetcode.contest.c414

import java.util.*

class SolutionC {
    fun findMaximumScore(nums: List<Int>): Long {
        var cur = nums[0]
        var curI = 0
        var ans = 0L
        for (i in nums.indices) {
            if (nums[i] > cur) {
                ans += 1L * (i - curI) * cur
                curI = i
                cur = nums[i]
            }
        }
        ans += 1L * (nums.lastIndex - curI) * cur
        return ans
    }
}