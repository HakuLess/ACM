package leetcode.contest.b94

import utils.biMin
import utils.lcm
import utils.print

fun main() {
    val s = SolutionC()
    // 217679202
    s.minimizeSet(92761, 48337, 208563424, 9115778).print()
}

class SolutionC {
    fun minimizeSet(divisor1: Int, divisor2: Int, uniqueCnt1: Int, uniqueCnt2: Int): Int {
        val lcm = lcm(divisor1.toLong(), divisor2.toLong())
        return biMin(1L, Long.MAX_VALUE / 2) {
            // 仅满足1 + 满足12
            val a = it - it / divisor1
            // 仅满足2 + 满足12
            val b = it - it / divisor2
            // 仅满足1 + 仅满足2 + 满足12
            val c = it - it / lcm

            // 仅满足1
            val na = c - b
            // 仅满足2
            val nb = c - a
            // 仅满足12
            val nc = c - na - nb

            // a个满足1 b个满足2 c个1、2都满足
            val la = maxOf(uniqueCnt1 - na, 0)
            val lb = maxOf(uniqueCnt2 - nb, 0)
            la + lb <= nc

        }.toInt()
    }
}