package leetcode.contest.c313

import utils.changeBit
import utils.countOne
import utils.print

fun main() {
    val s = SolutionC()
//    s.minimizeXor(3, 5).print()
//    s.minimizeXor(1, 12).print()
//
//    s.minimizeXor(12, 1).print()

    // 24
    s.minimizeXor(25, 72).print()
    25.toString(2).print()
    72.toString(2).print()
}

class SolutionC {
    fun minimizeXor(num1: Int, num2: Int): Int {
        var c2 = num2.countOne()
        val c1 = num1.countOne()
        if (c2 == c1) return num1

        var ans = 0

        if (c2 < c1) {
            num1.toString(2).forEachIndexed { index, it ->
                if (it == '1') {
                    if (c2 != 0) {
                        c2--
                        ans = ans.changeBit(num1.toString(2).length - index - 1)
                    }
                }
            }
        }

        if (c2 > c1) {
            var d = c2 - c1
            num1.toString(2).padStart(32, '0').reversed().forEachIndexed { index, it ->
                if (it != '1') {
                    if (d != 0) {
                        ans = ans.changeBit(index)
                        d--
                    }
                } else {
                    ans = ans.changeBit(index)
                }
            }
        }
        return ans
    }
}