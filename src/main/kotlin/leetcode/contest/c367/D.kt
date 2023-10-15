package leetcode.contest.c367

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.constructProductMatrix("[[1,2],[3,4]]".toGrid()).print()
    s.constructProductMatrix("[[12345],[2],[1]]".toGrid()).print()
}

class SolutionD {
    fun constructProductMatrix(grid: Array<IntArray>): Array<IntArray> {
        val mod = 12345
        val n = grid.size
        val m = grid[0].size

        val row = LongArray(n) { 1L }
        for (i in 0 until n) {
            for (j in 0 until m) {
                row[i] = (row[i] * grid[i][j]) % mod
            }
        }
//        row.print()
        val rowLeft = LongArray(n) { 1L }
        val rowRight = LongArray(n) { 1L }
        for (i in 0 until n) {
            rowLeft[i] = if (i == 0) row[i] else rowLeft[i - 1] * row[i]
            rowLeft[i] = rowLeft[i] % mod
        }
        for (i in n - 1 downTo 0) {
            rowRight[i] = if (i == n - 1) row[i] else rowRight[i + 1] * row[i]
            rowRight[i] = rowRight[i] % mod
        }
        val colLeft = Array<LongArray>(n) { LongArray(m) }
        val colRight = Array<LongArray>(n) { LongArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                colLeft[i][j] = if (j == 0) {
                    grid[i][j].toLong()
                } else {
                    colLeft[i][j - 1] * grid[i][j]
                }
                colLeft[i][j] = colLeft[i][j] % mod
            }
            for (j in m - 1 downTo 0) {
                colRight[i][j] = if (j == m - 1) {
                    grid[i][j].toLong()
                } else {
                    colRight[i][j + 1] * grid[i][j]
                }
                colRight[i][j] = colRight[i][j] % mod
            }
        }

        rowLeft.print()
        rowRight.print()

        val ans = Array<IntArray>(n) { IntArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                var tmp = 1L
                if (i - 1 in rowLeft.indices) {
                    tmp *= rowLeft[i - 1]
                    tmp %= mod
                }
                if (i + 1 in rowRight.indices) {
                    tmp *= rowRight[i + 1]
                    tmp %= mod
                }
                if (j - 1 in 0 until m) {
                    tmp *= colLeft[i][j - 1]
                    tmp %= mod
                }
                if (j + 1 in 0 until m) {
                    tmp *= colRight[i][j + 1]
                    tmp %= mod
                }
                ans[i][j] = tmp.toInt()
            }
        }
        return ans
    }
}