package leetcode.contest.c259

import utils.permutations
import utils.print

fun main() {
    val s = Solution5878()
    s.longestSubsequenceRepeatedK("letsleetcode", 2).print()
}

class Solution5878 {
    fun longestSubsequenceRepeatedK(s: String, k: Int): String {
        fun check(str: String): Boolean {
            var c = 0
            var i = 0
            s.forEach {
                if (it == str[i]) {
                    i++
                }
                if (i == str.length) {
                    i = 0
                    c++
                    if (c == k) return true
                }
            }
            return false
        }

        val dp = Array(8) { ArrayList<String>() }
        dp[0].add("")
        for (i in 0 until 7) {
            for (str in dp[i]) {
                for (c in 'a'..'z') {
                    val t = str + c
                    if (check(t)) dp[i + 1].add(t)
                }
            }
        }
        for (i in 7 downTo 0) {
            return dp[i].maxOrNull() ?: continue
//            return dp[i].max() ?: continue
        }
        return ""
    }

//    fun longestSubsequenceRepeatedK(s: String, k: Int): String {
//        fun check(str: String): Boolean {
//            var c = 0
//            var i = 0
//            s.forEach {
//                if (it == str[i]) {
//                    i++
//                }
//                if (i == str.length) {
//                    i = 0
//                    c++
//                }
//            }
//            return c >= k
//        }
//
//        val c = IntArray(26)
//        s.forEach {
//            c[it - 'a']++
//        }
//        val arr = ArrayList<Char>()
//        for (i in c.indices) {
//            repeat(c[i] / k) {
//                arr.add('a' + i)
//            }
//        }
//
//        val l = arrayListOf<String>()
//        arr.toTypedArray().permutations {
//            it.joinToString("").let { str ->
//                l.add(str)
//            }
//        }
//        l.sortedWith(compareBy({ it.length }, { it })).reversed().forEach { str ->
//            if (check(str)) {
//                return str
//            }
//        }
//        return ""
//    }
}

