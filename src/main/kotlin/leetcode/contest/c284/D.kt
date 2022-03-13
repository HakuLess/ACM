package leetcode.contest.c284

import utils.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minimumWeight(5, "[[4,2,20],[4,3,46],[0,1,15],[0,1,43],[0,1,32],[3,1,13]]".toGrid(), 0, 4, 1).print()
    s.minimumWeight(5, "[[0,1,100000],[1,2,100000],[2,3,100000],[3,4,100000]]".toGrid(), 1, 3, 4).print()
}

class SolutionD {
    fun minimumWeight(n: Int, edges: Array<IntArray>, src1: Int, src2: Int, dest: Int): Long {
        // 正向
        val g0 = Graph(n)
        // 逆向
        val g1 = Graph(n)
        val seen = HashSet<Pair<Int, Int>>()
        edges.sortBy { it[2] }
        edges.forEach {
            if (Pair(it[0], it[1]) in seen) return@forEach
            seen.add(Pair(it[0], it[1]))
            g1.addEdgeOri(it[1], it[0], it[2])
            g0.addEdgeOri(it[0], it[1], it[2])
        }
        val a = g0.dijkstra(src1)
        val b = g0.dijkstra(src2)
        val c = g1.dijkstra(dest)
        var ans = Long.MAX_VALUE / 2
        for (i in a.indices) {
            if (a[i] >= Long.MAX_VALUE / 2 ||
                b[i] >= Long.MAX_VALUE / 2 ||
                c[i] >= Long.MAX_VALUE / 2
            ) continue
            ans = minOf(ans, a[i] + b[i] + c[i])
        }
        if (ans >= Long.MAX_VALUE / 2) return -1
        return ans
    }
}