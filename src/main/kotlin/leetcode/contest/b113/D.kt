package leetcode.contest.b113

import utils.print
import utils.toGrid


fun main() {
    val s = SolutionD()
//    s.minEdgeReversals(4, "[[2,0],[2,1],[1,3]]".toGrid()).print()
//    s.minEdgeReversals(4, "[[0,2],[0,3],[3,1]]".toGrid()).print()
    s.minEdgeReversals(4, "[[0,3],[1,2],[2,3]]".toGrid()).print()
}

class SolutionD {
    fun minEdgeReversals(n: Int, edges: Array<IntArray>): IntArray {
        val map = HashMap<Int, ArrayList<IntArray>>()
        for (edge in edges) {
            map.computeIfAbsent(
                edge[0]
            ) { t: Int -> ArrayList() }.add(edge)
            map.computeIfAbsent(
                edge[1]
            ) { t: Int -> ArrayList() }.add(edge)
        }
        val result = IntArray(n)

        fun dfs1(u: Int, v: Int): Int {
            var count = 0
            for (i in map[u]!!) {
                if (i[0] != v && i[1] != v) {
                    count += (if (i[0] == u) 0 else 1) + dfs1(i[if (i[0] == u) 1 else 0], u)
                }
            }
            return count
        }

        fun dfs2(u: Int, v: Int) {
            for (i in map[u]!!) {
                if (i[0] != v && i[1] != v) {
                    result[i[if (i[0] == u) 1 else 0]] = result[u] + if (i[0] == u) 1 else -1
                    dfs2(i[if (i[0] == u) 1 else 0], u)
                }
            }
        }

        result[0] = dfs1(0, -1)
        dfs2(0, -1)
        return result
    }
}