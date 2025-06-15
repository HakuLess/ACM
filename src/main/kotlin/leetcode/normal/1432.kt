package leetcode.normal

import utils.print

fun main() {
    val s = Solution1432()
    s.maxDiff(555).print()
    // 8808050
    s.maxDiff(1101057).print()
}

class Solution1432 {
    fun maxDiff(num: Int): Int {
        val s0 = num.toString().firstOrNull { it != '9' } ?: '9'
        val max = num.toString().replace(s0, '9').toInt()
        val s1 = num.toString().firstOrNull { it != '1' && it != '0' } ?: '1'

        val min = if (s1 == '1') {
            num
        } else if (s1 == num.toString()[0]) {
            num.toString().replace(s1, '1').toInt()
        } else {
            num.toString().replace(s1, '0').toInt()
        }

//        println("$max - $min = ${max - min}")

        return max - min
    }
}