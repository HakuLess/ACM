package leetcode.contest.c394

import utils.Graph
import utils.print
import utils.toGraph
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.findAnswer(6, "[[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]".toGrid()).print()
}

class SolutionD {
    fun findAnswer(n: Int, edges: Array<IntArray>): BooleanArray {
        val m = edges.size
        val g = edges.toGraph(n, 0)

        // 当前消耗距离，当前节点，当前总路径
        val q = PriorityQueue<Triple<Int, Int, HashSet<Pair<Int, Int>>>>(compareBy { it.first })
        q.offer(Triple(0, 0, hashSetOf()))

        val ansPair = HashSet<Pair<Int, Int>>()
        var max = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            val (total, cur, routeSet) = q.poll()
//            println("poll $total $cur ${routeSet.joinToString()}")
            if (total > max) continue
            if (cur == n - 1) {
                if (total < max) {
                    max = total
                }
                if (total == max) {
                    ansPair.addAll(routeSet)
                }
            }
            g.adj[cur].forEach {
                if (routeSet.any { pair -> pair.first == it || pair.second == it }) {
                    return@forEach
                }
                val nextSet = routeSet.clone() as HashSet<Pair<Int, Int>>
                nextSet.add(Pair(cur, it))
                q.offer(Triple(total + g.weight[cur]!![it]!!, it, nextSet))
            }
        }

//        ansPair.joinToString().print()

        val ans = BooleanArray(m)
        for (i in 0 until m) {
            val (a, b) = edges[i]
            if (Pair(a, b) in ansPair || Pair(b, a) in ansPair) {
                ans[i] = true
            }
        }
        return ans
    }
}