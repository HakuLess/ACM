package leetcode.contest.b92

import utils.print


fun main() {
    val s = SolutionD()
    s.countPalindromes("103301").print()
    s.countPalindromes("0000000").print()
    s.countPalindromes("9999900000").print()
}

class SolutionD {
    fun countPalindromes(s: String): Int {
        val mod = 1000000007L
        var ans = 0L
        val n = s.length
        var dpi0 = LongArray(n)
        var dpi1 = LongArray(n)

        for (i in n - 2 downTo 0) {
            for (j in i + 2 until n) {
                dpi0[j] = dpi0[j - 1] + if (dpi1[j] == dpi1[j - 1]) 0 else (dpi1[j] - dpi1[j - 1] + mod)
                dpi0[j] %= mod
                if (s[i] == s[j]) {
                    dpi0[j] = dpi0[j] + (j - i - 1)
                    dpi0[j] %= mod
                }
            }

            for (j in i + 4 until n) {
                if (s[i] == s[j]) {
                    ans += dpi1[j - 1]
                    ans %= mod
                }
            }

            dpi1 = dpi0
            dpi0 = LongArray(n)
        }

        return ans.toInt()
    }
}
