package leetcode.contest.b63

import utils.Graph
import utils.dijkstra
import utils.print
import utils.toGrid

fun main() {
    val s = Solution5888()
//    s.networkBecomesIdle("[[0,1],[1,2]]".toGrid(), intArrayOf(0, 2, 1)).print()
//
//    s.networkBecomesIdle("[[0,1],[0,2],[1,2]]".toGrid(), intArrayOf(0, 10, 10)).print()

    s.networkBecomesIdle(
        "[[9,10],[0,4],[10,11],[8,3],[0,9],[6,8],[0,1],[8,2],[11,3],[8,11],[7,6],[1,5],[4,2],[5,3],[0,7],[3,2],[11,2]]".toGrid(),
        intArrayOf(0, 1, 1, 9, 3, 1, 1, 1, 5, 3, 1, 5)
    ).print()
}

class Solution5888 {
    fun networkBecomesIdle(edges: Array<IntArray>, patience: IntArray): Int {
        val n = patience.size
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1], 1)
        }
        val dis = g.dijkstra(0)
        var ans = 0L
        for (i in 1 until n) {
            if (dis[i] * 2 > patience[i]) {
                // 最后一次发射时间
                var cur = (dis[i] * 2 / patience[i]) * patience[i]
                if (dis[i] * 2 % patience[i] == 0L) {
                    cur -= patience[i].toLong()
                }
                ans = maxOf(ans, cur + dis[i] * 2 + 1)
            } else {
                ans = maxOf(ans, dis[i] * 2 + 1)
            }
        }
        return ans.toInt()
    }
}