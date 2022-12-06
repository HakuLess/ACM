package leetcode.contest.c322

import utils.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    // 4
    s.magnificentSets(6, "[[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]".toGrid()).print()
    // -1
    s.magnificentSets(3, "[[1,2],[2,3],[3,1]]".toGrid()).print()
}

class SolutionD {
    fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
        val g = edges.toGraph(n, 1)

        fun bfs(start: Int): Int {
            val seen = HashSet<Int>()
            val queue: Queue<Int> = LinkedList<Int>()
            queue.offer(start)
            var step = 0
            val level = HashMap<Int, Int>()
            level[start] = 0
            while (queue.isNotEmpty()) {
                val size = queue.size
                step++
                val next = HashSet<Int>()
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (item in seen) continue
                    seen.add(item)
                    g.adj[item].forEach {
                        if (it !in seen) {
                            next.add(it)
                            level[it] = step
                        } else if (abs(level[item]!! - level[it]!!) != 1) {
                            // 算Level
                            return -1
                        }
                    }
                }
                next.forEach {
                    queue.offer(it)
                }
            }

            return step
        }

        var ans = 0
        val seen = HashSet<Int>()
        for (i in 0 until n) {
            if (i in seen) continue
            val c = ArrayList<Int>()
            g.dijkstra(i).mapIndexed { index, l ->
                if (l < Int.MAX_VALUE) {
                    c.add(index)
                }
            }
            var tmp = -1
            c.forEach {
                tmp = maxOf(tmp, bfs(it))
            }
            if (tmp == -1) return -1
            ans += tmp
            seen.addAll(c)
        }
        return ans
    }
}