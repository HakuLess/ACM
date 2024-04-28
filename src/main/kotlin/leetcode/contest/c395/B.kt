package leetcode.contest.c395

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumAddedInteger(intArrayOf(4, 20, 16, 12, 8), intArrayOf(14, 18, 10)).print()
}

class SolutionB {
    fun minimumAddedInteger(nums1: IntArray, nums2: IntArray): Int {
        nums2.sortDescending()
        nums1.sortDescending()

//        nums2.joinToString().print()
//        nums1.joinToString().print()

        for (i in 0..2) {
            // 以 i 为 base
            val diff = nums2[0] - nums1[i]

            if (nums2.size == 1) {
                return diff
            }

            var cur = 1
            for (j in i + 1 until nums1.size) {
                if (nums2[cur] == nums1[j] + diff) {
//                    println("$diff with $cur")
                    cur++
                }
                if (cur !in nums2.indices) {
                    return diff
                }
            }
        }

        return -1
    }
}