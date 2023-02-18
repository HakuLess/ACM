package leetcode.contest.b98

import utils.print

fun main() {
    val s = SolutionA()
    s.minMaxDifference(99999).print()
}

class SolutionA {
    fun minMaxDifference(num: Int): Int {
        val str = num.toString()
        val c9 = str.toCharArray().firstOrNull { it != '9' }
        val max = if (c9 == null) str.toInt() else
            str.map { if (it == c9) '9' else it }.toTypedArray().joinToString("").toInt()
        val c0 = str[0]
        val min = str.map { if (it == c0) '0' else it }.toTypedArray().joinToString("").toInt()
        return max - min
    }
}