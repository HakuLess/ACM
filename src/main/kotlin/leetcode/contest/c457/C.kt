package leetcode.contest.c457

import utils.UFS
import utils.biMin
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    // 3
    s.minTime(2, "[[0,1,3]]".toGrid(), 2).print()
    // 4
    s.minTime(3, "[[0,1,2],[1,2,4]]".toGrid(), 3).print()
    // 0
    s.minTime(3, "[[0,2,5]]".toGrid(), 2).print()
}

class SolutionC {
    fun minTime(n: Int, edges: Array<IntArray>, k: Int): Int {

        fun getCnt(t: Long): Int {
            val ufs = UFS(n)
            var cnt = n
            edges.filter { it[2] > t }.forEach {
                if (ufs.union(it[0], it[1])) {
                    cnt--
                }
            }
            return cnt
        }

        return biMin(0L, Long.MAX_VALUE / 2) {
            val cnt = getCnt(it)
            cnt >= k
        }.toInt()
    }
}