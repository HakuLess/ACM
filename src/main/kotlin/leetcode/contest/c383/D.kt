package leetcode.contest.c383

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumTimeToInitialState("abacaba", 3).print()
    s.minimumTimeToInitialState("babab", 2).print()
}

// todo 字符串Hash模板
class SolutionD {
    fun minimumTimeToInitialState(word: String, k: Int): Int {
        val n = word.length
        for (i in k until n step k) {
            val c = n - i
            if (word.substring(i) == word.substring(0, c)) {
                return i / k
            }
        }
        if (n % k == 0) {
            return n / k
        }
        return (n / k) + 1
    }
}