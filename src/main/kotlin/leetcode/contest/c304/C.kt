package leetcode.contest.c304

import utils.Graph
import utils.dijkstra
import utils.print

fun main() {
    val s = SolutionC()
//    s.closestMeetingNode(intArrayOf(2, 2, 3, -1), 0, 1).print()
    s.closestMeetingNode(intArrayOf(5, 4, 5, 4, 3, 6, -1), 0, 1).print()
}

class SolutionC {
    fun closestMeetingNode(edges: IntArray, node1: Int, node2: Int): Int {
        val n = edges.size
        val g = Graph(n)
        for (i in edges.indices) {
            if (edges[i] != -1)
                g.addEdgeOri(i, edges[i], 1)
        }
        var cur = Long.MAX_VALUE / 4
        var ans = -1
        val a = g.dijkstra(node1)
        val b = g.dijkstra(node2)

//        a.print()
//        b.print()

        for (i in 0 until n) {
            if (maxOf(a[i], b[i]) < cur) {
//                println("$i: ${a[i]} ${b[i]} with ${maxOf(a[i], b[i])}")
                ans = i
                cur = maxOf(a[i], b[i])
            }
        }
        return ans
    }
}