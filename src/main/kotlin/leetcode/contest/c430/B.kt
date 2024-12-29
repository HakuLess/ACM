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
        for (i in 0 until word.length - maxLen + 1) {
            val item = word.substring(i, i + maxLen)
            if (item > ans) {
                ans = item
            }
        }

        for (i in word.length - maxLen until word.length) {
            val item = word.substring(i, word.length)
//            println("$i: $item")
            if (item > ans) {
                ans = item
            }
        }

        return ans
    }
}