package leetcode.contest.c424

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.isZeroArray(intArrayOf(1, 0, 1), "[[0,2]]".toGrid()).print()
}

class SolutionB {
    fun isZeroArray(nums: IntArray, queries: Array<IntArray>): Boolean {
        val n = nums.size
        val delta = IntArray(n + 1)

        for (query in queries) {
            val l = query[0]
            val r = query[1]
            delta[l] += 1
            if (r + 1 < n) {
                delta[r + 1] -= 1
            }
        }

        delta.print()

        var curDiff = 0
        for (i in 0 until n) {
            curDiff += delta[i]
            if (nums[i] > curDiff) return false
        }

        return true
    }
}