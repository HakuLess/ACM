package leetcode.contest.c305

import utils.Graph
import java.util.*
import kotlin.collections.HashSet

class SolutionB {
    fun reachableNodes(n: Int, edges: Array<IntArray>, restricted: IntArray): Int {
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }

        val queue: Queue<Int> = LinkedList<Int>()
        val seen = HashSet<Int>()
        queue.offer(0)
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                seen.add(item)
                g.adj[item].forEach {
                    if (it !in restricted && it !in seen) {
                        queue.offer(it)
                    }
                }
            }
        }
        return seen.size
    }
}