package leetcode.contest.b102

import utils.Graph
import utils.dijkstra

class Graph(n: Int, edges: Array<IntArray>) {

    val g = Graph(n)

    init {
        edges.forEach {
            g.addEdgeOri(it[0], it[1], it[2])
        }
    }

    fun addEdge(edge: IntArray) {
        g.addEdgeOri(edge[0], edge[1], edge[2])
    }

    fun shortestPath(node1: Int, node2: Int): Int {
        val ans = g.dijkstra(node1)[node2]
        return if (ans == Long.MAX_VALUE / 2) -1
        else ans.toInt()
    }

}

/**
 * Your Graph object will be instantiated and called as such:
 * var obj = Graph(n, edges)
 * obj.addEdge(edge)
 * var param_2 = obj.shortestPath(node1,node2)
 */