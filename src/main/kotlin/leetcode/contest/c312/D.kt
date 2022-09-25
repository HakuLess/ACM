package leetcode.contest.c312

import utils.Graph
import utils.UFS
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 6
    s.numberOfGoodPaths(intArrayOf(1, 3, 2, 1, 3), "[[0,1],[0,2],[2,3],[2,4]]".toGrid()).print()
    // 20
    s.numberOfGoodPaths(
        intArrayOf(2, 5, 5, 1, 5, 2, 3, 5, 1, 5),
        "[[0,1],[2,1],[3,2],[3,4],[3,5],[5,6],[1,7],[8,4],[9,7]]".toGrid()
    ).print()


}

class SolutionD {
    fun numberOfGoodPaths(vals: IntArray, edges: Array<IntArray>): Int {
        val n = vals.size
        var ans = 0

        val g = Graph(n)
        edges.forEach {
            g.addEdge(it[0], it[1])
        }

        val map = HashMap<Int, ArrayList<Int>>()
        for (i in vals.indices) {
            map[vals[i]] = map.getOrDefault(vals[i], arrayListOf())
            map[vals[i]]!!.add(i)
        }

        val ufs = UFS(n)
        map.keys.sorted().forEach { key ->
            val value = map[key]!!
            for (i in value) {
                for (j in g.adj[i]) {
                    if (vals[j] <= key) {
                        ufs.union(i, j)
                    }
                }
            }

            val cnt = HashMap<Int, Int>()
            for (i in value) {
                cnt[ufs.find(i)] = cnt.getOrDefault(ufs.find(i), 0) + 1
            }
            cnt.values.forEach {
                ans += it * (it - 1) / 2
            }
        }
        return ans + n
    }
}