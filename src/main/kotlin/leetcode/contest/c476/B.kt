package leetcode.contest.c476

import kotlin.math.abs

class SolutionB {
    fun minLengthAfterRemovals(s: String): Int {
        var a = 0
        var b = 0
        for (c in s) {
            if (c == 'a') a++ else b++
        }
        return abs(a - b)
    }
}