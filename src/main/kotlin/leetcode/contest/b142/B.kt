package leetcode.contest.b142

import utils.NTreeNode
import utils.print

fun main() {
    val s = SolutionB()
    s.findSubtreeSizes(intArrayOf(-1, 0, 0, 1, 1, 1), "abaabc").print()
}

class SolutionB {
    fun findSubtreeSizes(parent: IntArray, s: String): IntArray {
        val n = parent.size
        val ans = IntArray(n) { 1 }

        val nodeList = ArrayList<NTreeNode>()
        for (i in 0 until n) {
            val node = NTreeNode(`val` = s[i] - 'a', index = i)
            nodeList.add(node)
        }
        for (i in parent.indices) {
            if (parent[i] in nodeList.indices) {
                nodeList[i].parent = nodeList[parent[i]]
                nodeList[i].parent!!.children.add(nodeList[i])
            }
        }

        for (i in 1 until n) {
            val child = nodeList[i]
            var ancestor = nodeList[i].parent
            while (ancestor != null && ancestor.`val` != child.`val`) {
                ancestor = ancestor.parent
            }

            if (ancestor != null) {
                child.parent!!.children.remove(child)
                ancestor.children.add(child)
            }
        }


        fun dfs(node: NTreeNode, index: Int): Int {
            var tmp = 1
            node.children.forEach {
                tmp += dfs(it, it.index)
            }
            ans[index] = tmp
            return tmp
        }

        dfs(nodeList[0], 0)

        return ans
    }
}