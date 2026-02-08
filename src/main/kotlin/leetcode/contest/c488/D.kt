package leetcode.contest.c488

import utils.print

fun main() {
    val s = SolutionD()
    // 22
    s.maxScore(intArrayOf(1, 3, 2), intArrayOf(4, 5, 1), 2).print()
    // 26
    s.maxScore(intArrayOf(-2, 0, 5), intArrayOf(-3, 4, -1, 2), 2).print()
    // -7
    s.maxScore(intArrayOf(-3, -2), intArrayOf(1, 2), 2).print()
}

class SolutionD {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {

        val n = nums1.size
        val m = nums2.size
        val inf = Long.MIN_VALUE / 4

        val dp = Array(k + 1) { LongArray(m + 1) { inf } }

        for (j in 0..m) dp[0][j] = 0L

        for (i in 1..n) {
            val newDp = Array(k + 1) { LongArray(m + 1) { inf } }

            for (t in 0..k) {
                newDp[t][0] = if (t == 0) 0L else inf
            }

            for (j in 1..m) {
                for (t in 0..k) {
                    // Skip nums1[i-1]
                    newDp[t][j] = maxOf(newDp[t][j], dp[t][j])

                    // Skip nums2[j-1]
                    newDp[t][j] = maxOf(newDp[t][j], newDp[t][j - 1])

                    // Match
                    if (t > 0 && dp[t - 1][j - 1] != inf) {
                        val score = dp[t - 1][j - 1] + nums1[i - 1].toLong() * nums2[j - 1].toLong()
                        newDp[t][j] = maxOf(newDp[t][j], score)
                    }
                }
            }

            for (t in 0..k) {
                dp[t] = newDp[t]
            }
        }

        return dp[k][m]
    }
}