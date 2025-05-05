package leetcode.contest.c448

import utils.print

fun main() {
    val s = SolutionB()
    s.specialGrid(2).print()
    s.specialGrid(0).print()
}

class SolutionB {
    fun specialGrid(N: Int): Array<IntArray> {

        fun dfs(n: Int): Array<IntArray> {
            if (n == 0) {
                return arrayOf(intArrayOf(0))
            }
            val grid = specialGrid(n - 1)
            val s = grid.size
            val total = 2 * s
            val offset = arrayOf(0, s * s, 2 * s * s, 3 * s * s)
            val matrix = Array(total) { IntArray(total) }

            for (i in 0 until total) {
                for (j in 0 until total) {
                    val q = when {
                        s in (i + 1)..j -> 0
                        i >= s && j >= s -> 1
                        s in (j + 1)..i -> 2
                        else -> 3
                    }
                    val gi = if (i < s) i else i - s
                    val gj = if (j < s) j else j - s
                    matrix[i][j] = grid[gi][gj] + offset[q]
                }
            }
            return matrix
        }

        return dfs(N)
    }
}