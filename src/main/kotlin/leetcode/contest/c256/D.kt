package leetcode.contest.c256

import utils.print

fun main() {
    val s = Solution5857()
    s.numberOfUniqueGoodSubsequences("101").print()
    s.numberOfUniqueGoodSubsequences("001").print()
    s.numberOfUniqueGoodSubsequences("111").print()
}

class Solution5857 {
    fun numberOfUniqueGoodSubsequences(binary: String): Int {
        if (binary.all { it == '0' }) return 1
        if (binary.all { it == '1' }) return binary.length
        val mod = 1000000007L
        var ans = countSub(binary).first - countSub(binary.reversed()).second
        while (ans < 0) ans += mod
        return (ans % mod).toInt()
    }

    // Returns count of distinct sunsequences of str.
    fun countSub(str: String): Pair<Long, Long> {
        val mod = 1000000007L
        val last = IntArray(2) { -1 }

        // Length of input string
        val n = str.length

        // dp[i] is going to store count of distinct
        // subsequences of length i.
        val dp = LongArray(n + 1)

        // Empty substring has only one subsequence
        dp[0] = 1L

        // Traverse through all lengths from 1 to n.
        for (i in 1..n) {
            // Number of subsequences with substring
            // str[0..i-1]
            dp[i] = 2 * dp[i - 1]

            // If current character has appeared
            // before, then remove all subsequences
            // ending with previous occurrence.
            if (last[str[i - 1] - '0'] != -1) dp[i] = dp[i] - dp[last[str[i - 1] - '0']]

            // Mark occurrence of current character
            last[str[i - 1] - '0'] = i - 1
            dp[i] %= mod
        }
        return Pair(dp[n], dp[last[0]])
    }
}
