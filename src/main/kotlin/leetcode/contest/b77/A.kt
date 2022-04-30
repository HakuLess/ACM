package leetcode.contest.b77

class SolutionA {
    fun countPrefixes(words: Array<String>, s: String): Int {
        return words.count { s.startsWith(it) }
    }
}