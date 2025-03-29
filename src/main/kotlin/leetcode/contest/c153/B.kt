package leetcode.contest.c153

import utils.print

fun main() {
    val s = SolutionB()
    // 7
    s.maxActiveSectionsAfterTrade("1000100").print()
    // 4
    s.maxActiveSectionsAfterTrade("0100").print()
    // 4
    s.maxActiveSectionsAfterTrade("01010").print()
    // 1
    s.maxActiveSectionsAfterTrade("01").print()
    // 0
    s.maxActiveSectionsAfterTrade("0").print()
}

class SolutionB {
    fun maxActiveSectionsAfterTrade(s: String): Int {
        val n = s.length
        val c1 = s.count { it == '1' }

        var ans = 0
        var i = 0
        while (i < n) {
            if (s[i] == '1') {
                val start = i
                while (i < n && s[i] == '1') {
                    i++
                }
                val end = i - 1
                if (start > 0 && end < n - 1) {
                    var leftZeros = 0
                    var j = start - 1
                    while (j >= 0 && s[j] == '0') {
                        leftZeros++
                        j--
                    }
                    var rightZeros = 0
                    j = end + 1
                    while (j < n && s[j] == '0') {
                        rightZeros++
                        j++
                    }
                    val more = leftZeros + rightZeros
                    ans = maxOf(ans, more)
                }
            } else {
                i++
            }
        }
        return c1 + ans
    }
}