package leetcode.contest.c291

import utils.print

fun main() {
    val s = SolutionD()
//    s.appealSum("abbca").print()
//    s.appealSum("abcda").print()
    s.appealSum("abc").print()
//    s.appealSum("abbca").print()
}

class SolutionD {
    fun appealSum(s: String): Long {
        var ans = 0L
        var step = 0L
        val pos = IntArray(26) { -1 }
        for (i in s.indices) {
            val c = s[i] - 'a'
            step += if (pos[c] < 0) i + 1 else i - pos[c]
            ans += step
            pos[c] = i
        }
        return ans
    }
}