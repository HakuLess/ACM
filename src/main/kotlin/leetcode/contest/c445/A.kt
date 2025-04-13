package leetcode.contest.c445

import kotlin.math.abs

class SolutionA {
    fun findClosest(x: Int, y: Int, z: Int): Int {
        val cmp = abs(x - z) - abs(y - z)
        return if (cmp < 0) {
            1
        } else if (cmp > 0) {
            2
        } else {
            0
        }
    }
}