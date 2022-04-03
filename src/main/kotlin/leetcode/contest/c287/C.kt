package leetcode.contest.c287

import utils.biMax
import utils.print

fun main() {
    val s = SolutionC()
    s.maximumCandies(intArrayOf(5, 8, 6), 3).print()
    s.maximumCandies(intArrayOf(2, 5), 11).print()
}

class SolutionC {
    fun maximumCandies(candies: IntArray, k: Long): Int {
        var total = 0L
        candies.forEach {
            total += it
        }
        if (total < k) return 0
        return biMax(l = 1L, r = Long.MAX_VALUE / 2) { d ->
            var cur = 0L
            candies.forEach {
                cur += it.toLong() / d
            }
            cur >= k
        }.toInt()
    }
}