package leetcode.contest.c411

import utils.print

fun main() {
    val s = SolutionA()
    s.countKConstraintSubstrings("10101", 1).print()
}

class SolutionA {
    fun countKConstraintSubstrings(s: String, k: Int): Int {
        val n = s.length
        var result = 0

        for (left in 0 until n) {
            var zeroCount = 0
            var oneCount = 0
            for (right in left until n) {
                if (s[right] == '0') zeroCount++
                else oneCount++

                if (zeroCount <= k || oneCount <= k) {
                    result++
                } else {
                    break
                }
            }
        }

        return result
    }
}