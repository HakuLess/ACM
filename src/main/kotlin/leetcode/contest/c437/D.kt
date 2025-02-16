package leetcode.contest.c437

class SolutionD {
    fun lenOfVDiagonal(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        val dp1 = Array(n) { IntArray(m) }
        val dp2 = Array(n) { IntArray(m) }

        var maxLength = 0

        for (i in n - 1 downTo 0) {
            for (j in m - 1 downTo 0) {
                if (grid[i][j] == 1) {
                    if (i + 1 < n && j + 1 < m && grid[i + 1][j + 1] == 2) {
                        dp1[i][j] = dp1[i + 1][j + 1] + 1
                    } else {
                        dp1[i][j] = 1
                    }
                }

                if (grid[i][j] == 1) {
                    if (i - 1 >= 0 && j + 1 < m && grid[i - 1][j + 1] == 2) {
                        dp2[i][j] = dp2[i - 1][j + 1] + 1
                    } else {
                        dp2[i][j] = 1
                    }
                }

                maxLength = maxOf(maxLength, dp1[i][j], dp2[i][j])
            }
        }

        return maxLength
    }
}