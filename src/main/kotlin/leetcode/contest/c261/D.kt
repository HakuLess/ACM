package leetcode.contest.c261

import utils.print
import java.util.*

fun main() {
    val s = Solution5893()
    s.smallestSubsequence("leet", 3, 'e', 1).print()
//    s.smallestSubsequence("leetcode", 4, 'e', 2).print()
//    s.smallestSubsequence("leetcoaade", 4, 'e', 2).print()
//
//    s.smallestSubsequence("aaabbbcccddd", 3, 'b', 2).print()
//    s.smallestSubsequence("hjjhhhmhhwhz", 6, 'h', 5).print()
}

// 单调栈
class Solution5893 {
    fun smallestSubsequence(s: String, k: Int, letter: Char, repetition: Int): String {
        val st = Stack<Char>()
        // 剩余letter
        var lst = s.count { it == letter }
        // 当前持有letter
        var cur = 0
        for (i in s.indices) {
            // 1. 满足总长度k
            // 2. 满足字符重复repetition
            while (st.isNotEmpty() && s[i] < st.peek() && st.size + s.length - i > k) {
                if (st.peek() == letter) {
                    if (cur + lst > repetition) {
                        cur--
                        st.pop()
                    } else {
                        break
                    }
                } else {
                    st.pop()
                }
            }

            st.push(s[i])
            if (s[i] == letter) {
                cur++
                lst--
            }
            if (st.size > k) {
                if (st.peek() == letter) cur--
                st.pop()
            }
            // 腾出足够的位置给letter
            while (st.isNotEmpty() && k - st.size < repetition - cur) {
                if (st.peek() == letter) cur--
                st.pop()
            }
        }
        return st.joinToString("")
    }
}