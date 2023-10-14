package leetcode.contest.b115

import utils.print

fun main() {
    val s = SolutionA()
    s.lastVisitedIntegers(listOf("1", "2", "prev", "prev", "prev")).joinToString().print()
}

class SolutionA {
    fun lastVisitedIntegers(words: List<String>): List<Int> {
        val ans = ArrayList<Int>()
        for (i in words.indices) {
            if (words[i] == "prev") {
                var c = 0
                for (j in i downTo 0) {
                    if (words[j] == "prev") {
                        c++
                    } else break
                }
                for (j in i downTo 0) {
                    if (words[j] != "prev") c--
                    if (c == 0) {
                        ans.add(words[j].toInt())
                        break
                    }
                }

                if (c != 0) {
                    ans.add(-1)
                }
            }
        }
        return ans
    }
}