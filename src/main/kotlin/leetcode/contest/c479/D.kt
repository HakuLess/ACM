package leetcode.contest.c479

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // [1,1,1]
    s.maxSubgraphScore(3, "[[0,1],[1,2]]".toGrid(), intArrayOf(1, 0, 1)).print()
    // [2,3,2,3,3]
    s.maxSubgraphScore(5, "[[1,0],[1,2],[1,3],[3,4]]".toGrid(), intArrayOf(0, 1, 0, 1, 1)).print()
    // [-1,-1]
    s.maxSubgraphScore(2, "[[0,1]]".toGrid(), intArrayOf(0, 0)).print()
}

class SolutionD {
    fun maxSubgraphScore(n: Int, edges: Array<IntArray>, good: IntArray): IntArray {
        val g = Array(n) { ArrayList<Int>() }
        for (e in edges) {
            g[e[0]].add(e[1])
            g[e[1]].add(e[0])
        }

        val value = IntArray(n) { if (good[it] == 1) 1 else -1 }

        // 以节点0为根，自底向上计算可以提供父节点贡献值
        val dp1 = IntArray(n)
        fun dfs1(u: Int, p: Int) {
            var sum = value[u]
            for (v in g[u]) {
                if (v == p) continue
                dfs1(v, u)
                if (dp1[v] > 0) sum += dp1[v]
            }
            dp1[u] = sum
        }
        dfs1(0, -1)

        val dp2 = IntArray(n)
        fun dfs2(u: Int, p: Int) {
            // 先计算 children 的贡献值
            var totalPos = 0
            for (v in g[u]) {
                if (v == p) continue
                if (dp1[v] > 0) totalPos += dp1[v]
            }

            for (v in g[u]) {
                if (v == p) continue
                // Parent 贡献值，包含兄弟，需要减去自己这一支提供的贡献值
                val withoutV = totalPos - maxOf(dp1[v], 0)
                val fromParent = dp2[u] + value[u] + withoutV

                dp2[v] = maxOf(fromParent, 0)
                dfs2(v, u)
            }
        }
        dfs2(0, -1)

        val ans = IntArray(n) { -1 }
        for (i in 0 until n) {
            val best = dp1[i] + dp2[i]
            ans[i] = maxOf(ans[i], best)
        }
        return ans
    }
}