package leetcode.contest.b65

import kotlin.math.abs

class SolutionA {
    fun checkAlmostEquivalent(word1: String, word2: String): Boolean {
        return ('a'..'z').all { c ->
            abs(word1.count { it == c } - word2.count { it == c}) <= 3
        }
    }
}