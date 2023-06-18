package leetcode.contest.c350

import utils.print

fun main() {
    val s = SolutionD()
    s.paintWalls(intArrayOf(1, 2, 3, 2), intArrayOf(1, 2, 3, 2)).print()
    s.paintWalls(intArrayOf(2, 3, 4, 2), intArrayOf(1, 1, 1, 1)).print()
}

// 背包
class SolutionD {
    fun paintWalls(cost: IntArray, time: IntArray): Int {
        val n = cost.size
        // 刷N面墙，最小Cost
        val dp = IntArray(n + 1) { Int.MAX_VALUE / 2 }
        dp[0] = 0

        for (i in 0 until n)
        // 第i面墙由付费的刷
            for (j in n downTo 0) {
                // 免费的可以多刷 time[i] + 1面墙
                dp[j] = minOf(dp[j], dp[maxOf(j - time[i] - 1, 0)] + cost[i])
            }

        return dp[n]
    }
}