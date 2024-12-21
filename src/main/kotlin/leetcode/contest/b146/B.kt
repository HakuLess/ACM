package leetcode.contest.b146

class SolutionB {
    fun countPathsWithXorValue(grid: Array<IntArray>, k: Int): Int {
        val MOD = 1_000_000_007
        val m = grid.size
        val n = grid[0].size

        val dp = Array(m) { Array(n) { IntArray(16) } }

        dp[0][0][grid[0][0]] = 1

        for (i in 0 until m) {
            for (j in 0 until n) {
                for (x in 0..15) {
                    if (dp[i][j][x] > 0) {
                        if (i + 1 < m) {
                            dp[i + 1][j][x xor grid[i + 1][j]] =
                                (dp[i + 1][j][x xor grid[i + 1][j]] + dp[i][j][x]) % MOD
                        }
                        if (j + 1 < n) {
                            dp[i][j + 1][x xor grid[i][j + 1]] =
                                (dp[i][j + 1][x xor grid[i][j + 1]] + dp[i][j][x]) % MOD
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][k]
    }
}