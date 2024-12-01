package leetcode.contest.c426

import utils.Graph
import utils.dijkstra
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    // 8,7,7,8,8
    s.maxTargetNodes("[[0,1],[0,2],[2,3],[2,4]]".toGrid(), "[[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]".toGrid())
        .print()

    // 3,6,6,6,6
    s.maxTargetNodes("[[0,1],[0,2],[0,3],[0,4]]".toGrid(), "[[0,1],[1,2],[2,3]]".toGrid()).print()
}

class SolutionD {
    fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>): IntArray {
        val n = edges1.size + 1
        val m = edges2.size + 1
        val g1 = Graph(n)
        val g2 = Graph(m)

        edges1.forEach { (a, b) -> g1.addEdge(a, b, 1) }
        edges2.forEach { (a, b) -> g2.addEdge(a, b, 1) }

        val dis1 = g1.dijkstra(0)
        val dis2 = g2.dijkstra(0)

        val a0 = HashSet<Int>()
        val a1 = HashSet<Int>()
        val b0 = HashSet<Int>()
        val b1 = HashSet<Int>()
        for (i in dis1.indices) {
            if (dis1[i] % 2 == 0L) {
                a0.add(i)
            } else {
                a1.add(i)
            }
        }

        for (i in dis2.indices) {
            if (dis2[i] % 2 == 0L) {
                b0.add(i)
            } else {
                b1.add(i)
            }
        }

        val b = maxOf(b0.size, b1.size)
        val ans = ArrayList<Int>()
        for (i in 0 until n) {
            if (i in a0) {
                ans.add(a0.size + b)
            } else {
                ans.add(a1.size + b)
            }
        }
        return ans.toIntArray()
    }
}