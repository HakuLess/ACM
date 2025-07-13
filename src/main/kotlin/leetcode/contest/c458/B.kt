package leetcode.contest.c458

import utils.UFS
import utils.biMin
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.minCost(5, "[[0,1,4],[1,2,3],[1,3,2],[3,4,6]]".toGrid(), 2).print()
    s.minCost(4, "[[0,1,5],[1,2,5],[2,3,5]]".toGrid(), 1).print()

    s.minCost(4, "[[0,1,5],[1,2,5],[2,3,5]]".toGrid(), 4).print()
}

class SolutionB {
    fun minCost(n: Int, edges: Array<IntArray>, k: Int): Int {

        return biMin(0L, Long.MAX_VALUE / 4) { maxWeight ->

            val ufs = UFS(n)
            var tmp = n
            edges.forEach {
                val (u, v, w) = it
                if (w <= maxWeight) {
                    if (ufs.union(u, v)) {
                        tmp--
                    }
                }
            }
            tmp <= k
        }.toInt()
    }
}