package leetcode.contest.b154

import utils.*

fun main() {
    val s = SolutionD()
    s.treeQueries(2, "[[1,2,7]]".toGrid(), "[[2,2],[1,1,2,4],[2,2]]".toGrid()).print()
}

class SolutionD {
    fun treeQueries(n: Int, edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
        if (edges.isEmpty()) {
            val size = queries.filter { it[0] == 2 }.size
            return IntArray(size) { 0 }
        }

        val g = edges.toGraph(n, offset = 1)

        val ans = ArrayList<Int>()
        queries.forEach {
            if (it[0] == 1) {
                val (type, u, v, w) = it
                g.weight[u - 1]!![v - 1] = w
                g.weight[v - 1]!![u - 1] = w
            } else {
                val (type, x) = it
                val distance = g.dijkstra(0)
                ans.add(distance[x - 1].toInt())
            }
        }

        return ans.toIntArray()
    }
}