package leetcode.contest.c346

import utils.Graph
import utils.dijkstra
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.modifiedGraphEdges(5, "[[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]]".toGrid(), 0, 1, 5).print()
//    s.modifiedGraphEdges(3, "[[0,1,-1],[0,2,5]]".toGrid(), 0, 2, 6).print()
//    s.modifiedGraphEdges(4, "[[0,1,-1],[1,2,-1],[3,1,-1],[3,0,2],[0,2,5]]".toGrid(), 2, 3, 8).print()
//    s.modifiedGraphEdges(4, "[[0,1,-1],[2,0,2],[3,2,6],[2,1,10],[3,0,-1]]".toGrid(), 1, 3, 12).print()
    s.modifiedGraphEdges(5, "[[0,3,1],[1,2,-1],[2,3,7],[4,2,1],[2,0,-1],[4,1,9],[3,4,9]]".toGrid(), 0, 1, 18).print()
}

// todo not finish
class SolutionD {
    fun modifiedGraphEdges(
        n: Int,
        edges: Array<IntArray>,
        source: Int,
        destination: Int,
        target: Int
    ): Array<IntArray> {

        val bg = Graph(n)
        edges.forEach {
            if (it[2] != -1) {
                bg.addEdge(it[0], it[1], it[2])
            }
        }
        val dis = bg.dijkstra(source)
        if (dis[destination] < target) {
            return emptyArray()
        }
        dis.print()

        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1], it[2])
        }

        data class P(val i: Int, val v: Long, val meet: Boolean, val e: ArrayList<IntArray>)

        // 距离source的最短路径
        val ans = LongArray(n) { Long.MAX_VALUE / 2 }
        val pq = PriorityQueue<P>(compareBy({ it.v }, { it.meet }))
        pq.offer(P(source, 0L, false, arrayListOf()))
        var p: P? = null
        while (pq.isNotEmpty()) {
            p = pq.peek()
            val (item, dis, meet, es) = pq.poll()
            if (ans[item] <= dis) continue
            ans[item] = dis
            if (item == destination) break
            g.adj[item].forEach {
                if (ans[it] >= Int.MAX_VALUE / 2) {
                    val e = g.weight[item]!![it]!!
//                    println("e with $e && $meet")
                    val next = ArrayList<IntArray>()
                    next.addAll(es)
                    next.add(intArrayOf(item, it))
                    pq.offer(P(it, dis + if (e == -1) 1 else e, meet or (e == -1), next))
                }
            }
        }

        if (ans[destination] <= target && p!!.meet) {
//            println("$p with $target - ${ans[destination]}")
            val es = p!!.e
            for (e in es) {
                println(e.joinToString())
                val a = ArrayList<IntArray>()
                edges.forEach {
                    if (it[2] == -1) {
                        if (e.joinToString() == "${it[0]}, ${it[1]}" || e.joinToString() == "${it[1]}, ${it[0]}") {
                            a.add(intArrayOf(it[0], it[1], target + 1 - ans[destination].toInt()))
                        } else {
                            a.add(intArrayOf(it[0], it[1], 1))
                        }
                    } else {
                        a.add(intArrayOf(it[0], it[1], it[2]))
                    }
                }
                val cg = Graph(n)
                a.forEach {
                    cg.addEdge(it[0], it[1], it[2])
                }
                cg.dijkstra(source).print()
                if (cg.dijkstra(source)[destination] == target.toLong()) {
                    return a.toTypedArray()
                }
            }
        }
        return emptyArray()
    }
}