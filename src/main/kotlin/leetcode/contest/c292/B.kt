package leetcode.contest.c292

import utils.TreeNode
import utils.print
import utils.toTree

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
fun main() {
    val s = SolutionB()
    s.averageOfSubtree(arrayOf(4, 8, 5, 0, 1, null, 6).toTree()).print()
}

class SolutionB {
    fun averageOfSubtree(root: TreeNode?): Int {
        var ans = 0
        fun dfs(root: TreeNode?): Pair<Int, Int> {
            if (root == null) return Pair(0, 0)
            val l = dfs(root.left)
            val r = dfs(root.right)
            val sum = root.`val` + l.first + r.first
            val c = 1 + l.second + r.second
            if (sum / c == root.`val`) {
                ans++
            }
            return Pair(sum, c)
        }
        dfs(root)

        return ans
    }
}