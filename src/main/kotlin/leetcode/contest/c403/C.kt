package leetcode.contest.c403

import utils.print

fun main() {
    val s = SolutionC()
    s.maximumTotalCost(intArrayOf(1, -2, 3, 4)).print()
    s.maximumTotalCost(intArrayOf(-937)).print()
}

class SolutionC {
    fun maximumTotalCost(nums: IntArray): Long {
        val n = nums.size
        val numsL = nums.map { it.toLong() }.toLongArray()

        // 以i结尾 且当前为 +
        val dp0 = LongArray(n) { Long.MIN_VALUE / 2 }
        dp0[0] = numsL[0]

        // 以i结尾 且当前为-
        val dp1 = LongArray(n) { Long.MIN_VALUE / 2 }

        for (i in 1 until n) {
            // 重新分割
            dp0[i] = maxOf(dp1[i - 1] + numsL[i], dp0[i - 1] + numsL[i])

            dp1[i] = dp0[i - 1] - numsL[i]
        }

//        dp0.print()
//        dp1.print()

        return maxOf(dp0.last(), dp1.last())
    }
}