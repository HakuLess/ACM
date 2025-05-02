package leetcode.normal

import utils.TreeNode
import kotlin.concurrent.thread

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
    var i = 0
    thread {
        while (i < 100) {
            i++
            println("${Thread.currentThread().name} $i")
        }
    }
    thread {
        while (i < 100) {
            i++
            println("${Thread.currentThread().name} $i")
        }
    }

    thread {
        Thread.sleep(1000L)
        println("=========== ${Thread.currentThread().name} $i")
    }
}

class Solution230 {
    fun kthSmallest(root: TreeNode?, k: Int): Int {

        var ans = -1
        var leftK = k

        // 返回当前节点数量
        fun dfs(node: TreeNode?) {
            if (node == null) return

            dfs(node.left)
            leftK--
            if (leftK == 0) ans = node.`val`
            dfs(node.right)
        }
        dfs(root)

        return ans
    }
}