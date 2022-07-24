package leetcode.contest.c303

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.equalPairs("[[3,2,1],[1,7,6],[2,7,7]]".toGrid()).print()
}

class SolutionB {
    fun equalPairs(grid: Array<IntArray>): Int {
        val n = grid.size
        val r = grid.map { it.joinToString(";") }
        val newGrid = Array<IntArray>(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                newGrid[i][j] = grid[j][i]
            }
        }
        val c = newGrid.map { it.joinToString(";") }
        var ans = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (r[i] == c[j]) ans++
            }
        }
        return ans
    }
}