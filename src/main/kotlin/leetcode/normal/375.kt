package leetcode.normal

import utils.print

fun main() {
    val s = Solution375()
//    s.getMoneyAmount2(15).print()
    s.getMoneyAmount(15).print()
}

class Solution375 {
//    fun getMoneyAmount2(n: Int): Int {
//        val dp = Array(n + 1) { IntArray(n + 1) { 0 } }
//        for (k in 2..n) {
//            for (i in 1..n - k + 1) {
//                val j = i + k - 1
//                // 从i到j，需花费多少钱进行确认
//                var min = Int.MAX_VALUE / 2
//                for (m in j - 1 downTo i) {
//                    min = minOf(min, m + maxOf(dp[i][m - 1], dp[m + 1][j]))
//                }
//                dp[i][j] = min
//            }
//        }
//        return dp[1][n]
//    }

    fun getMoneyAmount(n: Int): Int {
        val dp = Array(n + 1) { IntArray(n + 1) { -1 } }
        for (i in dp.indices) {
            dp[i][i] = 0
        }

        fun dfs(i: Int, j: Int): Int {
            if (i !in 1..n || j !in 1..n) return 0
            if (dp[i][j] != -1) return dp[i][j]
            var ans = Int.MAX_VALUE / 2
            for (k in i..j) {
                ans = minOf(ans, k + maxOf(dfs(i, k - 1), dfs(k + 1, j)))
            }
            return ans.also {
                dp[i][j] = it
            }
        }
        return dfs(1, n)
    }
}