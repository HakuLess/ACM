package leetcode.contest.c276

import utils.MultiSet
import utils.print
import kotlin.math.min

fun main() {
    val s = SolutionD()
    s.maxRunTime(3, intArrayOf(10, 10, 3, 5)).print()
    s.maxRunTime(1, intArrayOf(53, 69)).print()
    s.maxRunTime(9, intArrayOf(18, 54, 2, 53, 87, 31, 71, 4, 29, 25)).print()
    s.maxRunTime(2, intArrayOf(3, 3, 3)).print()
}

class SolutionD {
    fun maxRunTime(n: Int, batteries: IntArray): Long {
        batteries.sortDescending()
        var c = n
        var sum = 0L
        batteries.forEach {
            sum += it
        }
        batteries.forEach {
            if (it > sum / c) {
                c--
                sum -= it
            } else {
                return sum / c
            }
        }
        return -1
    }
}