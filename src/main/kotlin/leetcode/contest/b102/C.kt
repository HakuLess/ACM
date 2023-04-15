package leetcode.contest.b102

import utils.TreeNode
import utils.print
import utils.printInt
import utils.toTree
import java.util.*
import kotlin.collections.HashMap

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
    val s = SolutionC()
    s.replaceValueInTree(arrayOf(5, 4, 9, 1, 10, null, 7).toTree()).toString().print()
}

class SolutionC {
    fun replaceValueInTree(root: TreeNode?): TreeNode? {

        val sum = HashMap<Int, Int>()

        val queue: Queue<TreeNode?> = LinkedList<TreeNode>()
        queue.offer(root)

        var step = 0
        while (queue.isNotEmpty()) {
            step++
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll() ?: continue
                queue.offer(item.left)
                queue.offer(item.right)

                sum[step] = sum.getOrDefault(step, 0) + item.`val`
            }
        }

        val map = HashMap<TreeNode, Int>()

        // 亲兄弟值
        fun dfs(root: TreeNode?, v: Int, step: Int) {
            if (root == null) {
                return
            }
            // 亲兄弟值 + 自己值
            dfs(root.left, root.right?.`val` ?: 0, step + 1)
            dfs(root.right, root.left?.`val` ?: 0, step + 1)

            map[root] = sum[step]!! - v - root.`val`
        }

        dfs(root, 0, 1)

        map.forEach { t, u ->
            t.`val` = u
        }
        return root
    }
}