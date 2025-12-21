package leetcode.contest.c481

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 4
    s.interactionCosts(3, "[[0,1],[1,2]]".toGrid(), intArrayOf(1, 1, 1)).print()
    // 2
    s.interactionCosts(3, "[[0,1],[1,2]]".toGrid(), intArrayOf(1, 2, 1)).print()
    // 3
    s.interactionCosts(4, "[[0,1],[0,2],[0,3]]".toGrid(), intArrayOf(1, 1, 4, 4)).print()
    // 0
    s.interactionCosts(2, "[[0,1]]".toGrid(), intArrayOf(9, 8)).print()
}

class SolutionD {
    fun interactionCosts(n: Int, edges: Array<IntArray>, group: IntArray): Long {

        val adj = Array(n) { mutableListOf<Int>() }
        for (e in edges) {
            adj[e[0]].add(e[1])
            adj[e[1]].add(e[0])
        }

        val total = LongArray(21)
        for (g in group) total[g]++

        var answer = 0L

        fun dfs(u: Int, parent: Int): LongArray {
            val cnt = LongArray(21)
            cnt[group[u]] = 1

            for (v in adj[u]) {
                if (v == parent) continue
                val childCnt = dfs(v, u)

                for (g in 1..20) {
                    val c = childCnt[g]
                    if (c > 0) {
                        answer += c * (total[g] - c)
                        cnt[g] += c
                    }
                }
            }
            return cnt
        }

        dfs(0, -1)
        return answer
    }
}