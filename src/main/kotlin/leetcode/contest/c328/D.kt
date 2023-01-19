package leetcode.contest.c328

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 24
    s.maxOutput(6, "[[0,1],[1,2],[1,3],[3,4],[3,5]]".toGrid(), intArrayOf(9, 8, 7, 6, 10, 5)).print()
    // 39
    s.maxOutput(
        9,
        "[[1,7],[5,2],[2,3],[6,0],[0,4],[4,7],[7,3],[3,8]]".toGrid(),
        intArrayOf(6, 13, 8, 10, 4, 5, 8, 3, 12)
    ).print()
}

class SolutionD {
    fun maxOutput(n: Int, edges: Array<IntArray>, price: IntArray): Long {
        val g = Array<ArrayList<Int>>(n) { ArrayList() }
        for ((x, y) in edges) {
            g[x].add(y)
            g[y].add(x)
        }
        g[0].add(-1)

        var ans = 0L

        // 当前节点
        // 上一节点值
        // 返回算最后叶子总值 + 不算最后叶子总值
        fun dfs(x: Int, pre: Int = -1): Pair<Long, Long> {
//            println("enter $x from $pre")
            val p = price[x].toLong()

            // 叶子节点直接返回
            if (g[x].size == 1) {
                return Pair(p, 0L)
            }

            // 算叶子节点最大值
            var max1 = 0L
            // 不算叶子节点最大值
            var max2 = 0L

            g[x].forEach {
                if (it == pre) return@forEach
                val (s1, s2) = dfs(it, x)

                // 之前不算叶子 当前 之后算叶子
                ans = if (max2 == 0L) {
                    maxOf(ans, s1)
                } else {
                    maxOf(ans, max2 + p + s1)
                }

                // 之前算叶子 当前 之后不算叶子
                ans = maxOf(ans, max1 + p + s2)

                // 更新前序可能的最大值
                max1 = maxOf(max1, s1)
                max2 = maxOf(max2, s2)
            }
            return Pair(max1 + p, max2 + p)
        }
        dfs(0)
        return ans
    }
}