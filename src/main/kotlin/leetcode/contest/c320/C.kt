package leetcode.contest.c320

import utils.Graph
import utils.print
import utils.toGrid
import kotlin.math.ceil

fun main() {
    val s = SolutionC()
    s.minimumFuelCost("[[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]".toGrid(), 2).print()
    // 16
    s.minimumFuelCost("[[0,1],[2,1],[3,2],[4,2],[4,5],[6,0],[5,7],[8,4],[9,2]]".toGrid(), 2).print()
    // 19
    s.minimumFuelCost(
        "[[0,1],[1,2],[1,3],[4,2],[5,3],[6,3],[6,7],[8,6],[9,0],[5,10],[11,9],[12,5],[5,13],[8,14],[11,15],[8,16],[17,0],[18,7]]".toGrid(),
        13
    ).print()
}

class SolutionC {
    fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
        val n = roads.size
        val g = Graph(n + 1)
        roads.forEach {
            g.addEdge(it[0], it[1])
        }
        val seen = HashSet<Int>()

        // 人数、距离
        fun dfs(cur: Int): Pair<Long, Long> {
            if (cur in seen) return Pair(0, 0)
            seen.add(cur)
            var c = 0L
            var dis = 0L
            g.adj[cur].forEach {
                val (p, d) = dfs(it)
                c += p
                dis += d + ceil(p.toDouble() / seats).toLong()
            }
            return Pair(c + 1, dis)
        }
        return dfs(0).second
    }
}