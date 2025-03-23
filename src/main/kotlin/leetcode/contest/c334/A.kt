package leetcode.contest.c334

import utils.preSumArray
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionA()
    s.leftRightDifference(intArrayOf(10, 4, 8, 3)).print()
}

class SolutionA {
    fun leftRightDifference(nums: IntArray): IntArray {
        val sum = nums.sum()
        val pre = nums.preSumArray(false)
        val ans = ArrayList<Int>()
        for (i in nums.indices) {
            val left = pre[i].toInt() - nums[i]
            val right = sum - pre[i].toInt()
//            println("$i : $left $right")
            ans.add(abs(left - right))
        }
        return ans.toIntArray()
    }
}