package leetcode.contest.c433


// TODO Not Finished
class SolutionC {
    fun minCost(n: Int, cost: Array<IntArray>): Long {
        // 初始化一个 dp 数组，dp[i][j] 表示第 i 个房屋涂成颜色 j+1 的最小成本
        val dp = Array(n) { LongArray(3) { Long.MAX_VALUE } }

        // 初始化第一个房屋的涂色成本
        for (j in 0 until 3) {
            dp[0][j] = cost[0][j].toLong()
        }

        // 动态规划，遍历每一个房屋
        for (i in 1 until n) {
            for (color in 0 until 3) {
                // 对于每一个房屋 i 和颜色 color，我们选择前一个房屋的所有颜色来转移
                for (prevColor in 0 until 3) {
                    if (color != prevColor) { // 相邻房屋不能涂成相同颜色
                        dp[i][color] = minOf(dp[i][color], dp[i - 1][prevColor] + cost[i][color])
                    }
                }
            }
        }

        // 考虑对称性约束，更新涂色的最小成本
        for (i in 0 until n / 2) {
            for (color in 0 until 3) {
                for (symColor in 0 until 3) {
                    // 当前房屋和对称房屋的颜色不能相同
                    if (color != symColor) {
                        dp[i][color] = minOf(dp[i][color], dp[n - i - 1][symColor] + cost[i][color])
                    }
                }
            }
        }

        // 返回所有可能的涂色方案中的最小值
        return dp[n - 1].minOrNull() ?: 0L
    }
}