package leetcode.contest.c308

import utils.print
import utils.printInt
import java.util.*

fun main() {
    val s = SolutionA()
    s.answerQueries(intArrayOf(4, 5, 2, 1), intArrayOf(3, 10, 21)).print()
    s.answerQueries(intArrayOf(2, 3, 4, 5), intArrayOf(1)).print()
}

class SolutionA {
    fun answerQueries(nums: IntArray, queries: IntArray): IntArray {
        nums.sort()
        val tm = TreeMap<Int, Int>()
        var sum = 0
        for (i in nums.indices) {
            sum += nums[i]
            tm[sum] = i + 1
        }
        return queries.map {
            tm[tm.floorKey(it) ?: 0] ?: 0
        }.toIntArray()
    }
}