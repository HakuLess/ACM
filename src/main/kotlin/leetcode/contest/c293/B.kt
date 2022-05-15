package leetcode.contest.c293

import utils.print

fun main() {
    val s = SolutionB()
    s.maxConsecutive(28, 50, intArrayOf(35, 48)).print()
}

class SolutionB {
    fun maxConsecutive(bottom: Int, top: Int, special: IntArray): Int {
        val sorted = special.sorted()
        var ans = 0
        ans = sorted[0] - bottom
        for (i in 1 until sorted.size) {
            ans = maxOf(ans, sorted[i] - sorted[i - 1] - 1)
        }
        ans = maxOf(ans, top - sorted.last())
        return ans
    }
}