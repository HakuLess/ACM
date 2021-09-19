package leetcode.contest.c259

import utils.nextPermutation
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
                }
            }
            return c >= k
        }

        val c = IntArray(26)
        s.forEach {
            c[it - 'a']++
        }
        val arr = ArrayList<Char>()
        for (i in c.indices) {
            repeat(c[i] / k) {
                arr.add('a' + i)
            }
        }

        var ans = ""
        val n = arr.size
        for (mask in (1 shl n) - 1 downTo 1) {
            val cur = StringBuilder()
            for (i in 0 until n) {
                if (mask and (1 shl i) != 0) {
                    cur.append(arr[i])
                }
            }
            val permute = cur.toString().toCharArray()
            do {
                permute.joinToString("").let { str ->
                    if (check(str)) {
                        if (ans.length < str.length) ans = str
                        else if (ans.length == str.length && ans < str) ans = str
                    }
                }
            } while (permute.nextPermutation())
        }
        return ans
    }
}

