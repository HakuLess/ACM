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
        val m = grid[0].size
        val a = LongArray(m)
        for (i in 0 until m)
            a[i] = (if (i != 0) a[i - 1] else 0) + grid[1][i]
        val b = LongArray(m)
        for (i in m - 1 downTo 0)
            b[i] = (if (i != m - 1) b[i + 1] else 0) + grid[0][i]
        var ans = Long.MAX_VALUE / 2
        for (i in 0 until m) {
            ans = minOf(
                ans, maxOf(
                    a.getOrElse(i - 1) { 0L },
                    b.getOrElse(i + 1) { 0L }
                )
            )
        }
        return ans
    }
}