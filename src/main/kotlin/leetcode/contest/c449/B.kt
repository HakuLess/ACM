package leetcode.contest.c449

import utils.preSumArray

class SolutionB {
    fun canPartitionGrid(grid: Array<IntArray>): Boolean {
        val n = grid.size
        val m = grid[0].size
        val cols = IntArray(m)
        val rows = IntArray(n)
        var sum = 0L
        for (i in 0 until n) {
            for (j in 0 until m) {
                val item = grid[i][j]
                cols[j] += item
                rows[i] += item
                sum += item
            }
        }
        if (sum % 2 != 0L) return false
        val target = sum / 2
        val preSumCols = cols.preSumArray(false)
        if (target in preSumCols) return true
        val preSumRows = rows.preSumArray(false)
        if (target in preSumRows) return true

        return false
    }
}