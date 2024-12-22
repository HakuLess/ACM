package leetcode.contest.c429

import utils.biMin
import utils.print

fun main() {
    val s = SolutionD()
    // 2
    s.minLength("01100011", 1).print()
}

class SolutionD {
    fun minLength(s: String, numOps: Int): Int {
        val n = s.length.toLong()

        fun getMin(s: String, offset: Int): Int {
            return biMin(1L, n) {
                var lst = ' '
                var tmp = 0
                var op = offset
                for (i in s.indices) {
                    if (s[i] == lst) {
                        tmp++
                        // 超过限制
                        if (tmp > it) {
                            lst = if (s[i] == '1') '0' else '1'
                            tmp = 1
                            op++
                        }
                    } else {
                        lst = s[i]
                        tmp = 1
                    }
                }
                op <= numOps
            }.toInt().let {
                if (it == -1) Int.MAX_VALUE
                else it
            }
        }

        var ans = minOf(getMin(s, 0), getMin(s.reversed(), 0))
        val sb = StringBuilder(s)
        sb[0] = '1'
        ans = minOf(ans, getMin(sb.toString(), 1))
        sb[0] = '0'
        ans = minOf(ans, getMin(sb.toString(), 1))
        return ans
    }
}