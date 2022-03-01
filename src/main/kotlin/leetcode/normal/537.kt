package leetcode.normal

import utils.print

fun main() {
    val s = Solution537()
    s.complexNumberMultiply("1+1i", "1+1i").print()
    s.complexNumberMultiply("1+-1i", "1+-1i").print()
}

class Solution537 {
    fun complexNumberMultiply(num1: String, num2: String): String {
        fun trans(s: String): Pair<Int, Int> {
            val (a, b) = s.take(s.lastIndex).split('+').map {
                it.toInt()
            }
            return Pair(a, b)
        }
        val (a0, a1) = trans(num1)
        val (b0, b1) = trans(num2)
        val ans0 = a0 * b0 - a1 * b1
        val ans1 = a0 * b1 + a1 * b0
        return "$ans0+${ans1}i"
    }
}