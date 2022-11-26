package leetcode.contest.b92

import utils.print

fun main() {
    val s = SolutionC()
    s.bestClosingTime("YYNY").print()
}

class SolutionC {
    fun bestClosingTime(customers: String): Int {
        val n = customers.length
        val pre = IntArray(n + 1)
        val suf = IntArray(n + 1)
        for (i in customers.indices) {
            pre[i + 1] = pre[i]
            if (customers[i] == 'N') {
                pre[i + 1]++
            }
        }
        for (i in customers.indices.reversed()) {
            suf[i] = suf[i + 1]
            if (customers[i] == 'Y') {
                suf[i]++
            }
        }
        var cur = Int.MAX_VALUE / 2
        var ans = 0
        for (i in pre.indices) {
            if (pre[i] + suf[i] < cur) {
                ans = i
                cur = pre[i] + suf[i]
            }
        }
        return ans
    }
}