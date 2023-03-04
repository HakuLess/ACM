package leetcode.contest.b99

import utils.Graph
import utils.dijkstra
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.rootCount("[[0,1],[1,2],[1,3],[4,2]]".toGrid(), "[[1,3],[0,1],[1,0],[2,4]]".toGrid(), 3).print()
    s.rootCount("[[0,1],[1,2],[2,3],[3,4]]".toGrid(), "[[1,0],[3,4],[2,1],[3,2]]".toGrid(), 1).print()
}

class SolutionD {
    fun rootCount(edges: Array<IntArray>, guesses: Array<IntArray>, k: Int): Int {
        val n = edges.size + 1
        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1], 1)
        }

        var ans = 0
        val seen = HashSet<Int>()

        val set = guesses.map {
            Pair(it[0], it[1])
        }.toHashSet()

        fun dfs(cur: Int, pre: Int = -1, total: Int) {
            if (cur in seen) return
            seen.add(cur)

            var next = total
            if (Pair(pre, cur) in set) next--
            if (Pair(cur, pre) in set) next++

            // 选中cur作为根节点
            if (next >= k) ans++

            g.adj[cur].forEach {
                dfs(it, cur, next)
            }
        }

        val cur = g.dijkstra(0)
        dfs(0, -1, guesses.count {
            cur[it[0]] < cur[it[1]]
        })

        return ans
    }
}