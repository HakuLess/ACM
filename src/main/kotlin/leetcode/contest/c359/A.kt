package leetcode.contest.c359

class SolutionA {
    fun isAcronym(words: List<String>, s: String): Boolean {
        return words.map { it[0] }.joinToString("") == s
    }
}