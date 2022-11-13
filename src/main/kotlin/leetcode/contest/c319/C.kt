package leetcode.contest.c319

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
    s.minimumOperations(arrayOf(1, 4, 3, 7, 6, 8, 5, null, null, null, null, 9, null, 10).toTree()).print()
}

class SolutionC {
    fun minimumOperations(root: TreeNode?): Int {
        if (root == null) return 0
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.offer(root)
        var ans = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            val l = ArrayList<Int>()
            for (i in 0 until size) {
                val item = queue.poll()
                l.add(item.`val`)
                if (item.left != null) {
                    queue.offer(item.left)
                }
                if (item.right != null) {
                    queue.offer(item.right)
                }
            }
            val sorted = l.sorted()
            for (i in sorted.indices) {
                if (l[i] != sorted[i]) {
//                    println("$i before")
//                    l.joinToString().print()
                    ans++
                    l[l.indexOf(sorted[i])] = l[i]
                    l[i] = sorted[i]
//                    l.joinToString().print()
                }
            }
//            println(ans)
        }
        return ans
    }
}