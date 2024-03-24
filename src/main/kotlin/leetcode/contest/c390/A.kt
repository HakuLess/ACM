package leetcode.contest.c390

import utils.print

fun main() {
    val s = SolutionA()
    s.maximumLengthSubstring("bcbbbcba").print()
}

class SolutionA {
    fun maximumLengthSubstring(s: String): Int {
        var ans = 0
        for (i in s.indices) {
            for (j in i + 1..s.length) {
                val sub = s.substring(i, j)
//                sub.print()
                if (('a'..'z').all { item -> sub.count { it == item } <= 2 }) {
                    ans = maxOf(ans, sub.length)
                }
            }
        }
        return ans
    }
}