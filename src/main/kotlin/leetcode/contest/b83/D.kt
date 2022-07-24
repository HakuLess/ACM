package leetcode.contest.b83

import utils.print

fun main() {
    val s = SolutionD()
    s.shortestSequence(intArrayOf(4, 2, 1, 2, 3, 3, 2, 4, 1), 4).print()
    s.shortestSequence(intArrayOf(1, 1, 2, 2), 2).print()
    s.shortestSequence(intArrayOf(1, 1, 3, 2, 2, 2, 3, 3), 4).print()
}

class SolutionD {
    fun shortestSequence(rolls: IntArray, k: Int): Int {
        var cur = IntArray(k + 1)
        var total = 0
        var ans = 0
        for (i in rolls.indices) {
            val item = rolls[i]
            if (cur[item] == 0) {
                cur[item] = 1
                total++
            }
            if (total == k) {
                ans++
                total = 0
                cur = IntArray(k + 1)
            }
        }
        return ans + 1
    }
}