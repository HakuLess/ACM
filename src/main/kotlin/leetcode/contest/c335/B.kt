package leetcode.contest.c335

import utils.TreeNode
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
class SolutionB {
    fun kthLargestLevelSum(root: TreeNode?, k: Int): Long {
        if (root == null) return -1
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.offer(root)
        val ans = ArrayList<Long>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            var tmp = 0L
            for (i in 0 until size) {
                val item = queue.poll() ?: continue
                tmp += item.`val`
                if (item.left != null)
                    queue.offer(item.left)
                if (item.right != null)
                    queue.offer(item.right)
            }
            ans.add(tmp)
        }
        if (k - 1 !in ans.indices) return -1
        return ans.sortedDescending()[k - 1]
    }
}