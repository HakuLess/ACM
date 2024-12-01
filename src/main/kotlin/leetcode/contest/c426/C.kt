package leetcode.contest.c426

import utils.*

fun main() {
    val s = SolutionC()
    s.maxTargetNodes("[[0,1],[0,2],[2,3],[2,4]]".toGrid(), "[[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]".toGrid(), 2)
        .print()
}

class SolutionC {
    fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>, k: Int): IntArray {
        val n = edges1.size + 1
        val m = edges2.size + 1
        val g1 = Graph(n)
        val g2 = Graph(m)

        edges1.forEach { (a, b) -> g1.addEdge(a, b, 1) }
        edges2.forEach { (a, b) -> g2.addEdge(a, b, 1) }

        // 计算两棵树的距离矩阵
        val dist1 = Array(n) { g1.dijkstra(it) }
        val dist2 = Array(m) { g2.dijkstra(it) }

        val ans = ArrayList<Int>()
        for (i in 0 until n) {
            var tmp = dist1[i].count { it <= k }
            tmp += dist2.maxOf { it.count { it <= k - 1 } }
            ans.add(tmp)
        }
        return ans.toIntArray()
    }
}