package leetcode.contest.c360

import utils.print


fun main() {
    val s = SolutionD()
    s.getMaxFunctionValue(listOf(2, 0, 1), 4).print()
    s.getMaxFunctionValue(listOf(1, 1, 1, 2, 3), 3).print()
}

// 倍增法
class SolutionD {
    fun getMaxFunctionValue(receiver: List<Int>, k: Long): Long {
        val dp = Array(35) { IntArray(receiver.size) }
        val sum = Array(35) { LongArray(receiver.size) }
        var max: Long = 0
        for (i in 0 until dp[0].size) {
            dp[0][i] = receiver[i]
            sum[0][i] = receiver[i].toLong()
        }
        for (i in 1 until dp.size) {
            for (j in 0 until dp[0].size) {
                dp[i][j] = dp[i - 1][dp[i - 1][j]]
                sum[i][j] = sum[i - 1][j] + sum[i - 1][dp[i - 1][j]]
            }
        }
        for (i in 0 until dp[0].size) {
            var curr = i.toLong()
            var j = 0
            var l = i
            while (j < dp.size) {
                if (k shr j and 1 > 0) {
                    max = maxOf(max, sum[j][l].let {
                        curr += it
                        curr
                    })
                    l = dp[j][l]
                }
                j++
            }
        }
        return max
    }
}