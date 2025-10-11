package leetcode.contest.b167

import utils.biMax
import java.util.LinkedList
import kotlin.math.abs

class SolutionD {
    fun maxPartitionFactor(points: Array<IntArray>): Int {
        val n = points.size
        if (n == 2) {
            return 0
        }

        fun check(d: Long): Boolean {
            val n = points.size
            val adj = Array(n) { mutableListOf<Int>() }

            for (i in 0 until n) {
                for (j in i + 1 until n) {
                    val dist = abs(points[i][0].toLong() - points[j][0].toLong()) +
                            abs(points[i][1].toLong() - points[j][1].toLong())

                    if (dist < d) {
                        adj[i].add(j)
                        adj[j].add(i)
                    }
                }
            }

            val colors = IntArray(n)
            val queue = LinkedList<Int>()

            for (i in 0 until n) {
                if (colors[i] == 0) {
                    queue.offer(i)
                    colors[i] = 1

                    while (queue.isNotEmpty()) {
                        val u = queue.poll()
                        for (v in adj[u]) {
                            if (colors[v] == 0) {
                                colors[v] = -colors[u]
                                queue.offer(v)
                            } else if (colors[v] == colors[u]) {
                                return false
                            }
                        }
                    }
                }
            }
            return true
        }

        return biMax(0L, Long.MAX_VALUE / 4) {
            check(it)
        }.toInt()
    }
}