package leetcode.contest.c389

import utils.C

class SolutionB {
    fun countSubstrings(s: String, c: Char): Long {
        val cnt = s.count { it == c }
        if (cnt <= 0) return 0

        return 1L * cnt * (cnt - 1) / 2 + cnt
    }
}