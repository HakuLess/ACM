package leetcode.contest.b158

import utils.print

fun main() {
    val s = SolutionB()
    s.maximumProfit(intArrayOf(1, 7, 9, 8, 2), 2).print()
    s.maximumProfit(intArrayOf(12, 16, 19, 19, 8, 1, 19, 13, 9), 3).print()
}

class SolutionB {
    fun maximumProfit(prices: IntArray, k: Int): Long {
        val n = prices.size

        val dp = Array(n) { Array(k + 1) { LongArray(3) { Long.MIN_VALUE / 2 } } }

        for (t in 0..k) {
            dp[0][t][0] = 0
            dp[0][t][1] = -prices[0].toLong()
            dp[0][t][2] = prices[0].toLong()
        }

        for (i in 1 until n) {
            for (t in 0..k) {
                dp[i][t][0] = dp[i - 1][t][0]
                if (t > 0) {
                    // 抹平之前买入、卖出的仓位
                    dp[i][t][0] = maxOf(dp[i][t][0], dp[i - 1][t][1] + prices[i])
                    dp[i][t][0] = maxOf(dp[i][t][0], dp[i - 1][t][2] - prices[i])
                }
                if (t > 0) {
                    // 新的一笔买入、卖出
                    dp[i][t][1] = maxOf(dp[i - 1][t][1], dp[i - 1][t - 1][0] - prices[i])
                    dp[i][t][2] = maxOf(dp[i - 1][t][2], dp[i - 1][t - 1][0] + prices[i])
                } else {
                    // 无操作
                    dp[i][t][1] = dp[i - 1][t][1]
                    dp[i][t][2] = dp[i - 1][t][2]
                }
            }
        }

        return (0..k).maxOf { dp[n - 1][it][0] }
    }
}