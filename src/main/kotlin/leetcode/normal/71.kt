package leetcode.normal

import java.util.*

class Solution71 {
    fun simplifyPath(path: String): String {
        val st = Stack<String>()
        path.split('/').filter { it.isNotEmpty() && it != "." }.forEach {
            if (it == "..") {
                if (st.isNotEmpty())
                    st.pop()
            } else {
                st.push(it)
            }
        }
        return "/${st.joinToString("/")}"
    }
}