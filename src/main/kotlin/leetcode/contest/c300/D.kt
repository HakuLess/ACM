package leetcode.contest.c300

import utils.dir4
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.countPaths("[[1,1],[3,4]]".toGrid()).print()
    s.countPaths("[[1],[2]]".toGrid()).print()
}

class SolutionD {
    fun countPaths(grid: Array<IntArray>): Int {
        val seen = HashMap<Int, Long>()
        val mod = 1000000007L
        fun dfs(i: Int, j: Int): Long {
            val key = i * 2000 + j
            if (key in seen) return seen[key]!!
            var ans = 1L
            dir4.forEach {
                val nextX = i + it[0]
                val nextY = j + it[1]
                if (nextX in grid.indices && nextY in grid[0].indices && grid[nextX][nextY] > grid[i][j]) {
                    ans += dfs(nextX, nextY)
                }
            }
            return (ans % mod).also {
                seen[key] = it
            }
        }

        var ans = 0L
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                ans += dfs(i, j)
                ans %= mod
            }
        }
        return ans.toInt()
    }
}