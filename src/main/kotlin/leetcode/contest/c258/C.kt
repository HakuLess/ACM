package leetcode.contest.c258

import utils.isPalindrome
import utils.print

fun main() {
    val s = Solution5869()
    s.maxProduct("accbcaxxcxx").print()
}

class Solution5869 {
    fun maxProduct(s: String): Int {
        var ans = 1
        fun dfs(i: Int, l: String, r: String) {
            if (i == s.length) {
                if (l.isPalindrome() && r.isPalindrome())
                    ans = maxOf(ans, l.length * r.length)
                return
            }
            dfs(i + 1, l + s[i], r)
            dfs(i + 1, l, r + s[i])
            dfs(i + 1, l, r)
        }
        dfs(0, "", "")
        return ans
    }
}

//class Solution5869 {
//    fun maxProduct(s: String): Int {
//        val state = quickPower(3L, s.length.toLong())
//        var ans = 0
//        // 0 none, 1 left, 2 right
//        for (i in 0 until state) {
//            var cur = i
//            val left = StringBuilder()
//            val right = StringBuilder()
//            var step = 0
//            while (cur != 0L) {
//                when (cur % 3) {
//                    1L -> left.append(s[step])
//                    2L -> right.append(s[step])
//                }
//                cur /= 3
//                step++
//            }
//            if (left.toString().isPalindrome() && right.toString().isPalindrome()) {
//                ans = maxOf(ans, left.length * right.length)
//            }
//        }
//        return ans
//    }
//}