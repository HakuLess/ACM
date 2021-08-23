package leetcode.contest.b59

import utils.Graph
import utils.dijkstra
import utils.print
import utils.toGrid
import kotlin.collections.HashMap

fun main() {
    val s = Solution5836()
    s.countPaths(7, "[[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]".toGrid())
        .print()
}

class Solution5836 {
    fun countPaths(n: Int, roads: Array<IntArray>): Int {
        val mod = 1000000007L
        val g = Graph(n)
        roads.forEach {
            g.addEdge(it[0], it[1], it[2])
        }
        val minArr = g.dijkstra(0)
        val seen = HashMap<String, Long>()
        fun dfs(cur: Int, cost: Long): Long {
            val key = "$cur,$cost"
            if (key in seen) {
                return seen[key]!!
            }
            if (minArr[cur] != cost) return 0L
            var ans = 0L
            if (cur == n - 1 && cost == minArr[cur]) return 1L
            if (cost >= minArr[n - 1]) return 0L
            g.adj[cur].forEach {
                ans += dfs(it, cost + g.weight[cur]!![it]!!)
            }
            return (ans % mod).also {
                seen[key] = it
            }
        }
        val ans = dfs(0, 0L)
        return (ans % mod).toInt()
    }
}