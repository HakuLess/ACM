package leetcode.contest.c347

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumCost("0011").print()
    s.minimumCost("010101").print()
    s.minimumCost("0").print()
    s.minimumCost("01").print()
}

class SolutionC {
    fun minimumCost(s: String): Long {
        if (s.length == 1) return 0L
        val n = s.length
        val left0 = LongArray(n)
        val left1 = LongArray(n)
        val right0 = LongArray(n)
        val right1 = LongArray(n)

        for (i in s.indices) {
            if (s[i] == '0') {
                left0[i] = if (i == 0) 0 else left0[i - 1]
                left1[i] = if (i == 0) 1 else {
                    if (s[i - 1] == '0') left1[i - 1] + 1
                    else left0[i - 1] + i + 1
                }
            } else {
                left1[i] = if (i > 0) left1[i - 1] else 0
                left0[i] = if (i == 0) 1 else {
                    if (s[i - 1] == '1') left0[i - 1] + 1
                    else left1[i - 1] + i + 1
                }
            }
        }

        for (i in s.indices.reversed()) {
            if (s[i] == '0') {
                right0[i] = if (i == s.lastIndex) 0 else right0[i + 1]
                right1[i] = if (i == s.lastIndex) 1 else {
                    if (s[i + 1] == '0') right1[i + 1] + 1
                    else right0[i + 1] + (n - i)
                }
            } else {
                right1[i] = if (i == s.lastIndex) 0 else right1[i + 1]
                right0[i] = if (i == s.lastIndex) 1 else {
                    if (s[i + 1] == '1') right0[i + 1] + 1
                    else right1[i + 1] + (n - i)
                }
            }
        }

//        left0.print()
//        left1.print()
//
//        right0.print()
//        right1.print()

        var ans = Long.MAX_VALUE
        for (i in 0 until n - 1) {
            ans = minOf(ans, left0[i] + right0[i + 1])
            ans = minOf(ans, left1[i] + right1[i + 1])
        }

        return ans
    }
}