package leetcode.contest.c356

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumString("abc", "bca", "aaa").print()
    s.minimumString("ab", "ba", "aba").print()
}

class SolutionC {
    fun minimumString(a: String, b: String, c: String): String {
        var ans = "$a$b$c"
        fun check(a: String, b: String, c: String) {
            var tmp = ""
            tmp += a
            if (b !in tmp) {
                for (i in b.length downTo 0) {
                    if (tmp.endsWith(b.substring(0, i))) {
                        tmp += b.substring(i, b.length)
                        break
                    }
                }
            }
            if (c !in tmp) {
                for (i in c.length downTo 0) {
                    if (tmp.endsWith(c.substring(0, i))) {
                        tmp += c.substring(i, c.length)
                        break
                    }
                }
            }
            if (tmp.length < ans.length) {
                ans = tmp
            } else if (tmp.length == ans.length) {
                ans = minOf(tmp, ans)
            }
        }
        check(a, b, c)
        check(a, c, b)
        check(b, a, c)
        check(b, c, a)
        check(c, a, b)
        check(c, b, a)
        return ans
    }
}