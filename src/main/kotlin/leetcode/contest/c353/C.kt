package leetcode.contest.c353

import utils.print

fun main() {
    val s = SolutionC()
//    s.maxNonDecreasingLength(intArrayOf(1, 3, 2, 1), intArrayOf(2, 2, 3, 4)).print()
    s.maxNonDecreasingLength(intArrayOf(8, 7, 4), intArrayOf(13, 4, 4)).print()
}

class SolutionC {
    fun maxNonDecreasingLength(nums1: IntArray, nums2: IntArray): Int {
        val n = nums1.size
        var dp = IntArray(3) { 1 }
        var ans = 1
        for (i in 1 until n) {
            val nextDp = IntArray(3) { 1 }
            if (nums1[i] >= nums1[i - 1]) {
                nextDp[1] = maxOf(nextDp[1], dp[1] + 1)
            }
            if (nums1[i] >= nums2[i - 1]) {
                nextDp[1] = maxOf(nextDp[1], dp[2] + 1)
            }
            if (nums2[i] >= nums1[i - 1]) {
                nextDp[2] = maxOf(nextDp[2], dp[1] + 1)
            }
            if (nums2[i] >= nums2[i - 1]) {
                nextDp[2] = maxOf(nextDp[2], dp[2] + 1)
            }
            dp = nextDp
            dp.print()
            ans = maxOf(ans, dp.maxOf { it })
//            ans = maxOf(ans, dp.max()!!)
        }
        return ans
//        return dp.max()!!
    }
}