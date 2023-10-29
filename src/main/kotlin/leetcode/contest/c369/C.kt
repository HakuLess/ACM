package leetcode.contest.c369

import utils.print

fun main() {
    val s = SolutionC()
    s.minIncrementOperations(intArrayOf(0, 1, 3, 3), 5).print()
}

class SolutionC {
    fun minIncrementOperations(nums: IntArray, k: Int): Long {
        val n = nums.size
        val dp = LongArray(n)
        for (i in dp.indices) {
            if (i < 3) {
                dp[i] = maxOf(0L, k.toLong() - nums[i])
            } else {
                dp[i] = listOf(dp[i - 3], dp[i - 2], dp[i - 1]).minOrNull()!! + maxOf(0L, k.toLong() - nums[i])
            }
        }
//        dp.print()
        return listOf(dp[n - 3], dp[n - 2], dp[n - 1]).minOrNull()!!
    }
}