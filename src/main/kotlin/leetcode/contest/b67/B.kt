package leetcode.contest.b67

import utils.print

fun main() {
    val s = SolutionB()
    s.goodDaysToRobBank(intArrayOf(5, 3, 3, 3, 5, 6, 2), 2).joinToString().print()
}

class SolutionB {
    fun goodDaysToRobBank(security: IntArray, time: Int): List<Int> {
        val left = IntArray(security.size)
        val right = IntArray(security.size)
        var cur = 0
        for (i in security.indices) {
            if (i == 0) continue
            if (security[i] > security[i - 1]) {
                cur = 0
            } else {
                cur++
            }
            left[i] = cur
        }
        cur = 0
        for (i in security.indices.reversed()) {
            if (i == security.lastIndex) continue
            if (security[i] > security[i + 1]) {
                cur = 0
            } else {
                cur++
            }
            right[i] = cur
        }
        val ans = ArrayList<Int>()
        for (i in security.indices) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i)
            }
        }
        return ans
    }
}