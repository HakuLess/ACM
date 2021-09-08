package leetcode.contest.c256

import utils.print

fun main() {
    val s = Solution5857()
    s.numberOfUniqueGoodSubsequences("101").print()
    s.numberOfUniqueGoodSubsequences("001").print()
    s.numberOfUniqueGoodSubsequences("111").print()
}

//class Solution5857 {
//    fun numberOfUniqueGoodSubsequences(binary: String): Int {
//        if (binary.all { it == '1' }) return binary.length
//        val mod = 1000000007L
//        var c0 = 0L
//        var c1 = 0L
//        binary.forEach {
//            if (it == '0') {
//                c0 = (c0 + c1) % mod
//            } else {
//                c1 = (c0 + c1 + 1) % mod
//            }
//        }
//        return ((c0 + c1 + 1) % mod).toInt()
//    }
//}

class Solution5857 {
    // 去除重复序列
    fun numberOfUniqueGoodSubsequences(binary: String): Int {
        if (binary.all { it == '0' }) return 1
        if (binary.all { it == '1' }) return binary.length
        val mod = 1000000007L

        // Returns count of distinct subsequences of str.
        fun countSub(str: String): Pair<Long, Long> {
            // 长度为 序列包含的字符数，存储上一次包含的index
            val last = IntArray(2) { -1 }

            val n = str.length
            // 共有不同的序列数
            val dp = LongArray(n + 1)
            // 0为空，不选取任何字符
            dp[0] = 1L

            for (i in 1..n) {
                // 不考虑去重情况，可选子序列应该翻倍
                dp[i] = 2 * dp[i - 1]
                // 该字符之前出现过，那么需要去掉重复序列
                // 即上一次的序列完全删除
                if (last[str[i - 1] - '0'] != -1)
                    dp[i] = (dp[i] - dp[last[str[i - 1] - '0']] + mod) % mod
                // 将当前字符的index存入历史数组中
                last[str[i - 1] - '0'] = i - 1
                dp[i] %= mod
            }
            return Pair(dp[n], dp[last[0]])
        }

        // 全部的子序列 减去 以0开头的子序列
        val ans = countSub(binary.reversed()).let { it.first - it.second }
        return ((ans + mod) % mod).toInt()
    }
}
