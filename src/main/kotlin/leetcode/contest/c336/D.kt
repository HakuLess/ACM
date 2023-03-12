package leetcode.contest.c336

import utils.print
import utils.printInt
import utils.toGrid
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    s.findMinimumTime("[[2,3,1],[4,5,1],[1,5,2]]".toGrid()).print()
    s.findMinimumTime("[[1,3,2],[2,5,3],[5,6,2]]".toGrid()).print()
}

class SolutionD {
    fun findMinimumTime(tasks: Array<IntArray>): Int {
        tasks.sortBy { it[1] }
        val run = BooleanArray(2002) { false }
        tasks.forEach {
            var (start, end, d) = it
            for (i in start..end) {
                if (run[i]) d--
            }
            var cur = end
            while (d > 0) {
                if (!run[cur]) {
                    run[cur] = true
                    d--
                }
                cur--
            }
        }
        return run.count { it }
    }
}