package leetcode.contest.c415

import utils.print

fun main() {
    val s = SolutionB()
    s.maxScore(intArrayOf(3, 2, 5, 6), intArrayOf(2, -6, 4, -5, -3, 2, -7)).print()
}

class SolutionB {
    fun maxScore(a: IntArray, b: IntArray): Long {
        val n = b.size
        val dp = Array(4) { LongArray(n) { Int.MIN_VALUE.toLong() } }

        // 初始化第一个选择的得分
        for (i in 0 until n) {
            dp[0][i] = 1L * a[0] * b[i]
        }

        // 动态规划
        for (i in 1..3) {
            var maxPrev = Long.MIN_VALUE
            for (j in i until n) {
                maxPrev = maxOf(maxPrev, dp[i - 1][j - 1])
                dp[i][j] = maxPrev + 1L * a[i] * b[j]
            }
        }
//        dp[0].print()
//        dp[1].print()
//        dp[2].print()
//        dp[3].print()

        return dp[3].maxOrNull()!!
    }
}