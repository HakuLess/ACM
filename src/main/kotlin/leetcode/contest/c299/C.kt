package leetcode.contest.c299

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    s.maximumsSplicedArray(intArrayOf(60, 60, 60), intArrayOf(10, 90, 10)).print()
}

class SolutionC {
    fun maximumsSplicedArray(nums1: IntArray, nums2: IntArray): Int {
        val pre1 = nums1.preSumArray(true)
        val pre2 = nums2.preSumArray(true)
//        pre1.print()
        var min = Long.MAX_VALUE / 2
        var max = Long.MIN_VALUE / 2
        for (i in pre1.indices) {
            for (j in i + 1 until pre1.size) {
                val diff1 = pre1[i] - pre1[j]
                val diff2 = pre2[i] - pre2[j]

                min = minOf(min, diff1 - diff2)
                max = maxOf(max, diff1 - diff2)
            }
        }
        return maxOf(nums1.sum() + max, nums2.sum() - min).toInt()
    }
}