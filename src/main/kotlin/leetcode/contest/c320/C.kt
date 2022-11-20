package leetcode.contest.c320

import utils.Graph
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.minimumFuelCost("[[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]".toGrid(), 2).print()
    // 16
    s.minimumFuelCost("[[0,1],[2,1],[3,2],[4,2],[4,5],[6,0],[5,7],[8,4],[9,2]]".toGrid(), 2).print()
}

class SolutionC {
    fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
        val n = roads.size
        val g = Graph(n + 1)
        roads.forEach {
            g.addEdge(it[0], it[1])
        }
        val seen = HashSet<Int>()

        var ans = 0L

        // 人数、距离
        fun dfs(cur: Int): Int {
            if (cur in seen) return 0
            seen.add(cur)
            var c = 0
            g.adj[cur].forEach {
                val p = dfs(it)
                c += p
                // 向上取整
                ans += (p + seats - 1) / seats
            }
            return c + 1
        }
        dfs(0)
        return ans
    }
}