package leetcode.contest.c311

import utils.TreeNode
import utils.print
import utils.toTree
import java.util.*
import kotlin.collections.ArrayList

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
    s.reverseOddLevels(arrayOf(2, 3, 5, 8, 13, 21, 34).toTree())
}

class SolutionC {
    fun reverseOddLevels(root: TreeNode?): TreeNode? {
        if (root == null) return null
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.offer(root)
        var step = 0
        val parents = ArrayList<TreeNode>()
        val children = ArrayList<Int>()
        while (queue.isNotEmpty()) {
            step++
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                parents.add(item)
                if (item.left != null) {
                    queue.offer(item.left)
                    children.add(item.left!!.`val`)
                }
                if (item.right != null) {
                    queue.offer(item.right)
                    children.add(item.right!!.`val`)
                }
            }

            children.joinToString().print()

            if (step % 2 == 1 && children.isNotEmpty()) {
                var i = children.lastIndex
                parents.forEach {
                    it.left!!.`val` = children[i]
                    i--
                    it.right!!.`val` = children[i]
                    i--
                }
            }
            children.clear()
            parents.clear()
        }
        return root
    }
}