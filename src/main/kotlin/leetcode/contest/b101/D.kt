package leetcode.contest.b101

import utils.Graph
import utils.print
import utils.toGrid
import java.util.*
import java.util.List
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.forEach
import kotlin.collections.isNotEmpty


fun main() {
    val s = SolutionD()
    // 3
//    s.findShortestCycle(7, "[[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]".toGrid()).print()
//    // -1
//    s.findShortestCycle(4, "[[0,1],[0,2]]".toGrid()).print()
//    // 3
//    s.findShortestCycle(8, "[[1,3],[3,5],[5,7],[7,1],[0,2],[2,4],[4,0],[6,0],[6,1]]".toGrid()).print()
//    // -1
//    s.findShortestCycle(3, "[[1,0],[2,1]]".toGrid()).print()
    // 3
    s.findShortestCycle(4, "[[1,2],[0,1],[3,2],[1,3]]".toGrid()).print()
    // 5
    s.findShortestCycle(5, "[[2,4],[0,1],[3,2],[4,0],[1,3]]".toGrid()).print()
}

class SolutionD {
    fun findShortestCycle(n: Int, edges: Array<IntArray>): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1], 1)
        }
        var min = Int.MAX_VALUE
        for (i in 0 until n) {
            val dist = IntArray(n)
            dist[i] = 1
            val deque = ArrayDeque(listOf(Pair(i, -1)))
            while (!deque.isEmpty()) {
                val (poll, pre) = deque.poll()
                for (j in g.adj[poll]) {
                    if (dist[j] == 0) {
                        dist[j] = dist[poll] + 1
                        deque.offer(Pair(j, poll))
                    } else if (j != pre) {
                        min = minOf(min, dist[poll] + dist[j] - 1)
                    }
                }
            }
        }
        return if (min == Int.MAX_VALUE) -1 else min
    }
}