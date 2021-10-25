package leetcode.contest.c264

import utils.TreeNode
import utils.count
import utils.print
import utils.toTree

fun main() {
    val s = Solution5908()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0, 2, 0)).print()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0)).print()
}

class Solution5908 {
    fun countHighestScoreNodes(parents: IntArray): Int {
        val root = parents.toTree()

        // 获取所有节点的儿子数（包含自己）
        val n = root.count()

        var max = -1L
        var ans = 0
        fun dfs(node: TreeNode?) {
            if (node == null) return

            var left = node.left?.cnt ?: 0
            var right = node.right?.cnt ?: 0
            var top = n - left - right - 1
            if (left == 0) left = 1
            if (right == 0) right = 1
            if (top == 0) top = 1
            val cur = top.toLong() * left * right
            if (cur > max) {
                max = cur
                ans = 1
            } else if (cur == max) {
                ans++
            }

            dfs(node.left)
            dfs(node.right)
        }

        dfs(root)
        return ans
    }
}