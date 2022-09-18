package leetcode.contest.c311

import utils.lcm

class SolutionA {
    fun smallestEvenMultiple(n: Int): Int {
        return lcm(2, n)
    }
}