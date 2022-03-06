package leetcode.contest.c283

import utils.TreeNode

class SolutionC {
    fun createBinaryTree(descriptions: Array<IntArray>): TreeNode? {
        val seen = HashMap<Int, TreeNode>()
        val p = IntArray(100005)
        descriptions.forEach {
            val parent = seen.getOrDefault(it[0], TreeNode(it[0]))
            val child = seen.getOrDefault(it[1], TreeNode(it[1]))
            if (it[2] == 1) {
                parent.left = child
            } else {
                parent.right = child
            }
            p[it[0]]++
            p[it[1]] = -10000
            seen[it[0]] = parent
            seen[it[1]] = child
        }
        for (i in p.indices) {
            if (p[i] > 0) {
                return seen[i]
            }
        }
        return null
    }
}