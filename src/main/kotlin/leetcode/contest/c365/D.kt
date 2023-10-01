package leetcode.contest.c365

import utils.print

fun main() {
    val s = SolutionD()
    s.countVisitedNodes(listOf(1, 2, 0, 0)).print()
    s.countVisitedNodes(listOf(1, 2, 3, 4, 0)).print()
}

class SolutionD {
    fun countVisitedNodes(edges: List<Int>): IntArray {
        val n = edges.size

        val ans = IntArray(n)

        val map = HashMap<Int, Int>()
        fun dfs(c: Int, step: Int) {
            if (ans[c] != 0) {
                val max = map.values.maxOrNull()!!
                map.forEach { k, v ->
                    ans[k] = ans[c] + max - v + 1
                }
                return
            }
            if (c in map.keys) {
                val s = map[c]!!
                val max = map.values.maxOrNull()!!
                map.forEach { k, v ->
                    if (v >= s) {
                        ans[k] = max - s + 1
                    } else {
                        ans[k] = max - v + 1
                    }
                }
                return
            }
            map[c] = step
            dfs(edges[c], step + 1)
        }

        for (i in 0 until n) {
            map.clear()
            if (ans[i] == 0)
                dfs(i, 0)

            ans.print()
        }

        return ans
    }
}