package leetcode.contest.c414

import utils.biMax
import utils.biMin
import utils.print

fun main() {
    val s = SolutionB()
    s.maxPossibleScore(intArrayOf(6, 0, 3), 2).print()
    s.maxPossibleScore(intArrayOf(1, 1), 0).print()
}

class SolutionB {
    fun maxPossibleScore(start: IntArray, d: Int): Int {
        start.sort()

        fun check(mid: Long): Boolean {
            var pre = start[0].toLong()
            for (i in start.indices) {
                if (i == 0) continue
                if (pre + mid <= start[i] + d) {
                    pre = maxOf(pre + mid, 0L + start[i])
                } else {
                    return false
                }
            }
            return true
        }
        return biMax(0L, Long.MAX_VALUE / 2) {
            check(it)
        }.toInt()
    }
}