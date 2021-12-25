package leetcode.normal

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
    val s = Solution1609()
    s.isEvenOddTree(arrayOf(1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2).toTree()).print()
    s.isEvenOddTree(arrayOf(5, 4, 2, 3, 3, 7).toTree()).print()
    s.isEvenOddTree(arrayOf(5, 9, 1, 3, 5, 7).toTree()).print()
}

class Solution1609 {
    fun isEvenOddTree(root: TreeNode?): Boolean {
        if (root == null) return false
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.offer(root)
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            val arr = ArrayList<Int>()
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.left != null)
                    queue.offer(item.left)
                if (item.right != null)
                    queue.offer(item.right)
                arr.add(item.`val`)
            }
            println("$step ${arr.joinToString()} ${arr.sortedDescending().joinToString()}")
            if (step % 2 == 1) {
                if (arr.joinToString() != arr.toSet().sortedDescending().joinToString() || arr.any { it % 2 == 1 }) {
                    return false
                }
            } else {
                if (arr.joinToString() != arr.toSet().sorted().joinToString() || arr.any { it % 2 == 0 }) {
                    return false
                }
            }
        }
        return true
    }
}