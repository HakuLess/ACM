package leetcode.contest.b69

import utils.*

fun main() {
    val s = SolutionD()
    s.possibleToStamp("[[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]]".toGrid(), 4, 3).print()
    s.possibleToStamp("[[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]]".toGrid(), 2, 2).print()
    s.possibleToStamp("[[0,0,0,0,0],[0,0,0,0,0],[0,0,1,0,0],[0,0,0,0,1],[0,0,0,1,1]]".toGrid(), 2, 2).print()
}

class SolutionD {
    fun possibleToStamp(grid: Array<IntArray>, stampHeight: Int, stampWidth: Int): Boolean {

        val matrix = grid.toMatrix()
        matrix.preSum()

        fun fill(x0: Int, y0: Int, x1: Int, y1: Int): Boolean {
            if (x0 !in grid.indices || x1 !in grid.indices || y0 !in grid[0].indices || y1 !in grid[0].indices) return false
            if (matrix.subMatrixSum(x0, y0, x1, y1) == 0)
                return true
            return false
        }

        val paint = Array<IntArray>(grid.size) { IntArray(grid[0].size) }

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] != 0) continue
                // 左上
                if (fill(i, j, i + stampHeight - 1, j + stampWidth - 1))
                    paint[i][j] = 1
            }
        }

        val pMatrix = paint.toMatrix()
        pMatrix.preSum()

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) continue
                val top = maxOf(0, i - stampHeight + 1)
                val left = maxOf(0, j - stampWidth + 1)
                if (pMatrix.subMatrixSum(top, left, i, j) == 0) return false
            }
        }
        return true
    }
}