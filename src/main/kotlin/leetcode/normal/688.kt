package leetcode.normal

import utils.dirHouse8
import utils.print

fun main() {
    val s = Solution688()
    s.knightProbability(3, 2, 0, 0).print()
}

class Solution688 {
    fun knightProbability(n: Int, k: Int, row: Int, column: Int): Double {
        var ans = Array<DoubleArray>(n) { DoubleArray(n) { 0.0 } }
        ans[row][column] = 1.0
        repeat(k) {
            val next = Array<DoubleArray>(n) { DoubleArray(n) { 0.0 } }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (ans[i][j] == 0.0) continue
                    dirHouse8.forEach {
                        val p = Pair(i + it[0], j + it[1])
                        if (p.first in 0 until n && p.second in 0 until n) {
                            next[p.first][p.second] += ans[i][j] / 8
                        }
                    }
                }
            }
            ans = next
        }
        var res = 0.0
        for (i in 0 until n) {
            for (j in 0 until n) {
                res += ans[i][j]
            }
        }
        return res
    }
}