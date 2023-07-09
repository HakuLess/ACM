package leetcode.contest.c353

import utils.print

fun main() {
    val s = SolutionB()

    s.maximumJumps(intArrayOf(1, 3, 6, 4, 1, 2), 2).print()
}

class SolutionB {
    fun maximumJumps(nums: IntArray, target: Int): Int {
        val dp = IntArray(nums.size) { -1 }
        dp[0] = 0
        for (i in nums.indices) {
            if (dp[i] == -1) continue
            for (j in i + 1 until nums.size) {
                if (nums[j] - nums[i] <= target && nums[j] - nums[i] >= -target) {
                    dp[j] = maxOf(dp[j], dp[i] + 1)
                }
            }
        }
        return dp.last()
    }
}