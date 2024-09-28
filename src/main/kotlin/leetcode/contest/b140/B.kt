package leetcode.contest.b140

import utils.print

fun main() {
    val s = SolutionB()
    s.maximumTotalSum(intArrayOf(2, 3, 4, 3)).print()
}

class SolutionB {
    fun maximumTotalSum(maximumHeight: IntArray): Long {
        val sorted = maximumHeight.sortedDescending().map { it.toLong() }
        var ans = 0L
        var cur = Long.MAX_VALUE
        for (item in sorted) {
            ans += minOf(cur, item)
            if (minOf(cur, item) <= 0) return -1
            cur = minOf(cur, item) - 1
        }
        return ans
    }
}