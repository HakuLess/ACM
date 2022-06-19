package leetcode.contest.c298

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.sellingWood(3, 5, "[[1,4,2],[2,2,7],[2,1,3]]".toGrid()).print()
    // 54
    s.sellingWood(9, 7, "[[4,3,2],[5,3,16],[4,4,18],[8,7,6]]".toGrid()).print()
}

class SolutionD {
    fun sellingWood(m: Int, n: Int, prices: Array<IntArray>): Long {
        val grid = Array<IntArray>(m + 1) { IntArray(n + 1) }
        for (i in prices.indices) {
            grid[prices[i][0]][prices[i][1]] = prices[i][2]
        }
        val seen = HashMap<String, Long>()
        fun dfs(h: Int = m, w: Int = n): Long {
            val key = "$h,$w"
            if (key in seen) return seen[key]!!
            var ans = grid[h][w].toLong()
            for (i in 1..h / 2) {
                ans = maxOf(ans, dfs(i, w) + dfs(h - i, w))
            }
            for (i in 1..w / 2) {
                ans = maxOf(ans, dfs(h, i) + dfs(h, w - i))
            }
            return ans.also {
                seen[key] = it
            }
        }
        return dfs()
    }
}