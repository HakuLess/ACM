package leetcode.contest.c426

import utils.*

fun main() {
    val s = SolutionC()
    s.maxTargetNodes("[[0,1],[0,2],[2,3],[2,4]]".toGrid(), "[[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]".toGrid(), 2)
        .print()
}

class SolutionC {
    fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>, k: Int): IntArray {
        val n = edges1.size + 1
        val m = edges2.size + 1

        val tree1 = Tree(n, edges1)
        val tree2 = Tree(m, edges2)

        val max = (0 until m).maxOf { tree2.distanceMax(it, -1, k - 1) }

        val ans = ArrayList<Int>()
        for (i in 0 until n) {
            ans.add(tree1.distanceMax(i, -1, k) + max)
        }
        return ans.toIntArray()
    }
}