package leetcode.contest.c319

import utils.longestPalindrome
import utils.manacher
import utils.print

fun main() {
    val s = SolutionD()
    // 2
    s.maxPalindromes("abaccdbbd", 3).print()
    // 4
    s.maxPalindromes("fttfjofpnpfydwdwdnns", 2).print()
    s.maxPalindromes("dwdwd", 2).print()
}

class SolutionD {
    fun maxPalindromes(s: String, k: Int): Int {
        val sb = StringBuilder()
        sb.append('#')
        s.forEach {
            sb.append(it)
            sb.append('#')
        }
        val c = manacher(sb.toString())
        val dp = IntArray(c.size)
        for (i in c.indices) {
            if (i > 0)
                dp[i] = maxOf(dp[i], dp[i - 1])
            var cur = c[i]
            while (cur >= k) {
                if (i + cur in dp.indices && i - cur in dp.indices) {
                    dp[i + cur] = maxOf(dp[i + cur], dp[i - cur] + 1)
                }
                cur -= 2
            }
        }
        return dp.last()
    }
}