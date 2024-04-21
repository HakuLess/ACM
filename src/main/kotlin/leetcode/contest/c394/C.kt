package leetcode.contest.c394

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.minimumOperations("[[1,1,1],[0,0,0]]".toGrid()).print()
}

class SolutionC {
    fun minimumOperations(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size

        // 每一列改为对应数字的成本
        val cnt = Array<IntArray>(m) { IntArray(10) }
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                for (k in 0..9) {
                    if (grid[i][j] != k) {
                        cnt[j][k]++
                    }
                }
            }
        }

//        cnt.print()

        for (i in 1 until m) {
            for (j in 0 until 10) {
                var pre = cnt[i - 1][j]
                cnt[i - 1][j] = Int.MAX_VALUE / 10
                cnt[i][j] = cnt[i][j] + cnt[i - 1].minOrNull()!!
                cnt[i - 1][j] = pre
            }
        }

//        cnt.print()

        return cnt.last().minOrNull()!!
    }
}