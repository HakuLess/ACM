package leetcode.lccup.last

import utils.TreeNode

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
class SolutionLCP26 {
    fun navigation(root: TreeNode?): Int {
        var ans = 0
        fun dfs(root: TreeNode?): Int {
            if (root == null) return 0
            val l = dfs(root.left)
            val r = dfs(root.right)
            return if (root.left != null && root.right != null) {
                if (l == 0 && r == 0) {
                    ans++
                    1
                } else if (l == 0 || r == 0) {
                    1
                } else {
                    2
                }
            } else if (root.left == null) {
                r
            } else {
                l
            }
        }
        if (root == null) return 0
        val l = dfs(root.left)
        val r = dfs(root.right)
        return if (l + r >= 2) {
            ans
        } else {
            ans + 1
        }
    }
}