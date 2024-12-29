package leetcode.contest.c430

import utils.print

fun main() {
    val s = SolutionB()
    s.answerString("dbca", 2).print()
    s.answerString("gggg", 4).print()
    s.answerString("aann", 2).print()
    s.answerString("dah", 3).print()
    s.answerString("nbjnc", 2).print()
}

class SolutionB {
    fun answerString(word: String, numFriends: Int): String {
        if (numFriends == 1) return word

        val maxLen = word.length - (numFriends - 1)

        var ans = ""
        for (i in word.indices) {
            val item = word.substring(i, minOf(i + maxLen, word.length))
            if (item > ans) {
                ans = item
            }
        }

        return ans
    }
}