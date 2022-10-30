package leetcode.contest.c317

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

    s.treeQueries(
        arrayOf(5, 8, 9, 2, 1, 3, 7, 4, 6).toTree(), intArrayOf(3, 2, 4, 8)
    ).print()

    s.treeQueries(
        arrayOf(1, null, 5, 3, null, 2, 4).toTree(), intArrayOf(3, 5, 4, 2, 4)
    ).print()
}

class SolutionD {
    fun treeQueries(root: TreeNode?, queries: IntArray): IntArray {

        // 结点值 & 层级
        val mapLeft = HashMap<Int, Int>()
        val mapRight = HashMap<Int, Int>()

        fun dfsLeft(root: TreeNode?, depth: Int, curMaxDep: Int): Int {
            if (root == null) return curMaxDep
            val idx = root.`val`
            mapLeft[idx] = curMaxDep
            var maxDep = maxOf(curMaxDep, depth)
            maxDep = dfsLeft(root.left, depth + 1, maxDep)
            maxDep = dfsLeft(root.right, depth + 1, maxDep)
            return maxDep
        }

        fun dfsRight(root: TreeNode?, depth: Int, curMaxDep: Int): Int {
            if (root == null) return curMaxDep
            val idx = root.`val`
            mapRight[idx] = curMaxDep
            var maxDep = maxOf(curMaxDep, depth)
            maxDep = dfsRight(root.right, depth + 1, maxDep)
            maxDep = dfsRight(root.left, depth + 1, maxDep)
            return maxDep
        }

        dfsLeft(root, 0, 0)
        dfsRight(root, 0, 0)

        return queries.map {
            maxOf(mapLeft[it]!!, mapRight[it]!!)
        }.toIntArray()
    }
}