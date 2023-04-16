package leetcode.contest.c341

import utils.print

fun main() {
    val s = SolutionC()
    s.addMinimum("aaaacb").print()
}

class SolutionC {
    fun addMinimum(word: String): Int {
        var pre = ' '
        var ans = 0
        for (i in word.indices) {
            val c = word[i]
            if (pre == ' ' || pre == 'c') {
                when (c) {
                    'b' -> ans++
                    'c' -> ans += 2
                }
            } else if (pre == 'a') {
                when (c) {
                    'a' -> ans += 2
                    'c' -> ans++
                }
            } else if (pre == 'b') {
                when (c) {
                    'a' -> ans++
                    'b' -> ans += 2
                }
            }

            pre = c
        }

        if (pre == 'a') ans += 2
        else if (pre == 'b') ans++

        return ans
    }
}