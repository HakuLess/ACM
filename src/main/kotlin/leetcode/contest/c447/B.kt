package leetcode.contest.c447

import utils.UFS
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.pathExistenceQueries(4, intArrayOf(2, 5, 6, 8), 2, "[[0,1],[0,2],[1,3],[2,3]]".toGrid()).print()
}

class SolutionB {
    fun pathExistenceQueries(n: Int, nums: IntArray, maxDiff: Int, queries: Array<IntArray>): BooleanArray {
        val ufs = UFS(n)
        for (i in 0 until nums.lastIndex) {
            val a = nums[i]
            val b = nums[i + 1]
            if (b - a <= maxDiff) {
                ufs.union(i, i + 1)
            }
        }
        return queries.map {
            val (a, b) = it
            ufs.find(a) == ufs.find(b)
        }.toBooleanArray()
    }
}