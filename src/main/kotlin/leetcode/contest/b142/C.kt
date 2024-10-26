package leetcode.contest.b142

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.maxScore(3, 2, "[[3,4,2],[2,1,2]]".toGrid(), "[[0,2,1],[2,0,4],[3,2,0]]".toGrid()).print()
    s.maxScore(2, 1, "[[1,1]]".toGrid(), "[[0,1],[6,0]]".toGrid()).print()
}

class SolutionC {
    fun maxScore(n: Int, k: Int, stayScore: Array<IntArray>, travelScore: Array<IntArray>): Int {
        var dpPrev = IntArray(n)
        for (i in 0 until k) {
            val dpCurr = IntArray(n)
            for (j in 0 until n) {
                dpCurr[j] = dpPrev[j] + stayScore[i][j]
                for (x in 0 until n) {
                    if (x != j) {
                        dpCurr[j] = maxOf(dpCurr[j], dpPrev[x] + travelScore[x][j])
                    }
                }
            }
            dpPrev = dpCurr
        }
        return dpPrev.maxOrNull() ?: 0
    }
}