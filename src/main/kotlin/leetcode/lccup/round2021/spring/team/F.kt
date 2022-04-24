package leetcode.lccup.round2021.spring.team

import utils.TreeNode
import utils.print
import utils.printInt
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
    val s = SolutionF()
    s.getMaxLayerSum(arrayOf(6, 0, 3, null, 8).toTree()).print()
}

class SolutionF {
    fun getMaxLayerSum(root: TreeNode?): Int {

        val map = HashMap<Int, Int>()
        fun dfs(root: TreeNode?, level: Int) {
            if (root == null) return
            map[level] = map.getOrDefault(level, 0) + root.`val`
            dfs(root.left, level + 1)
            dfs(root.right, level + 1)
        }
        dfs(root, 0)
        map.printInt()

        return 0
    }
}