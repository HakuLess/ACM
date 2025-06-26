package leetcode.contest.c455

import utils.Tree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.minIncrease(3, "[[0,1],[0,2]]".toGrid(), intArrayOf(2, 1, 3)).print()
    s.minIncrease(5, "[[0,4],[0,1],[1,2],[1,3]]".toGrid(), intArrayOf(3, 4, 1, 1, 7)).print()
}

class SolutionC {
    fun minIncrease(n: Int, edges: Array<IntArray>, cost: IntArray): Int {

        val tree = Tree(n, edges)

        fun dfs(u: Int, parent: Int): Pair<Long, Int> {
            var curMax = 0L
            var ans = 0
            val children = mutableListOf<Pair<Long, Int>>()

            for (v in tree.adj[u]) {
                if (v.first != parent) {
                    val (subMax, subOps) = dfs(v.first, u)
                    children.add(Pair(subMax, subOps))
                    curMax = maxOf(curMax, subMax)
                }
            }

            for ((subMax, subAns) in children) {
                if (subMax < curMax) {
                    // 子树的根节点进行添加，每个子树与最大值不同场景，仅需增加1
                    ans++
                }
                ans += subAns
            }

            // 返回局部最大值 & 增加部分
            return Pair(curMax + cost[u], ans)
        }

        return dfs(0, -1).second
    }
}