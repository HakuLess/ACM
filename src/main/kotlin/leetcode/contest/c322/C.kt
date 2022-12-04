package leetcode.contest.c322

import utils.*
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    s.minScore(4, "[[1,2,9],[2,3,6],[2,4,5],[1,4,7]]".toGrid()).print()
    s.minScore(
        6,
        "[[4,5,7468],[6,2,7173],[6,3,8365],[2,3,7674],[5,6,7852],[1,2,8547],[2,4,1885],[2,5,5192],[1,3,4065],[1,4,7357]]".toGrid()
    ).print()

}

class SolutionC {
    fun minScore(n: Int, roads: Array<IntArray>): Int {

        var max = 0
        for (i in roads.indices) {
            max = maxOf(max, roads[i][0])
            max = maxOf(max, roads[i][1])
        }
        val g = Graph(max + 2)
        roads.forEach {
            g.addEdge(it[0], it[1], it[2])
        }
        var ans = Int.MAX_VALUE

        val seen = HashSet<Int>()
        fun dfs(cur: Int) {
            if (cur in seen) return
            seen.add(cur)
            g.adj[cur].forEach {
                dfs(it)
                ans = minOf(ans, g.weight[cur]!![it]!!)
            }
        }
        dfs(1)

        return ans
    }
}