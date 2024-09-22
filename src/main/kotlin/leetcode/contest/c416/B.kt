package leetcode.contest.c416

import utils.biMin
import utils.print
import java.util.*
import kotlin.math.sqrt

fun main() {
    val s = SolutionB()
    s.minNumberOfSeconds(5, intArrayOf(1, 7)).print()
    s.minNumberOfSeconds(4, intArrayOf(2, 1, 1)).print()
}

class SolutionB {
    fun minNumberOfSeconds(mountainHeight: Int, workerTimes: IntArray): Long {
        return biMin(1L, Long.MAX_VALUE / 2) { time ->
            var total = 0L
            workerTimes.forEach {
                val cost = time / it
                var height = sqrt(cost.toDouble() * 2).toLong()
                if (height * (height + 1) / 2 > cost) {
                    height--
                }
                total += height
            }
            total >= mountainHeight
        }
    }
}