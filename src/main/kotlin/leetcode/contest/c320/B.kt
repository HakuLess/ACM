package leetcode.contest.c320

import utils.TreeNode
import java.util.*

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
    fun closestNodes(root: TreeNode?, queries: List<Int>): List<List<Int>> {
        val ts = TreeSet<Int>()
        fun dfs(node: TreeNode?) {
            if (node == null) return
            ts.add(node.`val`)
            dfs(node.left)
            dfs(node.right)
        }
        dfs(root)
        return queries.map {
            listOf(
                ts.floor(it) ?: -1,
                ts.ceiling(it) ?: -1
            )
        }
    }
}