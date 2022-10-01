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
        for (j in 0 until 32) {
            var zc = 0
            var oc = 0
            for (k in nums1.indices) {
                if (nums1[k] % 2 == 0) {
                    zc += nums2.size
                } else {
                    oc += nums2.size
                }
                nums1[k] /= 2
            }
            for (k in nums2.indices) {
                if (nums2[k] % 2 == 0) {
                    zc += nums1.size
                } else {
                    oc += nums1.size
                }
                nums2[k] /= 2
            }
            if (oc % 2 == 1) {
                ans += (1 shl j)
            }
        }
        return ans
    }
}