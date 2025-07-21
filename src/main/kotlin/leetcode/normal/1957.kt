package leetcode.normal

class Solution1957 {
    fun makeFancyString(s: String): String {
        val sb = ArrayList<Char>()
        s.forEach {
            val n = sb.size
            if (n >= 2 && (sb[n - 1] == it && sb[n - 2] == it)) {

            } else {
                sb.add(it)
            }
        }
        return sb.joinToString("")
    }
}