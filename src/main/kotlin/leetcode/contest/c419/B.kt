package leetcode.contest.c419

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
    fun kthLargestPerfectSubtree(root: TreeNode?, k: Int): Int {
        val sizes = mutableListOf<Int>()

        // 后序遍历递归函数，返回 (是否为完美二叉树, 树的高度, 树的大小)
        fun dfs(node: TreeNode?): Triple<Boolean, Int, Int> {
            if (node == null) {
                return Triple(true, 0, 0)  // 空树是完美二叉树，高度为 0，大小为 0
            }

            val (leftPerfect, leftHeight, leftSize) = dfs(node.left)
            val (rightPerfect, rightHeight, rightSize) = dfs(node.right)

            val isPerfect = leftPerfect && rightPerfect && leftHeight == rightHeight
            val height = leftHeight + 1
            val size = leftSize + rightSize + 1

            if (isPerfect) {
                sizes.add(size)  // 记录完美二叉子树的大小
            }

            return Triple(isPerfect, height, size)
        }

        dfs(root)

        // 按大小降序排列
        sizes.sortDescending()

        // 返回第 k 大的完美二叉子树的大小，如果不存在则返回 -1
        return if (k <= sizes.size) sizes[k - 1] else -1
    }
}