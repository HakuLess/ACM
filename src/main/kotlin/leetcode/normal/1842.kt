package leetcode.normal

import utils.nextPermutation
import utils.print

fun main() {
    val s = Solution1842()
    s.nextPalindrome("1221").print()
    s.nextPalindrome("32123").print()
    s.nextPalindrome("45544554").print()
}

class Solution1842 {
    fun nextPalindrome(num: String): String {
        val sub = num.substring(0, num.length / 2).toCharArray()
        if (sub.nextPermutation()) {
            return if (num.length % 2 == 0)
                "${sub.joinToString("")}${sub.reversed().joinToString("")}"
            else
                "${sub.joinToString("")}${num[num.length / 2]}${sub.reversed().joinToString("")}"
        }
        return ""
    }
}