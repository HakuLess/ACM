package leetcode.contest.b73

import utils.*

fun main() {
    val s = SolutionC()
    s.getAncestors(8, "[[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]".toGrid()).print()
}

class SolutionC {
    fun getAncestors(n: Int, edges: Array<IntArray>): List<List<Int>> {
        val g = Graph(n)
        edges.forEach {
            g.addEdgeOri(it[1], it[0], 1)
        }
        val ans = ArrayList<ArrayList<Int>>()
        for (i in 0 until n) {
            val tmp = ArrayList<Int>()
            g.dijkstra(i).forEachIndexed { index, l ->
                if (l > 0 && l < Int.MAX_VALUE) {
                    tmp.add(index)
                }
            }
            ans.add(tmp)
        }
        return ans
    }
}