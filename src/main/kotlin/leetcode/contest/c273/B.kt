package leetcode.contest.c273

import utils.print

fun main() {
    val s = SolutionB()
    s.executeInstructions(3, intArrayOf(0, 1), "RRDDLU").print()
}

class SolutionB {
    fun executeInstructions(n: Int, startPos: IntArray, s: String): IntArray {
        val ans = IntArray(s.length)
        for (i in s.indices) {
            var (x, y) = startPos
            var step = 0
            for (j in i until s.length) {
                when (s[j]) {
                    'L' -> y--
                    'R' -> y++
                    'U' -> x--
                    'D' -> x++
                }
                if (x !in 0 until n) break
                if (y !in 0 until n) break
                step++
            }
            ans[i] = step
        }
        return ans
    }
}