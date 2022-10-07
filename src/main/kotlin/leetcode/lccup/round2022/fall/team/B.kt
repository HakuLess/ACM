package leetcode.lccup.round2022.fall.team

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
class SolutionB {
    fun expandBinaryTree(root: TreeNode?): TreeNode? {
        fun dfs(root: TreeNode?) {
            if (root == null) return
            dfs(root.left)
            dfs(root.right)
            if (root.left != null) {
                val l = root.left
                val newL = TreeNode(-1)
                root.left = newL
                newL.left = l
            }
            if (root.right != null) {
                val r = root.right
                val newR = TreeNode(-1)
                root.right = newR
                newR.right = r
            }
        }
        dfs(root)
        return root
    }
}