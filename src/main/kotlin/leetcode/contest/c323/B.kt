package leetcode.contest.c323

import utils.print
import utils.printInt
import kotlin.math.sqrt

fun main() {
    val s = SolutionB()
    s.longestSquareStreak(intArrayOf(4, 3, 6, 16, 8, 2)).print()
    s.longestSquareStreak(intArrayOf(2, 3, 5, 6, 7)).print()
}

class SolutionB {
    fun longestSquareStreak(nums: IntArray): Int {
        nums.sort()
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val sq = sqrt(nums[i].toDouble()).toInt()
            if (sq * sq == nums[i] && sq in map.keys) {
                map[nums[i]] = map[sq]!! + 1
            } else {
                map[nums[i]] = 1
            }
        }
        map.printInt()
//        return map.values.max()!!.let {
        return map.values.maxOrNull()!!.let {
            if (it > 1) {
                it
            } else {
                -1
            }
        }
    }
}