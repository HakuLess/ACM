package leetcode.contest.c258

import utils.isPalindrome
import utils.print
import utils.quickPower

fun main() {
    val s = Solution5869()
    s.maxProduct("accbcaxxcxx").print()
}

class Solution5869 {
    fun maxProduct(s: String): Int {
        val state = quickPower(3L, s.length.toLong())
        var ans = 0
        // 0 none, 1 left, 2 right
        for (i in 0 until state) {
            var cur = i
            val left = StringBuilder()
            val right = StringBuilder()
            var step = 0
            while (cur != 0L) {
                when (cur % 3) {
                    1L -> left.append(s[step])
                    2L -> right.append(s[step])
                }
                cur /= 3
                step++
            }
            if (left.toString().isPalindrome() && right.toString().isPalindrome()) {
                ans = maxOf(ans, left.length * right.length)
            }
        }
        return ans
    }
}