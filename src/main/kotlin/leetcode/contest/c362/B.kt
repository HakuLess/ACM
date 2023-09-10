package leetcode.contest.c362

import kotlin.math.abs

class SolutionB {
    fun isReachableAtTime(sx: Int, sy: Int, fx: Int, fy: Int, t: Int): Boolean {
        val needX = abs(fx - sx)
        val needY = abs(fy - sy)
        val min = maxOf(needX, needY)
        if (min == 0 && t == 1) return false
        return t >= min
    }
}