package leetcode.normal

import utils.print
import java.util.*


fun main() {
    val s = Solution1717()
    s.maximumGain("cdbcbbaaabab", 4, 5).print()
    s.maximumGain("aabbaaxybbaabb", 5, 4).print()
}

class Solution1717 {

    // 贪心处理，优先奖励大的
    fun maximumGain(s: String, x: Int, y: Int): Int {
        var ans = 0
        fun dfs(s: String, a: Char, b: Char, v: Int): String {
            val st = Stack<Char>()
            s.forEach {
                if (it == a && st.isNotEmpty() && st.peek() == b) {
                    st.pop()
                    ans += v
                } else {
                    st.add(it)
                }
            }
            return st.joinToString("")
        }
        val (a, b) = if (x >= y) Pair('a', 'b') else Pair('b', 'a')
        val (big, small) = if (x >= y) Pair(x, y) else Pair(y, x)
        val next = dfs(s, b, a, big)
        dfs(next, a, b, small)
        return ans
    }

//    fun maximumGain(s: String, x: Int, y: Int): Int {
//        val seen = HashMap<String, Int>()
//        fun dfs(cur: String, remove: String, score: Int): Int {
//            val key = "${cur}-${remove}"
//            if (key in seen) {
//                return seen[key]!!
//            }
//            val index = cur.indexOf(remove)
//            if (index == -1) {
//                return 0
//            }
//            val next = cur.removeRange(index, index + 2)
//            return (score + maxOf(dfs(next, "ab", x), dfs(next, "ba", y))).also {
//                seen[key] = it
//            }
//        }
//
//        return maxOf(dfs(s, "ab", x), dfs(s, "ba", y))
//    }
}