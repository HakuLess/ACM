package leetcode.contest.b154

import utils.print

fun main() {
    val s = SolutionB()
    s.uniqueXorTriplets(intArrayOf(3, 1, 2, 4)).print()
    s.uniqueXorTriplets(intArrayOf(1, 2)).print()
}

class SolutionB {
    fun uniqueXorTriplets(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return 1
        if (n == 2) return 2
        var cur = 1
        while (cur <= n) {
//            println("$cur with $n")
            cur *= 2
        }
        return cur
    }
}