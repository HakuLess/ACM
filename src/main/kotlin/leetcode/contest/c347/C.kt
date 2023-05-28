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
        var ans = 0L
        for (i in 1 until s.length) {
            if (s[i] != s[i - 1]) {
                ans += minOf(i, s.length - i);
            }
        }
        return ans
    }
}