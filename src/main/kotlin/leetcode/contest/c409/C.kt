package leetcode.contest.c409

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.shortestDistanceAfterQueries(5, "[[2,4],[0,2],[0,4]]".toGrid()).print()
}

class SolutionC {
    fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
        val set = TreeSet<Int>()
        for (i in 1 until n) {
            set.add(i)
        }
        val ans = IntArray(queries.size)
        for (i in queries.indices) {
            val (a, b) = queries[i]
            val remove = set.subSet(a + 1, true, b - 1, true)
            set.removeAll(remove.toHashSet())
            ans[i] = set.size
        }
        return ans
    }
}