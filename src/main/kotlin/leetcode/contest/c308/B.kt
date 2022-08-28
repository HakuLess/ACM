package leetcode.contest.c308

import java.util.*

class SolutionB {
    fun removeStars(s: String): String {
        val st = Stack<Char>()
        s.forEach {
            if (it == '*') {
                if (st.isNotEmpty()) st.pop()
            } else {
                st.push(it)
            }
        }
        return st.joinToString("")
    }
}