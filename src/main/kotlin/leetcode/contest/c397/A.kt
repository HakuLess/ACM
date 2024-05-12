package leetcode.contest.c397

import kotlin.math.abs

class SolutionA {
    fun findPermutationDifference(s: String, t: String): Int {
        var ans = 0
        for (c in s) {
            val a = s.indexOf(c)
            val b = t.indexOf(c)
            ans += abs(a - b)
        }
        return ans
    }
}