package leetcode.contest.c272

class SolutionA {
    fun firstPalindrome(words: Array<String>): String {
        for (i in words.indices) {
            if (words[i].reversed() == words[i]) return words[i]
        }
        return ""
    }
}