package leetcode.contest.c408

import utils.print
import kotlin.math.sqrt

fun main() {
    val s = SolutionC()
    s.numberOfSubstrings("111").print()
}

class SolutionC {
    fun numberOfSubstrings(s: String): Int {
        val n = s.length
        val lim = sqrt(n.toDouble()).toInt() + 2

        // 记录下一个0所在位置
        val nxt = IntArray(n + 1) { n }
        for (i in n - 1 downTo 0) {
            nxt[i] = nxt[i + 1]
            if (s[i] == '0') nxt[i] = i
        }

        var ans = 0
        for (i in 0 until n) {
            var j = i
            var cnt = if (s[i] == '0') 1 else 0
            while (j < n && cnt <= lim) {
                val one = (nxt[j + 1] - i) - cnt
                if (one >= cnt * cnt) {
                    val t = one - cnt * cnt + 1
                    ans += minOf(t, nxt[j + 1] - j)
                }
                j = nxt[j + 1]
                cnt++
            }
        }
        return ans
    }
}