package leetcode.contest.b132

import java.util.*

class SolutionA {
    fun clearDigits(s: String): String {
        val st = Stack<Char>()
        s.forEach {
            if (it in '0'..'9') {
                if (st.isNotEmpty()) {
                    st.pop()
                }
            } else {
                st.push(it)
            }
        }
        return st.joinToString("")
    }
}