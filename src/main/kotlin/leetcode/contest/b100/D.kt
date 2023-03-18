package leetcode.contest.b100

import utils.biMin
import utils.print
import kotlin.math.sqrt

fun main() {
    val s = SolutionD()
    s.repairCars(intArrayOf(4, 2, 3, 1), 10).print()
}

class SolutionD {
    fun repairCars(ranks: IntArray, cars: Int): Long {
        return biMin(1L, Long.MAX_VALUE / 10) { ans ->
            var fix = 0L
            ranks.forEach {
                fix += sqrt(ans.toDouble() / it).toInt()
            }
            fix >= cars
        }
    }
}