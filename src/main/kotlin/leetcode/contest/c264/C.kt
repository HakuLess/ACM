package leetcode.contest.c264

import utils.*

fun main() {
    val s = Solution5908()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0, 2, 0)).print()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0)).print()
}

class Solution5908 {
    fun countHighestScoreNodes(parents: IntArray): Int {
        val root = parents.toNTree()

        // 获取所有节点的儿子数（包含自己）
        // 计算根节点时，同时会将所有子节点的cnt进行赋值
        val n = root.count()

        var max = -1L
        var ans = 0
        fun dfs(node: NTreeNode?) {
            if (node == null) return

            var cnt = 0
            var cur = 1L
            node.children.forEach {
                cnt += it.cnt
                cur *= it.cnt
            }
            val top = n - cnt - 1
            if (top > 0) cur *= top
            if (cur > max) {
                max = cur
                ans = 1
            } else if (cur == max) {
                ans++
            }

            node.children.forEach {
                dfs(it)
            }
        }

        dfs(root)
        return ans
    }
}