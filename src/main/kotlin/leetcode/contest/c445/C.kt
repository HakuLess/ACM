package leetcode.contest.c445

import utils.nextPermutation
import utils.print

fun main() {
    val s = SolutionC()
    s.smallestPalindrome("abba", 2).print()
}

class SolutionC {
    fun smallestPalindrome(s: String, k: Int): String {
        val n = s.length
        // if (n == 1) return s
        val pre = s.substring(0, n / 2)
        val left = pre.toCharArray().sorted().toCharArray()
        repeat(k - 1) {
            val b = left.nextPermutation()
//            left.joinToString().print()
            if (!b) return ""
        }

        val a = left.joinToString("")
        if (n % 2 == 0) {
            return "${a}${a.reversed()}"
        } else {
            return "${a}${s[n / 2]}${a.reversed()}"
        }
    }
}