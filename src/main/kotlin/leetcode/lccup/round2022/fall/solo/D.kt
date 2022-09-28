package leetcode.lccup.round2022.fall.solo

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
    val s = SolutionD()
    s.closeLampInTree(arrayOf(0, null, 0).toTree()).print()
    s.closeLampInTree(arrayOf(1, 1, 1, 1, null, null, 1).toTree()).print()
    s.closeLampInTree(arrayOf(1, null, 1, 0, 0, 1, null, null, null, 0).toTree()).print()
}

class SolutionD {
    fun closeLampInTree(root: TreeNode?): Int {
        val seen = HashMap<Triple<TreeNode?, Int, Int>, Int>()

        fun dfs(root: TreeNode?, target: Int, next: Int): Int {
            if (root == null) return 0
            val key = Triple(root, target, next)
            if (key in seen) return seen[key]!!

            var ans = Int.MAX_VALUE
            if (root.`val` == target) {

                // nothing
                ans = minOf(ans, dfs(root.left, next, next) + dfs(root.right, next, next))

                // 1 + 2
                ans = minOf(ans, 2 + dfs(root.left, 1 - next, 1 - next) + dfs(root.right, 1 - next, 1 - next))

                // 1 + 3
                ans = minOf(ans, 2 + dfs(root.left, 1 - next, next) + dfs(root.right, 1 - next, next))

                // 2 + 3
                ans = minOf(ans, 2 + dfs(root.left, next, 1 - next) + dfs(root.right, next, 1 - next))

            } else {

                // 1
                ans = minOf(ans, 1 + dfs(root.left, next, next) + dfs(root.right, next, next))

                // 2
                ans = minOf(ans, 1 + dfs(root.left, 1 - next, 1 - next) + dfs(root.right, 1 - next, 1 - next))

                // 3
                ans = minOf(ans, 1 + dfs(root.left, 1 - next, next) + dfs(root.right, 1 - next, next))

                // 1 + 2 + 3
                ans = minOf(ans, 3 + dfs(root.left, next, 1 - next) + dfs(root.right, next, 1 - next))
            }

            return ans.also {
                seen[key] = it
            }
        }

        return dfs(root, 0, 0)
    }
}