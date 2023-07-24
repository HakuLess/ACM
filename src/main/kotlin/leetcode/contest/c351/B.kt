package leetcode.contest.c351

import utils.print
import utils.quickPower
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionB()
    s.makeTheIntegerZero(3, -2).print()
    s.makeTheIntegerZero(5, 7).print()
    s.makeTheIntegerZero(110, 55).print()
}

class SolutionB {
    fun makeTheIntegerZero(num1: Int, num2: Int): Int {
        var c = num1.toLong()
        for (i in 1..60) {
            c -= num2
            if (c <= 0) continue
            if (c.toString(2).count { it == '1' } <= i && c >= i) {
                return i
            }
        }
        return -1
    }
}