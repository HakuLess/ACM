package leetcode.contest.c397

import utils.print
import utils.toListGrid

fun main() {
    val s = SolutionC()
    s.maxScore("[[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]".toListGrid()).print()
    s.maxScore("[[4,3,2],[3,2,1]]".toListGrid()).print()
    s.maxScore("[[4,3],[2,3]]".toListGrid()).print()
}

class SolutionC {
    fun maxScore(grid: List<List<Int>>): Int {

        val seen = HashMap<String, Int>()

        // i,j 左上最小值，不包括 i,j 本身
        fun dfs(i: Int, j: Int): Int {
            val key = "$i, $j"
            if (key in seen) return seen[key]!!

            var tmp = Int.MAX_VALUE / 2
            if (i - 1 in grid.indices) {
                tmp = minOf(tmp, grid[i - 1][j])
                tmp = minOf(tmp, dfs(i - 1, j))
            }
            if (j - 1 in grid[0].indices) {
                tmp = minOf(tmp, grid[i][j - 1])
                tmp = minOf(tmp, dfs(i, j - 1))
            }

            return tmp.also {
                seen[key] = it
            }
        }

        var ans = Int.MIN_VALUE / 2
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                ans = maxOf(ans, grid[i][j] - dfs(i, j))
            }
        }
        return ans
    }
}