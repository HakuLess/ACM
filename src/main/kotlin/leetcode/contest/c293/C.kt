package leetcode.contest.c293

import utils.print

fun main() {
    val s = SolutionC()
    s.largestCombination(intArrayOf(16, 16, 48, 71, 62, 12, 24, 14, 17, 18, 19, 20, 10000)).print()
}

class SolutionC {
    fun largestCombination(candidates: IntArray): Int {
        val cur = IntArray(24)
        for (i in candidates.indices) {
            candidates[i].toString(2).reversed().forEachIndexed { index, c ->
                if (c == '1') {
                    cur[index]++
                }
            }
        }
        return cur.maxOrNull()!!
    }
}