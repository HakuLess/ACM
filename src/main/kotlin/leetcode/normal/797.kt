package leetcode.normal

import utils.Graph

class Solution797 {
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val n = graph.size
        val g = Graph(n)
        for (i in graph.indices) {
            graph[i].forEach {
                g.addEdgeOri(i, it)
            }
        }
        val ans = ArrayList<ArrayList<Int>>()
        fun dfs(cur: Int, route: ArrayList<Int>) {
            if (cur == n - 1) {
                ans.add(ArrayList(route))
                return
            }
            g.adj[cur].forEach {
                route.add(it)
                dfs(it, route)
                route.remove(it)
            }
        }
        dfs(0, arrayListOf<Int>(0))
        return ans
    }
}