package leetcode.contest.c260

import utils.print
import utils.toGrid

fun main() {
    val s = Solution5882()
//    s.gridGame("[[2,5,4],[1,5,1]]".toGrid()).print()
    s.gridGame("[[20,3,20,17,2,12,15,17,4,15],[20,10,13,14,15,5,2,3,14,3]]".toGrid()).print()
}

class Solution5882 {
    fun gridGame(grid: Array<IntArray>): Long {
        val n = grid.size
        val m = grid[0].size
        val dp = Array<LongArray>(n) { LongArray(m) }
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j].toLong()
                } else if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j]
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j]
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
                }
            }
        }
        dp.print()
        fun dfs(i: Int, j: Int) {
            if (i - 1 >= 0 && dp[i][j] - grid[i][j] == dp[i - 1][j]) {
                dfs(i - 1, j)
            } else if (j - 1 >= 0 && dp[i][j] - grid[i][j] == dp[i][j - 1]) {
                dfs(i, j - 1)
            }
            grid[i][j] = 0
        }

        dfs(n - 1, m - 1)
        grid.print()
        val ans = Array<LongArray>(n) { LongArray(m) }
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i == 0 && j == 0) {
                    ans[i][j] = grid[i][j].toLong()
                } else if (i == 0 && j != 0) {
                    ans[i][j] = ans[i][j - 1] + grid[i][j]
                } else if (j == 0) {
                    ans[i][j] = ans[i - 1][j] + grid[i][j]
                } else {
                    ans[i][j] = maxOf(ans[i - 1][j], ans[i][j - 1]) + grid[i][j]
                }
            }
        }
//        ans.print()
        return ans.last().last()
    }
}