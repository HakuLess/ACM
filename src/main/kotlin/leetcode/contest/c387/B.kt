package leetcode.contest.c387

import utils.Matrix
import utils.print
import utils.subMatrixSum
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.countSubmatrices("[[7,6,3],[6,6,1]]".toGrid(), 18).print()
}

class SolutionB {
    fun countSubmatrices(grid: Array<IntArray>, k: Int): Int {
        val n = grid.size
        val m = grid[0].size
        val matrix = Matrix(n, m, grid)
        matrix.preSum()

        var ans = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                val sum = matrix.subMatrixSum(0, 0, i, j)
                if (sum <= k) {
                    ans++
                }
            }
        }
        return ans
    }
}