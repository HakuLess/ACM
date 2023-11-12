package leetcode.contest.c371

import utils.print

fun main() {
    val s = SolutionC()
    s.minOperations(intArrayOf(1, 2, 7), intArrayOf(4, 5, 3)).print()
}

class SolutionC {
    fun minOperations(nums1: IntArray, nums2: IntArray): Int {
        val n = nums1.size
        var a = nums1[n - 1]
        var b = nums2[n - 1]

        var tmp0 = 0
        for (i in 0 until n - 1) {
            val x = nums1[i]
            val y = nums2[i]

            if (minOf(x, y) > minOf(a, b)) return -1
            if (maxOf(x, y) > maxOf(a, b)) return -1

            if (y <= a && x <= b && (x > a || y > b)) {
                tmp0++
            }
        }

        a = nums2[n - 1]
        b = nums1[n - 1]
        var tmp1 = 0
        for (i in 0 until n - 1) {
            val x = nums1[i]
            val y = nums2[i]

            if (minOf(x, y) > minOf(a, b)) return -1
            if (maxOf(x, y) > maxOf(a, b)) return -1

            if (y <= a && x <= b && (x > a || y > b)) {
                tmp1++
            }
        }
//        println("$tmp0 $tmp1")
        return minOf(tmp0, tmp1 + 1)
    }
}