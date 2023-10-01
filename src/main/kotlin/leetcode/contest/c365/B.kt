package leetcode.contest.c365

import utils.print

class SolutionB {
    fun maximumTripletValue(nums: IntArray): Long {
        val n = nums.size
        val preMax = IntArray(n)
        val sufMax = IntArray(n)
        // 左侧最大值
        for (i in nums.indices) {
            if (i == 0) preMax[i] = nums[i]
            else preMax[i] = maxOf(preMax[i - 1], nums[i])
        }

        // 右侧最小值
        for (i in nums.indices.reversed()) {
            if (i == nums.lastIndex) sufMax[i] = nums[i]
            else sufMax[i] = maxOf(sufMax[i + 1], nums[i])
        }

        var ans = 0L
        preMax.print()
        sufMax.print()
        for (i in 1 until nums.lastIndex) {
            ans = maxOf(ans, 1L * (preMax[i - 1] - nums[i]) * sufMax[i + 1])
        }
        return ans
    }
}