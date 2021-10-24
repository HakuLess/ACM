package leetcode.contest.c264

import utils.TreeNode
import utils.count
import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = Solution5908()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0, 2, 0)).print()
    s.countHighestScoreNodes(intArrayOf(-1, 2, 0)).print()
}

class Solution5908 {
    fun countHighestScoreNodes(parents: IntArray): Int {
        val n = parents.size
        val root = TreeNode(0)

        val map = HashMap<Int, ArrayList<Int>>()
        for (i in parents.indices) {
            map[parents[i]] = map.getOrDefault(parents[i], arrayListOf())
            map[parents[i]]!!.add(i)
        }

        // 通过parents数组，构建二叉树
        val queue: Queue<Pair<Int, TreeNode>> = LinkedList<Pair<Int, TreeNode>>()
        queue.add(Pair(0, root))
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (k in 0 until size) {
                val item = queue.poll()
                val node = item.second

                map[item.first]?.forEach {
                    val next = TreeNode(it)
                    if (node.left == null) node.left = next
                    else if (node.right == null) node.right = next
                    queue.offer(Pair(it, next))
                }
            }
        }

        // 获取所有节点的儿子数（包含自己）
        val seen = HashMap<TreeNode, Int>()
        fun TreeNode?.count(): Int = if (this == null) {
            0
        } else if (this in seen) {
            seen[this]!!
        } else {
            (1 + this.left.count() + this.right.count()).also {
                seen[this] = it
            }
        }

        var max = -1L
        var ans = 0
        fun dfs(node: TreeNode?) {
            if (node == null) return

            var left = node.left.count()
            var right = node.right.count()
            var top = n - left - right - 1
            if (left == 0) left = 1
            if (right == 0) right = 1
            if (top == 0) top = 1
            val cur = top.toLong() * left * right
            if (cur > max) {
                max = cur
                ans = 1
            } else if (cur == max) {
                ans++
            }

            dfs(node.left)
            dfs(node.right)
        }

        dfs(root)
        return ans
    }
}