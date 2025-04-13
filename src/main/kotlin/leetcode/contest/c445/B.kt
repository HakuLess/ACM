package leetcode.contest.c445

import utils.print

fun main() {
    val s = SolutionB()
    s.smallestPalindrome("z").print()
}

class SolutionB {
    fun smallestPalindrome(s: String): String {
        val n = s.length
        // if (n == 1) return s
        val pre = s.substring(0, n / 2)
        val left = pre.toCharArray().sorted().joinToString("")
        if (n % 2 == 0) {
            return "${left}${left.reversed()}"
        } else {
            return "${left}${s[n / 2]}${left.reversed()}"
        }
    }
}