package leetcode.contest.c282

import utils.biMin
import utils.print

fun main() {
    val s = SolutionC()
}

class SolutionC {
    fun minimumTime(time: IntArray, totalTrips: Int): Long {
        val r = 1L * time.maxOrNull()!! * totalTrips
        return biMin(1, r) { total ->
            var cur = 0L
            time.forEach {
                cur += total / it
            }
            cur >= totalTrips
        }
    }
}