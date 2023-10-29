package leetcode.contest.c369

import utils.*

fun main() {
    val s = SolutionD()
//    s.maximumPoints("[[0,1],[1,2],[2,3]]".toGrid(), intArrayOf(10, 10, 3, 3), 5).print()
//    s.maximumPoints("[[1,0],[0,2],[1,3]]".toGrid(), intArrayOf(9, 3, 8, 9), 0).print()
//    s.maximumPoints(
//        "[[0,1],[0,2],[1,3],[3,4],[0,5],[6,3],[5,7],[3,8],[9,7]]".toGrid(),
//        intArrayOf(0, 5, 10, 5, 6, 5, 0, 2, 0, 0),
//        7
//    ).print()

    // 8
    s.maximumPoints(
        "[[0,1],[0,2],[3,2],[0,4]]".toGrid(),
        intArrayOf(5, 6, 8, 7, 4),
        7
    ).print()
}

class SolutionD {
    fun maximumPoints(edges: Array<IntArray>, coins: IntArray, k: Int): Int {
        val n = coins.size
        val seen = Array(n) { IntArray(18) { -1 } }
        val g = Array(n) { mutableListOf<Int>() }

        for (e in edges) {
            val (v, w) = e
            g[v].add(w)
            g[w].add(v)
        }

        fun dfs(i: Int, offset: Int, pre: Int): Int {
            val ans = seen[i][offset]
            if (ans != -1) {
                return ans
            }
            var res1 = (coins[i] shr offset) - k
            var res2 = coins[i] shr (offset + 1)
            for (ch in g[i]) {
                if (ch != pre) {
                    // 不右移
                    res1 += dfs(ch, offset, i)
                    if (offset < 17) {
                        // 右移
                        res2 += dfs(ch, offset + 1, i)
                    }
                }
            }
            seen[i][offset] = maxOf(res1, res2)
            return seen[i][offset]
        }

        return dfs(0, 0, -1)
    }
}