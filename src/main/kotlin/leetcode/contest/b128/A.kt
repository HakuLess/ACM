package leetcode.contest.b128

import kotlin.math.abs

class SolutionA {
    fun scoreOfString(s: String): Int {
        var ans = 0
        for (i in 1 until s.length) {
            ans += abs(s[i] - s[i - 1])
        }
        return ans
    }
}