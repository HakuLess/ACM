package leetcode.contest.b142

class SolutionA {
    fun possibleStringCount(word: String): Int {
        val n = word.length
        val dp = Array(n + 1) { IntArray(2) }
        dp[0][0] = 1

        for (i in 1..n) {
            dp[i][0] = dp[i - 1][0]
            if (i >= 2 && word[i - 1] == word[i - 2]) {
                dp[i][1] = dp[i - 1][0] + dp[i - 1][1]
            } else {
                dp[i][1] = dp[i - 1][1]
            }
        }

        return dp[n][0] + dp[n][1]
    }
}