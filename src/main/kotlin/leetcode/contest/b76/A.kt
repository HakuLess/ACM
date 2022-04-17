package leetcode.contest.b76

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionA()
    s.findClosestNumber(intArrayOf(2, -1, 1)).print()
}

class SolutionA {
    fun findClosestNumber(nums: IntArray): Int {
        val ans = abs(nums.minByOrNull { abs(it) }!!)
//        val ans = nums.minOf { abs(it) }
        return if (ans in nums) ans else -ans
    }
}