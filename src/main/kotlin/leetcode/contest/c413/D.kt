package leetcode.contest.c413

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximumSubarrayXor(intArrayOf(2, 8, 4, 32, 16, 1), "[[0,2],[1,4],[0,5]]".toGrid()).print()
}

class SolutionD {
    fun maximumSubarrayXor(nums: IntArray, queries: Array<IntArray>): IntArray {
        val n = nums.size
        val dp = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            dp[i][i] = nums[i]
        }
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                dp[i][j] = dp[i][j - 1] xor dp[i + 1][j]
            }
        }
        for (i in n - 1 downTo 0) {
            for (j in i + 1 until n) {
                dp[i][j] = maxOf(dp[i][j], dp[i][j - 1], dp[i + 1][j])
            }
        }
        val ans = IntArray(queries.size)
        var i = 0
        for (q in queries) {
            ans[i++] = dp[q[0]][q[1]]
        }
        return ans
    }
}