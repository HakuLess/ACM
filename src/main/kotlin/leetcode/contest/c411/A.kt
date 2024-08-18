package leetcode.contest.c411

import utils.print

fun main() {
    val s = SolutionA()
    s.countKConstraintSubstrings("10101", 1).print()
}

class SolutionA {
    fun countKConstraintSubstrings(s: String, k: Int): Int {
        var result = 0

        var zeroCount = 0
        var oneCount = 0

        var left = 0
        var right = left

        var tmp = 1
        while (left in s.indices) {
            tmp--
            while (right in s.indices) {
                if (s[right] == '0') zeroCount++
                else oneCount++

                if (zeroCount <= k || oneCount <= k) {
                    tmp++
                    right++
                } else {
                    if (s[right] == '0') zeroCount--
                    else oneCount--
                    break
                }
            }
//            println("$left with $tmp $zeroCount $oneCount")

            if (s[left] == '0') zeroCount--
            else oneCount--
            left++

            result += tmp
        }

        return result
    }
}