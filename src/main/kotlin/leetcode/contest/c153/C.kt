package leetcode.contest.c153

import utils.ConvexHullTrick
import utils.print

fun main() {
    val s = SolutionC()
    s.minimumCost(intArrayOf(4, 8, 5, 1, 14, 2, 2, 12, 1), intArrayOf(7, 2, 8, 4, 2, 2, 1, 1, 2), 7).print()
}

class SolutionC {
    fun minimumCost(nums: IntArray, cost: IntArray, k: Int): Long {
        val n = nums.size

        val preNum = LongArray(n + 1)
        val preCost = LongArray(n + 1)
        for (i in 0 until n) {
            preNum[i + 1] = preNum[i] + nums[i]
            preCost[i + 1] = preCost[i] + cost[i]
        }

        val inf = Long.MAX_VALUE / 2
        // dp[i][j]：将前 i 个元素分割为 j 个子数组的最小总代价
        val dp = Array(n + 1) { LongArray(n + 1) { inf } }
        dp[0][0] = 0L

        for (j in 1..n) {
            // 单调凸包优化
            val cht = ConvexHullTrick()
            for (l in (j - 1) until n) {
                if (dp[l][j - 1] < inf) {
                    cht.addLine(-preCost[l].toDouble(), dp[l][j - 1].toDouble())
                }
            }
            for (i in j..n) {
                val query = cht.query(preNum[i] + k.toLong() * j).toLong()
                dp[i][j] = (preNum[i] + k.toLong() * j) * preCost[i] + query
            }
        }

        var ans = inf
        for (j in 1..n) {
            ans = minOf(ans, dp[n][j])
        }
        return ans
    }
}