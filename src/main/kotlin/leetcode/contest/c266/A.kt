package leetcode.contest.c266

import utils.print

fun main() {
    val s = SolutionA()
    s.countVowelSubstrings("cuaieuouac").print()
}

class SolutionA {
    fun countVowelSubstrings(word: String): Int {
        var ans = 0
        val arr = arrayOf('a', 'e', 'i', 'o', 'u')
        for (i in 0 until word.length) {
            for (j in i..word.length) {
                val sub = word.substring(i, j)
                if (arr.all {
                        it in sub
                    } && sub.all { it in arr }) {
                    ans++
                }
            }
        }
        return ans
    }
}