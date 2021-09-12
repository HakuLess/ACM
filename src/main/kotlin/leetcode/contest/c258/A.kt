package leetcode.contest.c258

class Solution5867 {
    fun reversePrefix(word: String, ch: Char): String {
        val index = word.indexOf(ch)
        if (index < 0) return word
        return word.substring(0, index + 1).reversed() + word.substring(index + 1, word.length)
    }
}