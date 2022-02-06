package leetcode.contest.c279

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumTime("11000101").print()
    s.minimumTime("0").print()
}

class SolutionD {
    fun minimumTime(s: String): Int {
        val len = s.length
        val left = ArrayList<Int>()
        left.add(0)
        for (i in s.indices) {
            if (s[i] == '1') {
                left.add(minOf(left.last() + 2, i + 1))
            }
        }
        val right = ArrayList<Int>()
        right.add(0)
        for (i in s.indices.reversed()) {
            if (s[i] == '1') {
                right.add(minOf(right.last() + 2, len - i))
            }
        }
        right.reverse()
        var ans = left.size * 2 - 2
        for (i in left.indices) {
            ans = minOf(ans, left[i] + right[i])
        }
        return ans
    }
}