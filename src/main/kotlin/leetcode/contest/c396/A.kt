package leetcode.contest.c396

import utils.print

fun main() {
    val s = SolutionA()
    s.isValid("UuE6").print()
}

class SolutionA {
    fun isValid(word: String): Boolean {
        val set = hashSetOf('a', 'e', 'i', 'o', 'u')
        var ans = true
        ans = ans && word.length >= 3
        ans = ans && word.all { it in '0'..'9' || it in 'a'..'z' || it in 'A'..'Z' }
        ans = ans && word.any { it.lowercaseChar() in set }
        ans = ans && word.any { it.lowercaseChar() in 'a'..'z' && it.lowercaseChar() !in set }
        return ans
    }
}