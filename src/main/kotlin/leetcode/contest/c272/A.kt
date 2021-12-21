package leetcode.contest.c272

class SolutionA {
    fun firstPalindrome(words: Array<String>): String {
        return words.firstOrNull { it == it.reversed() } ?: ""
    }
}