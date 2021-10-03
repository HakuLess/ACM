package leetcode.contest.c261

import java.util.*

class Solution5893 {
    fun smallestSubsequence(s: String, k: Int, letter: Char, repetition: Int): String {
        var st = Stack<Char>()
        var cur = 0
        var ans = ""
        for (i in s.indices) {
            st.push(s[i])
            if (s[i] == letter) {
                cur++
            }
            if (st.size >= k && cur >= repetition) {
                var pop = 0
                val next = Stack<Char>()
                val arr = st.toTypedArray()
                for (it in arr) {
                    next.push(it)
                    if (next.isNotEmpty() && it < next.peek() && pop < arr.size - k) {
                        next.pop()
                        pop++
                    }
                }
                ans = minOf(ans, next.joinToString())
            }
        }
        return ans
    }
}