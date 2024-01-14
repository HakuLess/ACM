package leetcode.contest.c380

import utils.biMax
import utils.print
import kotlin.math.pow

fun main() {
    val s = SolutionC()
//    s.findMaximumNumber(9, 1).print()
//    s.findMaximumNumber(7, 2).print()
    // 4127
//    s.findMaximumNumber(4096, 6).print()
    // 47
    s.findMaximumNumber(128, 1).print()
    s.findMaximumNumber(Int.MAX_VALUE.toLong() / 100, 1).print()
    //
//    s.findMaximumNumber(3278539330613, 5).print()
}

class SolutionC {
    fun findMaximumNumber(k: Long, x: Int): Long {
        var cur = 0L
        var c = 1L
        while (cur <= k) {
            var add = 0
            c.toString(2).reversed().forEachIndexed { index, c ->
                if ((index + 1) % x == 0 && c == '1')
                    add++
            }
//            println("$c : add $add  $cur to ${cur + add}")
            if (cur + add > k) {
                return c - 1
            }
            cur += add
            c++
//            println("$cur with $c")
        }
        return c - 1
    }
}