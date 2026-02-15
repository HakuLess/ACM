package leetcode.contest.c489

import utils.print

fun main() {
    val s = SolutionC()
    // 4
    s.longestAlmostPalindrome("abca").print()
    // 4
    s.longestAlmostPalindrome("abba").print()
    // 5
    s.longestAlmostPalindrome("zzabba").print()
}

class SolutionC {
    fun longestAlmostPalindrome(s: String): Int {
        val n = s.length
        var ans = 0

        fun isPal(l0: Int, r0: Int): Boolean {
            var l = l0
            var r = r0
            while (l < r) {
                if (s[l] != s[r]) return false
                l++
                r--
            }
            return true
        }

        fun valid(l0: Int, r0: Int): Boolean {
            var l = l0
            var r = r0
            while (l < r) {
                if (s[l] == s[r]) {
                    l++
                    r--
                } else {
                    return isPal(l + 1, r) || isPal(l, r - 1)
                }
            }
            return true
        }

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (j - i + 1 <= ans) continue
                if (valid(i, j)) {
                    ans = j - i + 1
                }
            }
        }

        return ans
    }
}