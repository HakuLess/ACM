package leetcode.contest.b88

import utils.print

fun main() {
    val s = SolutionC()
    s.xorAllNums(intArrayOf(1, 2), intArrayOf(3, 4)).print()
    s.xorAllNums(intArrayOf(2, 1, 3), intArrayOf(10, 2, 5, 0)).print()
}

class SolutionC {
    fun xorAllNums(nums1: IntArray, nums2: IntArray): Int {
        var ans = 0
        if (nums1.size % 2 != 0) {
            nums2.forEach {
                ans = ans xor it
            }
        }
        if (nums2.size % 2 != 0) {
            nums1.forEach {
                ans = ans xor it
            }
        }
        return ans
    }
}