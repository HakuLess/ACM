package leetcode.contest.b161

import utils.*

fun main() {
    val s = SolutionC()
    // 3
    s.findMaxPathScore("[[0,1,5],[1,3,10],[0,2,3],[2,3,4]]".toGrid(), booleanArrayOf(true, true, true, true), 10).print()

    s.findMaxPathScore("[[0,1,5],[1,3,10],[0,2,3],[2,3,4]]".toGrid(), booleanArrayOf(false, false, false, false), 10).print()
    // 6
    s.findMaxPathScore("[[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]]".toGrid(), booleanArrayOf(true, true, true, false, true), 12).print()
}

class SolutionC {
    fun findMaxPathScore(edges: Array<IntArray>, online: BooleanArray, k: Long): Int {

        val n = online.size
        val g = edges.toGraphOri(n)
        val sortedList = g.topologicalSort()

        fun check(threshold: Int): Boolean {
            val inf = Long.MAX_VALUE / 4
            val dp = LongArray(n) { inf }
            dp[0] = 0L
            for (u in sortedList) {
                if (dp[u] == inf || !online[u]) continue
                for ((v, cost) in g.weight[u]!!) {
                    if (!online[v] || cost < threshold) continue
                    dp[v] = minOf(dp[v], dp[u] + cost)
                }
            }
            return dp[n - 1] <= k
        }

        return biMax(-1L, Int.MAX_VALUE.toLong()) {
            check(it.toInt())
        }.toInt()
    }
}