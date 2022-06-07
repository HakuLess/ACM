package leetcode.normal

import utils.biMin
import utils.print

fun main() {
    val s = Solution875()
    s.minEatingSpeed(intArrayOf(3, 6, 7, 11), 8).print()
}

class Solution875 {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        return biMin { s ->
            var c = 0L
            piles.map {
                c += (it - 1) / s + 1
            }
            c <= h
        }.toInt()
    }
}