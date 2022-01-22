package leetcode.contest.b70

import utils.print

fun main() {
    val s = SolutionB()
    s.numberOfArrays(intArrayOf(1, -3, 4), 1, 6).print()
}

class SolutionB {
    fun numberOfArrays(differences: IntArray, lower: Int, upper: Int): Int {
        var min = 0L
        var max = 0L
        var cur = 0L
        for (i in differences.indices) {
            cur += differences[i]
            min = minOf(min, cur)
            max = maxOf(max, cur)
        }
        return maxOf(0, 1 + (upper - lower) - (max - min)).toInt()
    }
}