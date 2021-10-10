package leetcode.contest.c262

import kotlin.math.abs

class Solution5895 {
    fun minOperations(grid: Array<IntArray>, x: Int): Int {
        val n = grid.size
        val m = grid[0].size
        val l = arrayListOf<Int>()
        for (i in grid.indices) {
            l.addAll(grid[i].toList())
        }

        val mid = l.sorted()[m * n / 2]
        var ans = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                val diff = abs(grid[i][j] - mid)
                if (diff % x != 0) return -1
                ans += diff / x
            }
        }
        return ans
    }
}