package leetcode.contest.b174

class SolutionD {
    fun minimumFlips(n: Int, edges: Array<IntArray>, start: String, target: String): List<Int> {

        val adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for (i in edges.indices) {
            val u = edges[i][0]
            val v = edges[i][1]
            adj[u].add(v to i)
            adj[v].add(u to i)
        }

        val need = IntArray(n)
        for (i in 0 until n) {
            need[i] = (start[i] - '0') xor (target[i] - '0')
        }

        val usedEdges = mutableListOf<Int>()

        fun dfs(u: Int, parent: Int) {
            for ((v, idx) in adj[u]) {
                if (v == parent) continue
                dfs(v, u)
                if (need[v] == 1) {
                    usedEdges.add(idx)
                    need[v] = 0
                    need[u] = need[u] xor 1
                }
            }
        }

        dfs(0, -1)

        if (need[0] == 1) {
            return intArrayOf(-1).toList()
        }

        usedEdges.sort()
        return usedEdges
    }
}