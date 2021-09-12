package leetcode.contest.c258

import utils.NTreeNode
import utils.print

fun main() {
    val s = Solution5870()
    s.smallestMissingValueSubtree(intArrayOf(-1, 0, 0, 2), intArrayOf(1, 2, 3, 4)).print()
//    s.smallestMissingValueSubtree(intArrayOf(-1, 0, 1, 0, 3, 3), intArrayOf(5, 4, 6, 2, 1, 3)).print()
//    s.smallestMissingValueSubtree(intArrayOf(-1, 2, 3, 0, 2, 4, 1), intArrayOf(2, 3, 4, 5, 6, 7, 8)).print()
}

class Solution5870 {
    fun smallestMissingValueSubtree(parents: IntArray, nums: IntArray): IntArray {
        val n = parents.size
        val nodeList = Array<NTreeNode>(n) { i -> NTreeNode(nums[i], i) }

        for (i in parents.indices) {
            if (parents[i] != -1) {
                nodeList[parents[i]].children.add(nodeList[i])
                nodeList[i].parent = nodeList[parents[i]]
            }
        }

        val ans = IntArray(n) { 1 }
        fun dfs(root: NTreeNode): ArrayList<Int> {
            val cur = ArrayList<Int>()
            root.children.forEach {
                cur.addAll(dfs(it))
            }
            cur.add(root.`val`)
            // println("${root.`val`}: ${cur.joinToString()}")
//            ans[root.index] = firstMissingPositive(cur.toIntArray())
            return cur
        }
        dfs(nodeList[0])
        return ans
    }
}