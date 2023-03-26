package leetcode.contest.c338

import utils.Tree
import utils.print
import utils.toGrid
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    // 22
    s.collectTheCoins(
        intArrayOf(1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1),
        "[[0,1],[1,2],[2,3],[3,4],[4,5],[5,6],[6,7],[7,8],[8,9],[8,10],[9,11],[10,12],[11,13],[13,14],[13,15],[15,16],[16,17],[17,18]]".toGrid()
    ).print()
}

// Rank 54	时光放逐
class SolutionD {

    fun collectTheCoins(coins: IntArray, edges: Array<IntArray>): Int {
        val n = edges.size + 1

        val graph = Array(n) { mutableListOf<Int>() }
        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            graph[u].add(v)
            graph[v].add(u)
        }

        val tree = Tree(n, edges)

        val set = HashSet<Int>()
        for (i in coins.indices) {
            if (coins[i] == 1) set.add(i)
        }

        val seen = HashSet<Int>()
        var ans = Int.MAX_VALUE
        fun dfs(cur: Int) {
            if (cur in seen) return
            seen.add(cur)
            var tmp = 0
            set.forEach {
                tmp += maxOf(0, tree.distance(it, cur) - 2)
            }
            graph[cur].forEach {
                dfs(it)
            }
            println("$cur: $tmp")
            ans = minOf(ans, tmp)
        }
        dfs(0)

        return ans * 2
    }

}