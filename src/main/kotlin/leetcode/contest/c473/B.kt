package leetcode.contest.c473

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.maxAlternatingSum(intArrayOf(1, 2, 3)).print()
}

class SolutionB {
    fun maxAlternatingSum(nums: IntArray): Long {
        val sorted = nums.sortedByDescending { abs(it) }.map { it.toLong() }

        var left = 0
        var right = nums.lastIndex
        val arr = LongArray(nums.size)
        var i = 0
        while (left <= right) {
            if (i % 2 == 0) arr[i++] = sorted[left++]
            else arr[i++] = sorted[right--]
        }

//        arr.print()

        var ans = 0L
        for (j in arr.indices) {
            ans += if (j % 2 == 0) arr[j] * arr[j] else -arr[j] * arr[j]
        }
        return ans
    }
}