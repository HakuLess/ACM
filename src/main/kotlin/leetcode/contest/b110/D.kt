package leetcode.contest.b110

import utils.print

fun main() {
    val s = SolutionD()
    // 3
//    s.minimumTime(listOf(1, 2, 3), listOf(1, 2, 3), 4).print()
    // -1
//    s.minimumTime(listOf(1, 2, 3), listOf(3, 3, 3), 4).print()
    // 4
//    s.minimumTime(listOf(4, 4, 9, 10), listOf(4, 4, 1, 3), 16).print()
    // 2
//    s.minimumTime(listOf(7, 9, 8, 5, 8, 3), listOf(0, 1, 4, 2, 3, 1), 37).print()

    // 7
//    s.minimumTime(listOf(7, 10, 1, 3, 7, 3, 2), listOf(1, 1, 3, 0, 2, 2, 3), 22).print()
    // 9
    s.minimumTime(listOf(10, 4, 1, 10, 7, 5, 6, 3, 2, 10), listOf(4, 0, 4, 0, 3, 4, 3, 0, 0, 3), 50).print()
}

class SolutionD {
    fun minimumTime(nums1: List<Int>, nums2: List<Int>, x: Int): Int {
        val n = nums1.size
        val p = ArrayList<Pair<Int, Int>>()
        for (i in 0 until n) {
            p.add(Pair(nums2[i], nums1[i]))
        }
        p.sortBy { it.first }
        val dp = IntArray(n + 1)
        for ((x, y) in p) {
            for (i in n - 1 downTo 0) {
                dp[i + 1] = maxOf(dp[i + 1], dp[i] + (i + 1) * x + y)
            }
        }
        val k = nums2.sumBy { it }
        val b = nums1.sumBy { it }
        for (i in 0..n) {
            if (k * i + b - dp[i] <= x) {
                return i
            }
        }
        return -1
    }
}