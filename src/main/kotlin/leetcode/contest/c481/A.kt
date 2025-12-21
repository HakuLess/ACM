package leetcode.contest.c481

import kotlin.math.abs

class SolutionA {
    fun mirrorDistance(n: Int): Int {
        return abs(n - n.toString().reversed().toInt())
    }
}