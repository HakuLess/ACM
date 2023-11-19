package leetcode.contest.c372

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumSteps("101").print()
}

class SolutionB {
    fun minimumSteps(s: String): Long {
        val l = ArrayList<Int>()
        var ans = 0L
        for (i in s.indices) {
            if (s[i] == '0') {
                if (l.isNotEmpty()) {
                    val lst = l.removeAt(0)
                    ans += (i - lst)
                    l.add(i)
                }
            } else {
                l.add(i)
            }
        }
        return ans
    }
}