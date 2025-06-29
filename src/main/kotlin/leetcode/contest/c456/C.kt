package leetcode.contest.c456

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.minXor(intArrayOf(1, 2, 3), 2).print()
    // 2
    s.minXor(intArrayOf(2, 3, 3, 2), 3).print()
    // 0
    s.minXor(intArrayOf(1, 1, 2, 3, 1), 2).print()

    s.minXor(intArrayOf(1, 1, 2, 3, 1), 1).print()
    s.minXor(intArrayOf(1, 1, 2, 3, 1), 5).print()
}

class SolutionC {
    fun minXor(nums: IntArray, k: Int): Int {

        val n = nums.size

        val prefix = IntArray(n + 1)
        for (i in 0 until n) {
            prefix[i + 1] = prefix[i] xor nums[i]
        }

        // dp[i][j] 前i个元素 拆分为j组的
        val dp = Array(n + 1) { IntArray(k + 1) { Int.MAX_VALUE } }
        dp[0][0] = 0
        for (i in 1..n) {
            dp[i][1] = prefix[i]
        }

        for (j in 2..k) {
            for (i in j..n) {
                for (l in j - 1 until i) {
                    // 0..l个拆分为 j - 1组，l..i另外一组
                    val cur = maxOf(dp[l][j - 1], prefix[l] xor prefix[i])
                    // 更小值优化当前数据
                    dp[i][j] = minOf(cur, dp[i][j])
                }
            }
        }

        return dp[n][k]
    }
}