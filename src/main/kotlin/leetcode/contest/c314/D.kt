package leetcode.contest.c314

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.numberOfPaths("[[5,2,4],[3,0,5],[0,7,2]]".toGrid(), 3).print()
}

class SolutionD {
    fun numberOfPaths(grid: Array<IntArray>, k: Int): Int {
        val mod = 1000000007L
        val n = grid.size
        val m = grid[0].size
        val dp = Array<Array<LongArray>>(n) { Array(m) { LongArray(k) } }
        for (i in 0 until n) {
            for (j in 0 until m) {
                for (t in 0 until k) {
                    if (i == 0 && j == 0) {
                        dp[i][j][grid[i][j] % k] = 1
                    } else if (i == 0) {
                        dp[i][j][(grid[i][j] + t) % k] = dp[i][j - 1][t]
                    } else if (j == 0) {
                        dp[i][j][(grid[i][j] + t) % k] = dp[i - 1][j][t]
                    } else {
                        dp[i][j][(grid[i][j] + t) % k] += dp[i][j - 1][t]
                        dp[i][j][(grid[i][j] + t) % k] += dp[i - 1][j][t]
                    }
                    dp[i][j][t] %= mod
                }
            }
        }

        return (dp[n - 1][m - 1][0] % mod).toInt()
    }
}