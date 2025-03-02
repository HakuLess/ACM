package leetcode.contest.c439

import kotlin.math.abs

class SolutionB {
    fun longestPalindromicSubsequence(s: String, k: Int): Int {
        val n = s.length
        val dp = Array(n) { Array(n) { IntArray(k + 1) { -1 } } }

        fun cost(a: Char, b: Char): Int {
            val diff = abs(a - b)
            return minOf(diff, 26 - diff)
        }

        fun dfs(i: Int, j: Int, rem: Int): Int {
            if (i > j) return 0
            if (i == j) return 1
            if (dp[i][j][rem] != -1) return dp[i][j][rem]

            var res = maxOf(dfs(i + 1, j, rem), dfs(i, j - 1, rem))
            val c = cost(s[i], s[j])
            if (c <= rem) {
                res = maxOf(res, 2 + dfs(i + 1, j - 1, rem - c))
            }
            dp[i][j][rem] = res
            return res
        }

        return dfs(0, n - 1, k)
    }
}