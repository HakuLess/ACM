package leetcode.contest.b82

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
class SolutionA {
    fun evaluateTree(root: TreeNode?): Boolean {
        if (root == null) return false
        if (root.left != null && root.right != null) {
            if (root.`val` == 2) {
                return evaluateTree(root.left) or evaluateTree(root.right)
            } else {
                return evaluateTree(root.left) and evaluateTree(root.right)
            }
        } else {
            return root.`val` == 1
        }
    }
}