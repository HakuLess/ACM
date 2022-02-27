package leetcode.contest.c282

class SolutionA {
    fun prefixCount(words: Array<String>, pref: String): Int {
        return words.count { it.startsWith(pref) }
    }
}