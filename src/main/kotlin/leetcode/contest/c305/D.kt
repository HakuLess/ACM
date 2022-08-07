package leetcode.contest.c305

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.longestIdealString("abcd", 3).print()
}

class SolutionD {
    fun longestIdealString(s: String, k: Int): Int {
        // 字符，总长度
        val dp = IntArray(26)
        for (i in s.indices) {
            dp[s[i] - 'a']++
            for (c in 'a'..'z') {
                val len = dp[c - 'a']
                if (abs(s[i] - c) <= k && s[i] != c) {
                    dp[s[i] - 'a'] = maxOf(len + 1, dp[s[i] - 'a'])
                }
            }
        }
//        return dp.max()!!
        return dp.maxOf { it }
    }
}