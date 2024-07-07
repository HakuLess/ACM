package leetcode.contest.c405

import utils.print

fun main() {
    val s = SolutionC()
    s.numberOfSubmatrices(
        arrayOf(
            charArrayOf('X', 'X'),
            charArrayOf('X', 'Y'),
        )
    ).print()
}

class SolutionC {
    fun numberOfSubmatrices(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size

        // 前缀和数组
        val prefixX = Array(m + 1) { IntArray(n + 1) }
        val prefixY = Array(m + 1) { IntArray(n + 1) }

        // 计算前缀和
        for (i in 1..m) {
            for (j in 1..n) {
                prefixX[i][j] =
                    prefixX[i - 1][j] + prefixX[i][j - 1] - prefixX[i - 1][j - 1] + if (grid[i - 1][j - 1] == 'X') 1 else 0
                prefixY[i][j] =
                    prefixY[i - 1][j] + prefixY[i][j - 1] - prefixY[i - 1][j - 1] + if (grid[i - 1][j - 1] == 'Y') 1 else 0
            }
        }

        var count = 0

        // 遍历所有可能的子矩阵
        for (r in 0 until m) {
            for (c in 0 until n) {
                // 子矩阵 (r1, c1) 到 (r2, c2) 中 X 和 Y 的数量
                val xCount = prefixX[r + 1][c + 1]
                val yCount = prefixY[r + 1][c + 1]

                if (xCount > 0 && xCount == yCount) {
                    count++
                }
            }
        }

        return count
    }
}