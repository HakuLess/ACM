package leetcode.contest.c408

import utils.isPrime
import kotlin.math.sqrt

class SolutionB {
    fun nonSpecialCount(l: Int, r: Int): Int {
        val sq = hashSetOf<Int>()
        for (i in 1..sqrt(Int.MAX_VALUE.toDouble()).toInt()) {
            if (isPrime(i)) {
                sq.add(i * i)
            }
        }
        val c = sq.count { it in l..r }
        return r - l - c + 1
    }
}