package leetcode.normal

import utils.dir4
import utils.fits

class Solution1020 {
    fun numEnclaves(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        val seen = Array<BooleanArray>(n) { BooleanArray(m) }
        fun dfs(x: Int, y: Int) {
            if (seen[x][y]) return
            seen[x][y] = true
            val cur = grid[x][y]
            grid[x][y] = 0
            for (dir in dir4) {
                intArrayOf(x + dir[0], y + dir[1]).fits(grid) { a, b ->
                    if (grid[a][b] == cur)
                        dfs(a, b)
                }
            }
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1)
                    dfs(i, j)
            }
        }
        return grid.sumBy { it.sum() }
    }
}