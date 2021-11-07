package leetcode.contest.c266

import utils.print

fun main() {
    val s = SolutionB()
    s.countVowels("aba").print()
    s.countVowels("noosabasboosa").print()
}

class SolutionB {
    fun countVowels(word: String): Long {
        val arr = arrayOf('a', 'e', 'i', 'o', 'u')
        var ans = 0L
        val n = word.length
        for (i in word.indices) {
            if (word[i] in arr) {
                ans += (i + 1).toLong() * (n - i)
            }
        }
        return ans
    }
}