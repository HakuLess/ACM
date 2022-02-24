package leetcode.normal

import kotlin.math.abs

class Solution296 {
    fun minTotalDistance(grid: Array<IntArray>): Int {
        val x = ArrayList<Int>()
        val y = ArrayList<Int>()
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    x.add(i)
                    y.add(j)
                }
            }
        }
        x.sort()
        y.sort()
        val targetX = x[x.size / 2]
        val targetY = y[y.size / 2]

        var ans = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) {
                    ans += abs(i - targetX)
                    ans += abs(j - targetY)
                }
            }
        }
        return ans
    }
}