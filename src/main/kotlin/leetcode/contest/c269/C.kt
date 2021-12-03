package leetcode.contest.c269

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumDeletions(intArrayOf(2, 10, 7, 5, 4, 1, 8, 6)).print()
//    s.minimumDeletions(intArrayOf(41, -47, -26, 57, 82, -23, 47, 52, 42, 79, 2, 77, 0, -4, 1, -99, -57, 72, -95))
//        .print()
}

class SolutionC {
    fun minimumDeletions(nums: IntArray): Int {
        val n = nums.size
//        val minIndex = nums.indexOf(nums.min()!!)
        val minIndex = nums.indexOf(nums.minOrNull()!!)
//        val maxIndex = nums.indexOf(nums.max()!!)
        val maxIndex = nums.indexOf(nums.maxOrNull()!!)
        val l = minOf(minIndex, maxIndex)
        val r = maxOf(minIndex, maxIndex)
        println("$l $r")
        return minOf(r + 1, n - l, l + 1 + n - r)
    }
}