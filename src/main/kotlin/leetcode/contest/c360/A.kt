package leetcode.contest.c360

import kotlin.math.abs

class SolutionA {
    fun furthestDistanceFromOrigin(moves: String): Int {
        var ans1 = 0
        var ans2 = 0
        moves.forEach {
            if (it == 'L') {
                ans1--
            } else {
                ans1++
            }

            if (it == 'R') {
                ans2--
            } else {
                ans2++
            }
        }
        return maxOf(abs(ans1), abs(ans2))
    }
}