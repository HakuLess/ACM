package leetcode.contest.b136

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    // 2
//    s.minFlips("[[0,1],[0,1],[0,0]]".toGrid()).print()

    // 1
//    s.minFlips("[[1,0,1],[0,0,0],[0,0,0],[0,0,1]]".toGrid()).print()

    // 5
//    s.minFlips("[[0,0,0],[1,1,0],[0,1,1],[0,0,1]]".toGrid()).print()

    // 3
//    s.minFlips("[[1,0,0],[0,1,0],[0,0,1]]".toGrid()).print()

    // 3
//    s.minFlips("[[0,1,1,0,0,1]]".toGrid()).print()

    // 2
//    s.minFlips("[[0],[0],[1],[1],[1],[0],[1]]".toGrid()).print()

    // 3
    s.minFlips("[[0],[0],[1],[1],[0],[0],[1],[0]]".toGrid()).print()
}

class SolutionC {
    fun minFlips(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        var ans = 0

        // 情况一：处理有四个对应位置的格子
        for (i in 0 until n / 2) {
            for (j in 0 until m / 2) {
                val sm = grid[i][j] + grid[i][m - 1 - j] + grid[n - 1 - i][j] + grid[n - 1 - i][m - 1 - j]
                ans += minOf(sm, 4 - sm)
            }
        }

        // 情况二：处理在对称轴上的格子
        var one = 0
        var diff = 0
        if (n % 2 == 1) {
            val i = n / 2
            for (j in 0 until m / 2) {
                val jj = m - 1 - j
                if (grid[i][j] == grid[i][jj] && grid[i][j] == 1) one++
                else if (grid[i][j] != grid[i][jj]) diff++
            }
        }
        if (m % 2 == 1) {
            val j = m / 2
            for (i in 0 until n / 2) {
                val ii = n - 1 - i
                if (grid[i][j] == grid[ii][j] && grid[i][j] == 1) one++
                else if (grid[i][j] != grid[ii][j]) diff++
            }
        }
        ans += diff
        // 如果没有对称不同的格子，1 的数量又不被 4 整除，那只能花两次操作，把两个 1 都变成 0
        if (diff == 0 && one % 2 == 1) ans += 2

        // 情况三：处理矩阵中心的格子
        if (n % 2 == 1 && m % 2 == 1 && grid[n / 2][m / 2] == 1) ans++

        return ans
    }
}