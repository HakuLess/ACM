package leetcode.contest.b109

import utils.print

fun main() {
    val s = SolutionC()
    // 13
//    s.maxScore(intArrayOf(2, 3, 6, 1, 9, 2), 5).print()
    // 470
    s.maxScore(intArrayOf(8, 50, 65, 85, 8, 73, 55, 50, 29, 95, 5, 68, 52, 79), 74).print()
}

class SolutionC {
    fun maxScore(nums: IntArray, x: Int): Long {
        var cur1 = 0L
        var cur2 = 0L

        if (nums[0] % 2 == 0) {
            cur2 = nums[0].toLong()
            cur1 = nums[0].toLong() - x
        } else {
            cur1 = nums[0].toLong()
            cur2 = nums[0].toLong() - x
        }

        for (i in 1 until nums.size) {
            val item = nums[i]
            if (nums[i] % 2 == 0) {
                cur2 += item
                cur2 = maxOf(cur1 + item - x, cur2)
            } else {
                cur1 += item
                cur1 = maxOf(cur2 + item - x, cur1)
            }
            println("$i: $cur1 $cur2")
        }
        return maxOf(cur1, cur2)
    }
}