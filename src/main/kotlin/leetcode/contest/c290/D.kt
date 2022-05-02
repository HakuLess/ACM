package leetcode.contest.c290

import utils.SortedList
import utils.print
import utils.toGrid
import utils.toSortedList

fun main() {
    val s = SolutionD()
    s.fullBloomFlowers("[[1,6],[3,7],[9,12],[4,13]]".toGrid(), intArrayOf(2, 3, 7, 11)).print()
    s.fullBloomFlowers("[[1,10],[3,3]]".toGrid(), intArrayOf(3, 3, 2)).print()
}

class SolutionD {
    fun fullBloomFlowers(flowers: Array<IntArray>, persons: IntArray): IntArray {
        val start = flowers.map { it[0] }.toSortedList()
        val end = flowers.map { it[1] }.toSortedList()
        val n = flowers.size
        return persons.map {
            n - start.largerThanAndEqual(it + 1) - end.smallerThanAndEqual(it - 1)
        }.toIntArray()
    }
}