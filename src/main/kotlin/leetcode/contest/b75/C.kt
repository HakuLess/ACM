package leetcode.contest.b75

import utils.print

fun main() {
    val s = SolutionC()
    s.numberOfWays("001101").print()
}

class SolutionC {
    fun numberOfWays(s: String): Long {
        val l0 = LongArray(s.length + 1)
        val l1 = LongArray(s.length + 1)
        val r0 = LongArray(s.length + 1)
        val r1 = LongArray(s.length + 1)
        for (i in s.indices) {
            l0[i + 1] = l0[i]
            l1[i + 1] = l1[i]
            if (s[i] == '0') {
                l0[i + 1]++
            } else {
                l1[i + 1]++
            }
        }
        for (i in s.indices.reversed()) {
            r0[i] = r0[i + 1]
            r1[i] = r1[i + 1]
            if (s[i] == '0') {
                r0[i]++
            } else {
                r1[i]++
            }
        }
        var ans = 0L
        for (i in s.indices) {
            if (s[i] == '0') {
                ans += l1[i] * r1[i]
            } else {
                ans += l0[i] * r0[i]
            }
        }

        return ans
    }
}