package leetcode.contest.c367

import java.util.*

class SolutionB {
    fun shortestBeautifulSubstring(s: String, k: Int): String {
        var ans = ""
        var c = 0
        val queue = LinkedList<Char>()
        for (i in s.indices) {
            if (s[i] == '1') c++
            queue.offer(s[i])
            while (c >= k) {
                if (c == k) {
                    val cur = queue.joinToString("")
                    if (ans.isEmpty()) {
                        ans = cur
                    } else if (ans.length > cur.length) {
                        ans = cur
                    } else if (ans.length == cur.length) {
                        ans = minOf(ans, cur)
                    }
                }
                val remove = queue.pop()
                if (remove == '1') {
                    c--
                }
            }
            if (c == k) {
                val cur = queue.joinToString("")
                if (ans.isEmpty()) {
                    ans = cur
                } else if (ans.length > cur.length) {
                    ans = cur
                } else if (ans.length == cur.length) {
                    ans = minOf(ans, cur)
                }
            }
        }
        return ans
    }
}