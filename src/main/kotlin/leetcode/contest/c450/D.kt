package leetcode.contest.c450

import utils.Tree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // 12 11
    s.minimumWeight("[[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]]".toGrid(), "[[2,3,4],[0,2,5]]".toGrid()).print()
}

class SolutionD {
    fun minimumWeight(edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val n = edges.size + 1
        val tree = Tree(n, edges)

        val ans = ArrayList<Int>()
        for ((src1, src2, dest) in queries) {
            tree.apply {
                val lca1 = findLca(src1, dest)
                val lca2 = findLca(src2, dest)
                val lca3 = findLca(src1, src2)
//                println("$lca1 $lca2 $lca3")
                val total = dist[src1] + dist[src2] + dist[dest] - dist[lca1] - dist[lca2] - dist[lca3]
                ans.add(total)
            }
        }

        return ans.toIntArray()
    }
}